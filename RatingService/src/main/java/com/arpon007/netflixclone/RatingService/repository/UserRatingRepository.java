package com.arpon007.netflixclone.RatingService.repository;

import com.arpon007.netflixclone.RatingService.entity.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRatingRepository extends JpaRepository<UserRating, Long> {

    Optional<UserRating> findByUserIdAndVideoId(Long userId, Long videoId);

    @Query("SELECT AVG(r.rating) FROM UserRating r WHERE r.videoId = :videoId")
    Double getAverageRating(Long videoId);

    long countByVideoId(Long videoId);
}
