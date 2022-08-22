package com.blogapp.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiException extends  RuntimeException{

     String message;

    public ApiException(String message){
        this.message = message;
    }


}
