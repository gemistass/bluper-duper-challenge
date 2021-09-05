package com.blue1.challenge.homehandler;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface HomeRepository extends MongoRepository<Home, String> {


    List<Home> findByName(String name);

}
