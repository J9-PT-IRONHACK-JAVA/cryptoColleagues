package com.cryptocolleagues.exceptions;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(Long id){
        super(String.format("Post with Id %d not found", id));
    }
}
