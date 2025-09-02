package com.Soham.X.Service;

import com.Soham.X.Entities.Tweet;
import com.Soham.X.Entities.User;
import com.Soham.X.Exception.TweetException;
import com.Soham.X.Exception.UserException;
import com.Soham.X.Request.TweetReplyReq;

import java.util.List;

public interface TweetService {

    public Tweet createTweet(Tweet req, User user) throws UserException;
    public List<Tweet> findAllTweet();
    public Tweet retweet(Long tweetId,User user)throws UserException, TweetException;
    public Tweet findById(Long tweetId) throws TweetException;
    public void deleteTweetById(Long tweetId,Long userId) throws TweetException,UserException;
    public Tweet removeFromRetweet(Long tweetId,User user)throws TweetException,UserException;
    public Tweet createReply(TweetReplyReq req, User user)throws TweetException;
    public List<Tweet> getUserTweet(User user);
    public List<Tweet> findByLikesContainsUser(User user);
}
