package com.blogapp.formdata;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostForm {
    private String title;
    private String content;
    private String imgName;
    private Date date;
}
