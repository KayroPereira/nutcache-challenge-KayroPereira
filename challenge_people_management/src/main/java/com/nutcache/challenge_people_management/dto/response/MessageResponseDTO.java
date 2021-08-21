package com.nutcache.challenge_people_management.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponseDTO {

    private String message;

    public MessageResponseDTO createMessageResponse(Long id, String message){
        this.setMessage(message + id);
        return this;
    }
}
