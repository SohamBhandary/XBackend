package com.Soham.X.Controllers;

import com.Soham.X.DTO.LikeDTO;
import com.Soham.X.Entities.Like;
import com.Soham.X.Entities.User;
import com.Soham.X.Exception.TweetException;
import com.Soham.X.Exception.UserException;
import com.Soham.X.Mapper.LikeDTOMapper;
import com.Soham.X.Service.LikeService;
import com.Soham.X.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LikeController {
    @Autowired
    private UserService userService;
    @Autowired
    private LikeService likeService;

    @PostMapping("/{tweetId}/likes")
    public ResponseEntity<LikeDTO> likeTweet(@PathVariable Long tweetId,
                                             @RequestHeader("Authorization") String jwt)throws UserException, TweetException {
        User user=userService.findUserProfileByJwt(jwt);
        Like like=likeService.likeTweet(tweetId,user);
        LikeDTO likeDTO= LikeDTOMapper.toLikeDTO(like,user);



        return new ResponseEntity<LikeDTO>(likeDTO, HttpStatus.CREATED);

    }
    @PostMapping("/tweet/{tweetId}")
    public ResponseEntity<List<LikeDTO>> getAllLikes(@PathVariable Long tweetId,
                                                     @RequestHeader("Authorization") String jwt)throws UserException, TweetException {
        User user=userService.findUserProfileByJwt(jwt);
        List<Like> likes=likeService.getAllLikes(tweetId);
        List<LikeDTO> likeDTO= LikeDTOMapper.toLikeDtos(likes,user);



        return new ResponseEntity<>(likeDTO, HttpStatus.CREATED);

    }


}
