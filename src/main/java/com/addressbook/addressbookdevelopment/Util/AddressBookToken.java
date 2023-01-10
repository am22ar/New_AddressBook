package com.addressbook.addressbookdevelopment.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;

//initializing a class to create and decode tokens
@Component
public class AddressBookToken {

    //used to declare a secret message
    private static final String TokenMessage="Hello !!!";

    //method to create a token with id as parameter
    public String createToken(Integer personId){
        Algorithm algorithm = Algorithm.HMAC256(TokenMessage);
        String token = JWT.create().withClaim("personId_Key",personId).sign(algorithm);
        return token;
    }

    public Integer decodeToken(String token) throws SignatureVerificationException{
        Verification verification = JWT.require(Algorithm.HMAC256(TokenMessage));
        JWTVerifier jwtVerifier = verification.build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        Claim idClaim = decodedJWT.getClaim("personId_Key");
        Integer personId= idClaim.asInt();
        return personId;
    }
}
