package com.school.copyManagement.utils;

import com.school.copyManagement.dto.response.ResponseHandler;
import org.springframework.http.ResponseEntity;

public class Utils {

    // CHECK STRING VALUES
    public static void checkStringValues(String value, String valueName) throws Exception {

        if (value == null || value.equals("")) {
            throw new Exception(valueName + " can't be null or empty string !");
        }
    }

    // EXCEPTION CATCHING
    public static ResponseEntity<Object> catchException(Exception e) {
        e.printStackTrace();
        return ResponseHandler.generateError(e);
    }
}
