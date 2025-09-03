package com.Soham.X.Repository;

import com.Soham.X.Entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("SELECT l FROM Like l WHERE l.user.id = :userId AND l.tweet.id = :tweetId")
    Like doesLikeExist(@Param("userId") Long userId,
                       @Param("tweetId") Long tweetId);

    @Query("SELECT l FROM Like l WHERE l.tweet.id = :tweetId")
    List<Like> findByTweetId(@Param("tweetId") Long tweetId);
}
