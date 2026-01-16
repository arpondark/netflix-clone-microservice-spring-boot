package com.arpon007.netflixclone.RatingService.service;

import com.arpon007.netflixclone.RatingService.dto.UserRatingRequest;
import com.arpon007.netflixclone.RatingService.entity.UserRating;

public interface RatingService {
    UserRating addRating(Long userId, UserRatingRequest request);

    Double getAverageRating(Long videoId);

    Long getRatingCount(Long videoId);

    UserRating getUserRating(Long userId, Long videoId);
}
