package com.Soham.X.Mapper;

import com.Soham.X.DTO.UserDTO;
import com.Soham.X.Entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTOMapper {
    public static UserDTO toUserDto(User user) {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFullName(user.getFullName());
        userDTO.setImage(user.getImage());
        userDTO.setBackgroundImg(user.getBackgroundImage());
        userDTO.setBio(user.getBio());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setFollowers(toUserDto(user.getFollowers()));
        userDTO.setFollowing(toUserDto(user.getFollowings()));
        userDTO.setLogin_with_google(user.isLoginWithGoogle());
        userDTO.setLocation(user.getLocation());
//        userDTO.setVerified();

        return userDTO;
    }

    private static List<UserDTO> toUserDto(List<User> followers) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : followers) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setFullName(user.getFullName());
            userDTO.setImage(user.getImage());
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }
}
