package com.Soham.X.Service;

import com.Soham.X.Entities.Like;
import com.Soham.X.Entities.User;
import com.Soham.X.Exception.TweetException;
import com.Soham.X.Exception.UserException;

import java.util.List;

public interface LikeService {
    public Like likeTweet(Long tweetId, User user) throws TweetException, UserException;
    public List<Like> getAllLikes(Long tweetId) throws TweetException;
}
