package com.Soham.X.Util;

import com.Soham.X.Entities.Like;
import com.Soham.X.Entities.Tweet;
import com.Soham.X.Entities.User;

public class TweetUtil {
    public final static boolean isLikedByReqUser(User req, Tweet tweet){

        for (Like like:tweet.getLikes()){
            if(like.getUser().getId().equals(req.getId())){
                return true;
            }
        }
        return false;

    }
    public final static boolean isRetweetedByReqUser(User req,Tweet tweet){
        for (User user:tweet.getRetweetUser()){
            if(user.getId().equals(req.getId())){
                return true;
            }
        }
        return false;
    }
}
