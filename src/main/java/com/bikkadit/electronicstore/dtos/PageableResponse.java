package com.bikkadit.electronicstore.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageableResponse<T> {

    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private Long totalElement;
    private int totalPages;
    private boolean lastPage;

}
