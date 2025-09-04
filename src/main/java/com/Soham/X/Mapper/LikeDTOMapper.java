package com.Soham.X.Mapper;

import com.Soham.X.DTO.LikeDTO;
import com.Soham.X.DTO.TweetDTO;
import com.Soham.X.DTO.UserDTO;
import com.Soham.X.Entities.Like;
import com.Soham.X.Entities.User;

import java.util.ArrayList;
import java.util.List;

public class LikeDTOMapper {
    public static LikeDTO toLikeDTO(Like like, User req){
        UserDTO user=UserDTOMapper.toUserDto(like.getUser());
        UserDTO reqUserDTO=UserDTOMapper.toUserDto(req);
        TweetDTO tweet=TweetDTOMapper.totweetDTO(like.getTweet(),req);

        LikeDTO likeDTO=new LikeDTO();
        likeDTO.setId(like.getId());
        likeDTO.setTweetDTO(tweet);
        likeDTO.setUser(user);


        return likeDTO;

    }
    public static List<LikeDTO> toLikeDtos(List<Like>likes,User req){
        List<LikeDTO>likeDTOS= new ArrayList<>();
        for (Like like:likes){
            UserDTO user=UserDTOMapper.toUserDto(like.getUser());
            TweetDTO tweet=TweetDTOMapper.totweetDTO(like.getTweet(),req);
            LikeDTO likeDTO=new LikeDTO();
            likeDTO.setId(like.getId());
            likeDTO.setTweetDTO(tweet);
            likeDTO.setUser(user);
            likeDTOS.add(likeDTO);

        }
        return likeDTOS;
    }
}
