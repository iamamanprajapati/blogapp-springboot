package com.blogapp.services;

import com.blogapp.formdata.CommentForm;
import com.blogapp.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentForm commentForm, Integer postId, Integer userId);
    void deleteComment(Integer commentId);

}
