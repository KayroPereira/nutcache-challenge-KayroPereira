package com.nutcache.challenge_people_management.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private Date timestamp;
    private String status;
    private String message;
    private String path;
    private List<ErrorObject> errors;
}
