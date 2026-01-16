package com.arpon007.netflixclone.RatingService.service.impl;

import com.arpon007.netflixclone.RatingService.dto.UserRatingRequest;
import com.arpon007.netflixclone.RatingService.entity.UserRating;
import com.arpon007.netflixclone.RatingService.repository.UserRatingRepository;
import com.arpon007.netflixclone.RatingService.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private UserRatingRepository userRatingRepository;

    @Override
    public UserRating addRating(Long userId, UserRatingRequest request) {
        Optional<UserRating> existingRating = userRatingRepository.findByUserIdAndVideoId(userId, request.getVideoId());

        UserRating rating;
        if (existingRating.isPresent()) {
            rating = existingRating.get();
            rating.setRating(request.getRating());
        } else {
            rating = new UserRating();
            rating.setUserId(userId);
            rating.setVideoId(request.getVideoId());
            rating.setRating(request.getRating());
        }
        return userRatingRepository.save(rating);
    }

    @Override
    public Double getAverageRating(Long videoId) {
        return userRatingRepository.getAverageRating(videoId);
    }

    @Override
    public Long getRatingCount(Long videoId) {
        return userRatingRepository.countByVideoId(videoId);
    }

    @Override
    public UserRating getUserRating(Long userId, Long videoId) {
        return userRatingRepository.findByUserIdAndVideoId(userId, videoId).orElse(null);
    }
}
