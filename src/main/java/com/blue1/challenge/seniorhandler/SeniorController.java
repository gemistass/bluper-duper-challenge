package com.blue1.challenge.seniorhandler;


import com.blue1.challenge.homehandler.HomeRepository;
import com.blue1.challenge.sensorHandler.SensorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
@Slf4j
public class SeniorController {

//    private static final Logger log = LoggerFactory.getLogger(SeniorController.class);


    @Autowired
    SeniorRepository seniorRepository;
    @Autowired
    HomeRepository homeRepository;
    @Autowired
    SensorRepository sensorRepository;



    @GetMapping("/seniors")
    CollectionModel<EntityModel<Senior>> all() {
        List<EntityModel<Senior>> seniors = seniorRepository.findAll().stream()
                .map(senior -> EntityModel.of(senior
                                , linkTo(methodOn(SeniorController.class).one(senior.getSeniorId())).withSelfRel(),
                                linkTo(methodOn(SeniorController.class).all()).withRel("seniors")
                        )
                )
                .collect(Collectors.toList());

        return CollectionModel.of(seniors, linkTo(methodOn(SeniorController.class).all()).withSelfRel());
    }

    @GetMapping("/seniors/{id}")
    Senior one(@PathVariable String id) {
        return seniorRepository.findById(id).get();

    }

    @PutMapping("/assign/{sensorId}/{seniorId}")
    Object newAssignment(@PathVariable String sensorId, @PathVariable String seniorId) {
        log.info(sensorId);
        log.info(seniorId);
        log.info("Assigning sensor: {} to senior {}",sensorId,seniorId);
        List<Senior> found = seniorRepository.findBySensorId(sensorId);
        if (found.size() > 0) {
            return ResponseEntity.badRequest().body("" +
                    " {\"status\":400,\"error\":\"Bad Request. Sensor already assigned to " + found.get(0).getSeniorId()
            +"\n\"}");
        }

        if ((sensorRepository.findBySensorId(sensorId).isEmpty())){
            return ResponseEntity.badRequest().body("" +
                    " {\"status\":400,\"error\":\"Bad Request. Sensor: "+sensorId+" not found "+"\n\"}");
        }

        return seniorRepository.findBySeniorId(seniorId).stream().
                map(senior -> {
                    senior.setSensorId(sensorId);
                    return seniorRepository.save(senior);
                });



    }

    @PostMapping("/seniors")
    Object NewSenior(@RequestBody Senior newSenior) {

//        log.debug(newSenior.getHomeId());
        log.info(String.valueOf(homeRepository.existsById(newSenior.getHomeId())));
        if (!homeRepository.existsById(newSenior.getHomeId())) {
            return ResponseEntity.badRequest().body("" +
                    " {\"status\":400,\"error\":\"Bad Request. Home not Found\"}");
        }
        if ((newSenior.getSensorId() == null))
            return seniorRepository.save(newSenior);
        else
            return ResponseEntity.badRequest().body("" +
                    " {\"status\":400,\"error\":\"Bad Request. Upon senior creation, sensorId should\n" +
                    "not be defined\"}");


    }


    @DeleteMapping("/seniors/{id}")
    void deleteSenior(@PathVariable String id) {
        seniorRepository.deleteById(id);
    }
}

//@Data
class Assignment {

    private String sensorId;
    private String seniorId;

    public Assignment(String sensorId, String seniorId) {
        this.sensorId = sensorId;
        this.seniorId = seniorId;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "sensorId='" + sensorId + '\'' +
                ", seniorId='" + seniorId + '\'' +
                '}';
    }

    public String getSeniorId() {
        return seniorId;
    }

    public void setSeniorId(String seniorId) {
        this.seniorId = seniorId;
    }
}
