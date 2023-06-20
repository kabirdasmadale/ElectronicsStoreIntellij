package com.bikkadit.electronicstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class Product {
    private String productId;
    private String title;
    private String descripsition;
    private int price;
    private int discountPrice;
    private String quantity;
    private Date addedDate;
    private boolean live;
    private boolean stotck;

}
