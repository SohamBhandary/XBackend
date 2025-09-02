package com.Soham.X.Repository;

import com.Soham.X.Entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {

    @Query("SELECT l from Like l where l.userId=:userId AND l.tweetId=:tweetId")
    public Like doesLikeExist(@Param("userId")Long userId,@Param("tweetId")Long tweetId);

    @Query("SELECT l from Like l WHERE l.tweetId=:tweetId")
    public List<Like> findByTweetId(@Param("tweetId")Long tweetId);

}
