package com.bikkadit.electronicstore.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtos {

    private String productId;
    @NotBlank(message = "Invalid product title: Empty product title")
    @NotNull(message = "Invalid product title: product title is NULL")
    @Size(min = 3, max = 30, message = "Invalid product title: Must be of 3 - 30 characters")
    private String title;

    @NotBlank(message = "Invalid product descripsition: Empty product descripsition")
    @NotNull(message = "Invalid product descripsition: product descripsition is NULL")
    @Size(min = 3, max = 30, message = "Invalid product descripsition: Must be of 3 - 30 characters")
    private String descripsition;

    @Size(max =9999,message = "price is not in the range..")
    private int price;
    private int discountPrice;
    private String quantity;
    private Date addedDate;
    private boolean live;
    private boolean stotck;

}
