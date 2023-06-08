package com.bikkadit.electronicstore.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageResponse {
    private String imageName;
    String message;
    boolean success;
    HttpStatus Status;
}

