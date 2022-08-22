package com.blogapp.payloads;


import com.blogapp.entities.Category;
import com.blogapp.entities.Comment;
import com.blogapp.entities.User;
import com.blogapp.formdata.CommentForm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private String imgName;
    private Date date;
    private CategoryDto category;
    private UserDto user;
    private Set<CommentForm> comments = new HashSet<>();
}
