package com.bikkadit.electronicstore.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category  {
    @Id
    private String categoryId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    private String coverImage;
}
