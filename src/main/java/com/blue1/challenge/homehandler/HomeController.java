package com.blue1.challenge.homehandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Slf4j
@RequestMapping("/api")
public class HomeController {


    @Autowired
    HomeRepository homeRepository;

    @GetMapping("/homes")
    CollectionModel<EntityModel<Home>> all() {

        //return the homes along with their self link relation
        List<EntityModel<Home>> homes = homeRepository.findAll().stream()
                .map(home -> EntityModel.of(home,
                        linkTo(methodOn(HomeController.class).one(home.getHomeId())).withSelfRel(),

                        linkTo(methodOn(HomeController.class).all()).withRel("homes")))
                .collect(Collectors.toList());

        return CollectionModel.of(homes, linkTo(methodOn(HomeController.class).all()).withSelfRel());
    }

    @GetMapping("/homes/{id}")
    Home one(@PathVariable String id) {

        return homeRepository.findById(id)
                .orElseThrow(() -> new HomeNotFoundException(id));
    }


    @PostMapping("/homes")

    Home NewHome(@Valid @RequestBody   Home newHome) {
//        log.info("Bedug newHome: "+newHome);

        return homeRepository.save(newHome);
    }

    @DeleteMapping("/homes/{id}")
    void deleteHome(@PathVariable String id) {
        homeRepository.deleteById(id);
    }


}


