package com.blogapp.services;

import com.blogapp.entities.Post;
import com.blogapp.formdata.PostForm;
import com.blogapp.payloads.PostDto;
import com.blogapp.payloads.PostResponse;
import com.blogapp.payloads.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {

    PostDto createPost(PostForm postForm, Integer userId, Integer categoryId);

    PostDto updatePost(PostForm postForm, Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPost(String keyword);

     PostDto getPostDto(MultipartFile image, Integer postId) throws IOException;
}
