package com.Soham.X.Repository;

import com.Soham.X.Entities.Tweet;
import com.Soham.X.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet,Long> {
    List<Tweet>findAllByIsTweetTrueOrderByCreatedAtDesc();
    List<Tweet>findByRetweetUserContainsOrUser_IdAndIsTweetTrueOrderByCreatedAtDesc(User user,Long userId);
    List<Tweet>findByLikesContainingOrderByCreatedAtDesc(User user);
    @Query("Select t from Tweet t JOIN t.likes l WHERE l.user.id=:userId ")
    List<Tweet>findByLikesUser_Id(Long userId);

}
