package com.blue1.challenge.sensorHandler;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SensorRepository extends MongoRepository<Sensor, String> {

    List<Sensor> findBySensorId(String sensorId);
    List<Sensor> findBySoftwareVersion(String softwareVersion);

}
