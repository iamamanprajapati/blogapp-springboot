package com.blogapp.controllers;

import com.blogapp.formdata.CommentForm;
import com.blogapp.payloads.ApiResponse;
import com.blogapp.payloads.CommentDto;
import com.blogapp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/user/{userId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentForm commentForm, @PathVariable Integer postId, @PathVariable Integer userId){

        CommentDto createdComment = this.commentService.createComment(commentForm, postId,userId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);

    }

    @DeleteMapping("comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){

        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("comment deleted successfully",true,HttpStatus.OK), HttpStatus.CREATED);

    }

}
