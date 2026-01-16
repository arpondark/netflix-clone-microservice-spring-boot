package com.arpon007.netflixclone.RatingService.controller;

import com.arpon007.netflixclone.RatingService.dto.UserRatingRequest;
import com.arpon007.netflixclone.RatingService.entity.UserRating;
import com.arpon007.netflixclone.RatingService.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<?> addRating(@RequestHeader(value = "X-User-Id", required = false) Long userId,
            @RequestBody UserRatingRequest request) {
        // In a real microservice, userId should come from the Gateway/Auth token.
        // For now, assuming it's passed in header or we might need to handle it
        // differently.
        // If userId is missing, we can't rate.
        if (userId == null) {
            // For testing purposes allow passing it in request or assume 1L
            // return ResponseEntity.status(401).body("User ID required");
            userId = 1L; // Fallback for dev/testing locally without Gateway adding headers yet
        }

        UserRating rating = ratingService.addRating(userId, request);
        return ResponseEntity.ok(rating);
    }

    @GetMapping("/video/{videoId}")
    public ResponseEntity<?> getVideoRatingStats(@PathVariable Long videoId) {
        Double average = ratingService.getAverageRating(videoId);
        Long count = ratingService.getRatingCount(videoId);

        Map<String, Object> response = new HashMap<>();
        response.put("videoId", videoId);
        response.put("averageRating", average != null ? average : 0.0);
        response.put("totalRatings", count);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/video/{videoId}/user/{userId}")
    public ResponseEntity<?> getUserRatingForVideo(@PathVariable Long videoId, @PathVariable Long userId) {
        UserRating rating = ratingService.getUserRating(userId, videoId);
        if (rating == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rating);
    }
}
