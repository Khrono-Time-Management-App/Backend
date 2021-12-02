package com.khrono.app.config;

import com.google.gson.Gson;
import com.khrono.app.domain.User;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class DecodedJWT {
    public User user;
    public Object roles;
    public Object exp;

    public static DecodedJWT getDecoded(String encodedToken) throws UnsupportedEncodingException {
        String[] pieces = encodedToken.split("\\.");
        String b64payload = pieces[1];
        String jsonString = new String(Base64.decodeBase64(b64payload), StandardCharsets.UTF_8);

        return new Gson().fromJson(jsonString, DecodedJWT.class);
    }
}
