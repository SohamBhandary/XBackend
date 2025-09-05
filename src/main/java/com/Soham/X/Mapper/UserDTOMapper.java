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
        userDTO.setFollowers(toUserDtos(user.getFollowers()));   // FIX: call plural method
        userDTO.setFollowing(toUserDtos(user.getFollowings()));  // FIX: call plural method
        userDTO.setLogin_with_google(user.isLoginWithGoogle());
        userDTO.setLocation(user.getLocation());
//        userDTO.setVerified();

        return userDTO;
    }

    public static List<UserDTO> toUserDtos(List<User> followers) {
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
