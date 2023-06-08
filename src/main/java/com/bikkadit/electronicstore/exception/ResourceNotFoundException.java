package com.bikkadit.electronicstore.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ResourceNotFoundException  extends RuntimeException {

    public ResourceNotFoundException() {
        super("resorse not found with given Id");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}


