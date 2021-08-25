package com.nutcache.challenge_people_management.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class MessageResponseDTO {

    private String message;
    private int status;

        public MessageResponseDTO createMessageResponse(Long id, String message, HttpStatus status){
        this.setMessage(message + id);
        this.status = status.value();
        return this;
    }
}
