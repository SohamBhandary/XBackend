package com.Soham.X.Controllers;

import com.Soham.X.DTO.TweetDTO;
import com.Soham.X.Service.TweetService;
import com.Soham.X.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {
    @Autowired
    private TweetService tweetService;
    @Autowired
    private UserService userService;

    public ResponseEntity<TweetDTO>


}
