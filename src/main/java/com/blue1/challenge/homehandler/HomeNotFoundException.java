package com.blue1.challenge.homehandler;

public class HomeNotFoundException extends RuntimeException {

    public HomeNotFoundException(String id) {
        super("Could not find Home " + id);
    }
}