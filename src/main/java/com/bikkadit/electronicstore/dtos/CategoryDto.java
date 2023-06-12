package com.bikkadit.electronicstore.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryDto {
    private String categoryId;

    @NotBlank
    @Size(min = 5, max = 30, message = "Invalid title : Must be of 5 - 30 characters")
    private String title;

    @NotBlank
    @Size(min = 5, max = 30, message = "Invalid description : Must be of 5 - 30 characters")
    @NotNull
    private String description;


    private String coverImage;
}


