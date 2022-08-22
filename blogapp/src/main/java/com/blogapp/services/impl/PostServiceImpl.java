package com.blogapp.services.impl;

import com.blogapp.entities.Category;
import com.blogapp.entities.Post;
import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.formdata.PostForm;
import com.blogapp.payloads.PostDto;
import com.blogapp.payloads.PostResponse;
import com.blogapp.payloads.UserDto;
import com.blogapp.repositories.CategoryRepo;
import com.blogapp.repositories.PostRepo;
import com.blogapp.repositories.UserRepo;
import com.blogapp.services.FileService;
import com.blogapp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Value("${project.image}")
    private String path;
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private FileService fileService;


    @Override
    public PostDto createPost(PostForm postForm, Integer userId, Integer categoryId) {

        User user =  this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","User id",userId));
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        Post post = this.modelMapper.map(postForm, Post.class);
        post.setImgName("default.png");
        post.setDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post createdPost =  this.postRepo.save(post);
        return this.modelMapper.map(createdPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostForm postForm, Integer postId) {
       Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post id",postId));

       post.setTitle(postForm.getTitle());
       post.setContent(postForm.getContent());
       post.setImgName(postForm.getImgName());
       Post updatedPost =  this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDto.class);
    }
    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post id",postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {


        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable p =PageRequest.of(pageNumber,pageSize, sort);

        Page<Post> pagePost = this.postRepo.findAll(p);
        List<Post> allPosts = pagePost.getContent();
        List<PostDto> postDtos =  allPosts.stream().map(post -> this.modelMapper
                        .map(post, PostDto.class))
                .collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
       Post post =  this.postRepo.findById(postId)
               .orElseThrow(()->new ResourceNotFoundException("Post","Post id",postId));
       return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);
        return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","User id",userId));

        List<Post> posts = this.postRepo.findByUser(user);
        return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos = posts.stream().map(post->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    @Transactional
    public PostDto getPostDto(MultipartFile image, Integer postId) throws IOException {
        Post post =  this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","Post id",postId));
        String fileName = this.fileService.uploadImage(path, image);
        post.setImgName(fileName);
        postRepo.save(post);
        return modelMapper.map(post, PostDto.class);
    }

}


















