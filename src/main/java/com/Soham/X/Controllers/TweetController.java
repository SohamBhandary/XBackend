package com.Soham.X.Controllers;

import com.Soham.X.DTO.TweetDTO;
import com.Soham.X.Entities.Tweet;
import com.Soham.X.Entities.User;
import com.Soham.X.Exception.TweetException;
import com.Soham.X.Exception.UserException;
import com.Soham.X.Mapper.TweetDTOMapper;
import com.Soham.X.Request.TweetReplyReq;
import com.Soham.X.Response.ApiResponse;
import com.Soham.X.Service.TweetService;
import com.Soham.X.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {
    @Autowired
    private TweetService tweetService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<TweetDTO> createTweet(@RequestBody Tweet req,
                                                @RequestHeader("Authorization") String jwt) throws UserException, TweetException {
        User user=userService.findUserProfileByJwt(jwt);
        Tweet tweet=tweetService.createTweet(req,user);
        TweetDTO tweetDTO= TweetDTOMapper.totweetDTO(tweet,user);
        return new ResponseEntity<>(tweetDTO, HttpStatus.CREATED);


    }
    @PostMapping("/reply")
    public ResponseEntity<TweetDTO> replyTweet(@RequestBody TweetReplyReq req,
                                                @RequestHeader("Authorization") String jwt) throws UserException, TweetException {
        User user=userService.findUserProfileByJwt(jwt);
        Tweet tweet=tweetService.createReply(req,user);
        TweetDTO tweetDTO= TweetDTOMapper.totweetDTO(tweet,user);
        return new ResponseEntity<>(tweetDTO, HttpStatus.CREATED);


    }
    @PutMapping("/{tweetId}/retweet")
    public ResponseEntity<TweetDTO> retweet(@PathVariable Long tweetId,
                                               @RequestHeader("Authorization") String jwt) throws UserException, TweetException {
        User user=userService.findUserProfileByJwt(jwt);
        Tweet tweet=tweetService.retweet(tweetId,user);
        TweetDTO tweetDTO= TweetDTOMapper.totweetDTO(tweet,user);
        return new ResponseEntity<>(tweetDTO, HttpStatus.OK);


    }
    @GetMapping("/{tweetId}")
    public ResponseEntity<TweetDTO> findTweetById(@PathVariable Long tweetId,
                                            @RequestHeader("Authorization") String jwt) throws UserException, TweetException {
        User user=userService.findUserProfileByJwt(jwt);
        Tweet tweet=tweetService.findById(tweetId);
        TweetDTO tweetDTO= TweetDTOMapper.totweetDTO(tweet,user);
        return new ResponseEntity<>(tweetDTO, HttpStatus.OK);


    }
    @DeleteMapping("/{tweetId}")
    public ResponseEntity<ApiResponse> deleteTweet(@PathVariable Long tweetId,
                                                  @RequestHeader("Authorization") String jwt) throws UserException, TweetException {
        User user=userService.findUserProfileByJwt(jwt);
     tweetService.deleteTweetById(tweetId, user.getId());
        ApiResponse apiResponse= new ApiResponse("Tweet deleted Succesfuly",true);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);


    }
    @GetMapping("/")
    public ResponseEntity<List<TweetDTO>> getAllTweets(
                                            @RequestHeader("Authorization") String jwt) throws UserException, TweetException {
        User user=userService.findUserProfileByJwt(jwt);

        List<Tweet> tweets=tweetService.findAllTweet();
        List<TweetDTO> tweetDTOs=TweetDTOMapper.totweetDTOS(tweets,user);
        return new ResponseEntity<>(tweetDTOs, HttpStatus.OK);


    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TweetDTO>> getUsersAllTweets(@PathVariable Long userId,
            @RequestHeader("Authorization") String jwt) throws UserException, TweetException {
        User user=userService.findUserProfileByJwt(jwt);

        List<Tweet> tweets=tweetService.getUserTweet(user);
        List<TweetDTO> tweetDTOs=TweetDTOMapper.totweetDTOS(tweets,user);
        return new ResponseEntity<>(tweetDTOs, HttpStatus.OK);


    }
    @GetMapping("/user/{userId}/likes")
    public ResponseEntity<List<TweetDTO>> findTweetByLikesUser(@PathVariable Long userId,
                                                            @RequestHeader("Authorization") String jwt) throws UserException, TweetException {
        User user=userService.findUserProfileByJwt(jwt);

        List<Tweet> tweets=tweetService.findByLikesContainsUser(user);
        List<TweetDTO> tweetDTOs=TweetDTOMapper.totweetDTOS(tweets,user);
        return new ResponseEntity<>(tweetDTOs, HttpStatus.OK);


    }


}
