package com.bikkadit.electronicstore.helper;

import lombok.*;
import org.springframework.http.HttpStatus;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {
    String message;
    boolean success;
    HttpStatus Status;
}
