package com.Soham.X.Mapper;

import com.Soham.X.DTO.TweetDTO;
import com.Soham.X.DTO.UserDTO;
import com.Soham.X.Entities.Tweet;
import com.Soham.X.Entities.User;
import com.Soham.X.Util.TweetUtil;

import java.util.ArrayList;
import java.util.List;

public class TweetDTOMapper {

    public static TweetDTO totweetDTO(Tweet tweet, User req){
        UserDTO user = UserDTOMapper.toUserDto(tweet.getUser());
        boolean isLiked = TweetUtil.isLikedByReqUser(req, tweet);
        boolean isRetweeted = TweetUtil.isRetweetedByReqUser(req, tweet);
        List<Long> retweetUserid = new ArrayList<>();
        for(User user1 : tweet.getRetweetUser()){
            retweetUserid.add(user1.getId());
        }

        TweetDTO tweetDTO = new TweetDTO();
        tweetDTO.setId(tweet.getId());
        tweetDTO.setContent(tweet.getContent());
        tweetDTO.setCreatedAt(tweet.getCreatedAt());
        tweetDTO.setImage(tweet.getImage());
        tweetDTO.setTotalLikes(tweet.getLikes().size());
        tweetDTO.setTotalReplies(tweet.getReplyTweets().size());
        tweetDTO.setTotalRetweets(tweet.getRetweetUser().size());
        tweetDTO.setUserDTO(user);
        tweetDTO.setLiked(isLiked);
        tweetDTO.setRetweet(isRetweeted);
        tweetDTO.setReTweetUsersId(retweetUserid);
        tweetDTO.setReplyTweets(totweetDTOS(tweet.getReplyTweets(), req));
        tweetDTO.setVideo(tweet.getVideo()); // fixed: was tweetDTO.getVideo()

        return tweetDTO;
    }

    public static List<TweetDTO> totweetDTOS(List<Tweet> tweets, User req){
        List<TweetDTO> tweetDTOS = new ArrayList<>();
        for(Tweet tweet : tweets){
            TweetDTO tweetDTO = toReplyTweetDTO(tweet, req);
            tweetDTOS.add(tweetDTO);
        }
        return tweetDTOS;
    }

    private static TweetDTO toReplyTweetDTO(Tweet tweet, User req) {
        UserDTO user = UserDTOMapper.toUserDto(tweet.getUser());
        boolean isLiked = TweetUtil.isLikedByReqUser(req, tweet);
        boolean isRetweeted = TweetUtil.isRetweetedByReqUser(req, tweet);
        List<Long> retweetUserid = new ArrayList<>();
        for(User user1 : tweet.getRetweetUser()){
            retweetUserid.add(user1.getId());
        }

        TweetDTO tweetDTO = new TweetDTO();
        tweetDTO.setId(tweet.getId());
        tweetDTO.setContent(tweet.getContent());
        tweetDTO.setCreatedAt(tweet.getCreatedAt());
        tweetDTO.setImage(tweet.getImage());
        tweetDTO.setTotalLikes(tweet.getLikes().size());
        tweetDTO.setTotalReplies(tweet.getReplyTweets().size());
        tweetDTO.setTotalRetweets(tweet.getRetweetUser().size());
        tweetDTO.setUserDTO(user);
        tweetDTO.setLiked(isLiked);
        tweetDTO.setRetweet(isRetweeted);
        tweetDTO.setReTweetUsersId(retweetUserid);
        tweetDTO.setVideo(tweet.getVideo()); // fixed

        return tweetDTO;
    }
}
