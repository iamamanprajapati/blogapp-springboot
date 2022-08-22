package com.blogapp.formdata;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryForm {

    @NotBlank
    @Size(min=4,message = "Size must be minimum 4 characters")
    private String categoryTitle;
    @NotBlank
    @Size(min=10)
    private String categoryDescription;
}
