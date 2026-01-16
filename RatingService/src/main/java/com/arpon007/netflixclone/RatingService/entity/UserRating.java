package com.arpon007.netflixclone.RatingService.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "user_ratings")
@Data
public class UserRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "video_id", nullable = false)
    private Long videoId;

    @Column(nullable = false)
    private Double rating;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
}
