package com.blue1.challenge.seniorhandler;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SeniorRepository extends MongoRepository<Senior, String> {

    List<Senior> findBySensorId(String sensorId);
    List<Senior> findBySeniorId(String seniorId);

}
