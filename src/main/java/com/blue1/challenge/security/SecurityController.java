package com.blue1.challenge.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


/*
A new endpoint should be created that returns a json web token.
Token create upon Get request on new endpoint /security/tokenplease
*/

@Controller
@RequestMapping("/security")
@Slf4j
public class SecurityController {
    private static final String USERNAME = "backend-SW-Engineer-Candidate";



    @SneakyThrows
    @GetMapping("/tokenplease")
    ResponseEntity<String> giveToken(HttpServletRequest httpServletRequest, HttpServletResponse httpservletResponse) {


        //Create and sign with the private key, expires in 60 minutes, and includes the username in the payload
        Algorithm signAlg = Algorithm.HMAC256("backendChallengeJWTKey".getBytes());
        String access_jwtToken = JWT.create().
                withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .withClaim("username", USERNAME)


                .withIssuer(httpServletRequest.getRequestURL().toString())
                .sign(signAlg);
        log.info("Access JWT token is  " + access_jwtToken);

        httpservletResponse.addHeader("access_token", access_jwtToken);
//        new ObjectMapper().writeValue(httpservletResponse.getOutputStream(), new HashMap<>().put("access_token",access_jwtToken));

       
        String body =   "{\n" +
                "    \"status\": "+HttpStatus.CREATED+",\n" +
                "    \"token\": \""+access_jwtToken+"\",\n" +
                "    \"path\": \""+httpServletRequest.getServletPath().toString()+"\"\n" +
                "}";
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);

    }

}
