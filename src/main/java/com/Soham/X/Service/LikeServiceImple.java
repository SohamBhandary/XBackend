package com.Soham.X.Service;

import com.Soham.X.Entities.Like;
import com.Soham.X.Entities.Tweet;
import com.Soham.X.Entities.User;
import com.Soham.X.Exception.TweetException;
import com.Soham.X.Exception.UserException;
import com.Soham.X.Repository.LikeRepository;
import com.Soham.X.Repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImple implements LikeService{
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private TweetService tweetService;
    @Autowired
    private TweetRepository tweetRepository;
    @Override
    public Like likeTweet(Long tweetId, User user) throws TweetException, UserException {
        Like isLikeExsists=likeRepository.doesLikeExist(user.getId(), tweetId);
        if(isLikeExsists!=null){
            likeRepository.deleteById(tweetId);
            return isLikeExsists ;
        }
        Tweet tweet=tweetService.findById(tweetId);
        Like like=new Like();
        like.setTweet(tweet);
        like.setUser(user);
        Like savedLike=likeRepository.save(like);
        tweet.getLikes().add(savedLike);
        tweetRepository.save(tweet);
        return savedLike;


    }

    @Override
    public List<Like> getAllLikes(Long tweetId) throws TweetException {
        Tweet tweet=tweetService.findById(tweetId);
        List<Like>likes=likeRepository.findByTweetId(tweetId);


        return likes ;
    }
}
