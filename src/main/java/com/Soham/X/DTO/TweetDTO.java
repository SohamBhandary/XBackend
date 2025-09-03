package com.Soham.X.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class TweetDTO {
    private Long id;
    private String content;
    private String image;
    private String video;
    private UserDTO userDTO;
    private LocalDateTime createdAt;
    private int totalLikes;
    private int totalReplies;
    private int totalRetweets;
    private boolean isLiked;
    private boolean isRetweet;
    private List<Long> reTweetUsersId;
    private List<TweetDTO> replyTweets;

}
