package com.bikkadit.electronicstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class Product {
    @Id
    private String productId;
    @Column(name="product_title")
    private String title;
    @Column(name="product_descripsition")
    private String descripsition;
    @Column(name="product_price")
    private int price;
    @Column(name="product_discountprice")
    private int discountPrice;
    @Column(name="product_quantity")
    private String quantity;
    @Column(name="product_added_date")
    private Date addedDate;
    @Column(name="product_live")
    private boolean live;
    @Column(name="product_stock")
    private boolean stotck;

}
