package com.blue1.challenge;

import com.blue1.challenge.seniorhandler.Senior;
import com.blue1.challenge.seniorhandler.SeniorRepository;
import com.blue1.challenge.sensorHandler.Sensor;
import com.blue1.challenge.sensorHandler.SensorRepository;
import com.blue1.challenge.homehandler.Home;
import com.blue1.challenge.homehandler.HomeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@SuppressWarnings({"all"})
class FillMongoDb {

    private static final Logger log = LoggerFactory.getLogger(FillMongoDb.class);

    @Bean
    CommandLineRunner initHomeRepo(HomeRepository homerepository, SensorRepository sensorrepository, SeniorRepository seniorrepository) {
        homerepository.deleteAll();
        sensorrepository.deleteAll();
        seniorrepository.deleteAll();

        Home home1 = homerepository.save(new Home("home1", "NURSING"));
        Home home2 = homerepository.save(new Home("home2", "PRIVATE"));


        Sensor sensor1 = sensorrepository.save(new Sensor("v1.0", "v1.0"));
        Sensor sensor2 = sensorrepository.save(new Sensor("v2.0", "v2.0"));

//        Senior senior1 = seniorrepository.save(new Senior("home1","senior1","s1"));
//        Senior senior2 = seniorrepository.save(new Senior("home2",true,"s2","name2"));
        Senior senior3 = seniorrepository.save(new Senior("home1",false,"George"));

        return args -> {
            log.info("Presaving " + home1);
            log.info("Presaving " + home2);
            log.info("Presaving " + sensor1);
            log.info("Presaving " + sensor2);
//            log.info("Presaving " + senior1);
//            log.info("Presaving " + senior2);
            log.info("Presaving " + senior3);


        };
    }

//    @Bean
//    CommandLineRunner initSensorRepo(SensorRepository repository) {
//
//        return args -> {
//            repository.deleteAll();
//            log.info("Preloading " + repository.save(new Sensor("hw1", "sf1")));
//            log.info("Preloading " + repository.save(new Sensor("hw2", "sf2")));
//        };
//    }
//    @Bean
//    CommandLineRunner initSeniorRepo(SeniorRepository repository) {
//
//        return args -> {
//            repository.deleteAll();
//            log.info("Preloading " + repository.save(new Senior("homoeid_qweqweqwe",true "sf1")));
//            log.info("Preloading " + repository.save(new Senior("hw2", "sf2")));
//        };
//    }

}

@Configuration
class MongoValidationConfig {

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener(LocalValidatorFactoryBean factory) {
        return new ValidatingMongoEventListener(factory);
    }
}