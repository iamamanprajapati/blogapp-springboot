package com.blogapp.payloads;


import com.blogapp.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private int id;
    private String content;
    private UserDto user;
    private PostDto post;

}
