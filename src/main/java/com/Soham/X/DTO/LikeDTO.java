package com.Soham.X.DTO;

import com.Soham.X.Entities.User;
import lombok.Data;

@Data
public class LikeDTO {

    private Long id;
    private UserDTO user;
    private TweetDTO tweetDTO;
}
