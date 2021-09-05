package com.blue1.challenge.sensorHandler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
@Slf4j
public class SensorController {


    @Autowired
    SensorRepository sensorRepository;

    @GetMapping("/sensors")
    CollectionModel<EntityModel<Sensor>> all(String a) {

        List<EntityModel<Sensor>> sensors = sensorRepository.findAll().stream()
                .map(sensor -> EntityModel.of(sensor,
                        linkTo(methodOn(SensorController.class).one(sensor.getSensorId())).withSelfRel(),

                        linkTo(methodOn(SensorController.class).all("A")).withRel("sensors")))
                .collect(Collectors.toList());

        return CollectionModel.of(sensors, linkTo(methodOn(SensorController.class).all("A")).withSelfRel());
    }

    @GetMapping("/sensors/{id}")
    Sensor one(@PathVariable String id) {

        return sensorRepository.findById(id).get();

    }
//    @GetMapping("/sensortest")
//    ResponseEntity test(HttpServletRequest httpServletRequest, HttpServletResponse httpservletResponse) throws IOException {
//        log.info("STATUS: "+String.valueOf(httpservletResponse.getStatus()));
//        httpservletResponse.sendError(httpservletResponse.getStatus());
//        return null;
//
//    }

    @PostMapping("/sensors")
    Sensor NewSensor(@RequestBody Sensor newSensor, HttpServletRequest httpServletRequest, HttpServletResponse httpservletResponse) {

        return sensorRepository.save(newSensor);
    }

    @DeleteMapping("/sensors/{id}")
    void deleteSensor(@PathVariable String id) { sensorRepository.deleteById(id);
    }
}
