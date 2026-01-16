package com.arpon007.netflixclone.RatingService.dto;

import lombok.Data;

@Data
public class UserRatingRequest {
    private Long videoId;
    private Double rating;
}
