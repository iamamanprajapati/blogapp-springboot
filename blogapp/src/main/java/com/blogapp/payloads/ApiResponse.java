package com.blogapp.payloads;


import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiResponse {

    private String message;
    private boolean success;
    private HttpStatus status;


}
