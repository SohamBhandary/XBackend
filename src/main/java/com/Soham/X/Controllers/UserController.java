package com.Soham.X.Controllers;

import com.Soham.X.DTO.UserDTO;
import com.Soham.X.Entities.User;
import com.Soham.X.Exception.TweetException;
import com.Soham.X.Exception.UserException;
import com.Soham.X.Mapper.UserDTOMapper;
import com.Soham.X.Service.UserService;
import com.Soham.X.Util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile(@RequestHeader ("Authorization")String jwt) throws UserException, TweetException{

        User user=userService.findUserProfileByJwt(jwt);
        UserDTO userDTO= UserDTOMapper.toUserDto(user);
        userDTO.setReq_user(true);
        return  new ResponseEntity<UserDTO>(userDTO, HttpStatus.ACCEPTED);


    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserByid(@PathVariable Long userId
            ,@RequestHeader ("Authorization")String jwt) throws UserException, TweetException{

        User requser=userService.findUserProfileByJwt(jwt);
        User user=userService.findUserById(userId);

        UserDTO userDTO= UserDTOMapper.toUserDto(user);
        userDTO.setReq_user(UserUtil.isReqUser(requser,user));
        userDTO.setFollowed(UserUtil.isFollowedByReqUser(requser,user));
        return  new ResponseEntity<UserDTO>(userDTO, HttpStatus.ACCEPTED);


    }
    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> serachUser(@RequestParam String query
            ,@RequestHeader ("Authorization")String jwt) throws UserException, TweetException{

        User requser=userService.findUserProfileByJwt(jwt);
        List<User> users=userService.serachUser(query);

        List<UserDTO> userDTOs= UserDTOMapper.toUserDtos(users);
//        userDTO.setReq_user(UserUtil.isReqUser(requser,user));
//        userDTO.setFollowed(UserUtil.isFollowedByReqUser(requser,user));
        return  new ResponseEntity<>(userDTOs, HttpStatus.ACCEPTED);


    }
    @PutMapping("/update")
    public ResponseEntity <UserDTO> update(@RequestBody User req
            ,@RequestHeader ("Authorization")String jwt) throws UserException, TweetException{

        User requser=userService.findUserProfileByJwt(jwt);
        User user=userService.updateUser(requser.getId(),req);

        UserDTO userDTO= UserDTOMapper.toUserDto(user);
//        userDTO.setReq_user(UserUtil.isReqUser(requser,user));
//        userDTO.setFollowed(UserUtil.isFollowedByReqUser(requser,user));
        return  new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);


    }
    @PutMapping("/{userId}/follow")
    public ResponseEntity <UserDTO> serachUser(@PathVariable Long userId
            ,@RequestHeader ("Authorization")String jwt) throws UserException, TweetException{

        User requser=userService.findUserProfileByJwt(jwt);
        User user=userService.followUser(userId,requser);

        UserDTO userDTO= UserDTOMapper.toUserDto(user);
        userDTO.setFollowed(UserUtil.isFollowedByReqUser(requser,user));
//        userDTO.setReq_user(UserUtil.isReqUser(requser,user));
//        userDTO.setFollowed(UserUtil.isFollowedByReqUser(requser,user));
        return  new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);


    }

}
