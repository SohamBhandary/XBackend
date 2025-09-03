package com.Soham.X.Service;

import com.Soham.X.Config.JwtProvider;
import com.Soham.X.Entities.User;
import com.Soham.X.Exception.UserException;
import com.Soham.X.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImple implements UserService{
    @Autowired
    private UserRepository userRepository    ;
     @Autowired
     private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws UserException {
        User user=userRepository.findById(userId).orElseThrow(()-> new UserException("User Not Found"));

        return user;
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email=jwtProvider.getEmailFromJwtToken(jwt);
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new UserException("User not found with email"+email);
        }
        return user;
    }

    @Override
    public User updateUser(Long userId, User req) throws UserException {
        User user=findUserById(userId);
        if(req.getFullName()!=null){
            user.setFullName(req.getFullName());
        }
        if(req.getImage()!=null){
            user.setImage(req.getImage());
        }
        if(req.getBackgroundImage()!=null){
            user.setBackgroundImage(req.getBackgroundImage());
        }
        if(req.getBirthDate()!=null){
            user.setBirthDate(req.getBirthDate());
        }
        if(req.getLocation()!=null){
            user.setLocation(req.getLocation());
        }
        if(req.getBio()!=null){
            user.setBio(req.getBio());
        }
        if(req.getWebsite()!=null){
            user.setWebsite(req.getWebsite());
        }
        return userRepository.save(user);
    }

    @Override
    public User followUser(Long userId, User user) throws UserException {
        User followToUser=findUserById(userId);
        if(user.getFollowings().contains(followToUser)&& followToUser.getFollowers().contains(user)){
            user.getFollowings().remove(followToUser);
            followToUser.getFollowers().remove(user);
        }
        else{
            user.getFollowings().add(followToUser);
            followToUser.getFollowings().add(user);
        }
        userRepository.save(followToUser);
        userRepository.save(user);
        return followToUser;
    }

    @Override
    public List<User> serachUser(String query) {

        return userRepository.search(query);
    }
}
