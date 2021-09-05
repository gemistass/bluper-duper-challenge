package com.blue1.challenge.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/*
Filtering every request and processing the access_token.
Not verified signature responds with FORBIDDEN status code, expired access token with REQUEST_TIMEOUT,
no token provided responds with UNAUTHORIZED code.
*/

@Slf4j
@Component
//@Order(1)
public class TokenAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Verify the access token or throw the corresponding exceptions at failure
        if (request.getServletPath().equals("/security/tokenplease")) {
            filterChain.doFilter(request, response);
            return;
        } else {
            String token = request.getHeader("token");
            if (token != null) {
                try {
                    log.info("Token from client received: {} ", token);
                    Algorithm unsignedAlg = Algorithm.HMAC256("backendChallengeJWTKey".getBytes());
                    JWTVerifier jwtVerifier = JWT.require(unsignedAlg).build();
                    DecodedJWT decodedJWT = jwtVerifier.verify(token);
                    Map<String, Claim> a = decodedJWT.getClaims();
                    Claim claim = decodedJWT.getClaim("username");
                    if (!claim.asString().equals("backend-SW-Engineer-Candidate"))
                        throw new Exception(claim.asString()+" is not authorized");
                    log.info("Token verified!");
                    int breakpoint = 0;


                } catch (SignatureVerificationException ex) {
                    log.info(ex.getMessage());
                    response.setHeader("error", ex.getMessage());
                    if (!response.isCommitted())
//                        response.setStatus(403);
                        response.sendError(HttpStatus.FORBIDDEN.value());//status code 403
                } catch (TokenExpiredException ex) {
                    log.info(ex.getMessage());
                    response.setHeader("error", ex.getMessage());

                    if (!response.isCommitted())
//                    response.setStatus(408);
                        response.sendError(HttpStatus.REQUEST_TIMEOUT.value());//status code 408
                } catch (Exception ex) {
                    log.info(ex.getMessage());

                    response.setHeader("error", ex.getMessage());
//                    response.setStatus(500);
                    if (!response.isCommitted())
                        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());//status code 403
                }
            } else {
                log.info("No token provided!");
                response.setHeader("error", "No token provided");
//                response.setStatus(401);
                if (!response.isCommitted())
                    response.sendError(HttpStatus.UNAUTHORIZED.value());//status code 403
            }
        }
        filterChain.doFilter(request, response);
        return;
    }
}
