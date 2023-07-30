package com.bikkadit.electronicstore.helper;

import com.bikkadit.electronicstore.dtos.PageableResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Helper {

    public static <T, E> PageableResponse<T> getPageableResponse(Page<E> page, Class<T> dtoClass) {
        Page<T> dtoPage = page.map(entity -> new ModelMapper().map(entity,dtoClass));
        return PageableResponse.<T>builder()
                .content(dtoPage.getContent())
                .pageNumber(dtoPage.getNumber())
                .pageSize(dtoPage.getSize())
                .totalElement(dtoPage.getTotalElements())
                .totalPages(dtoPage.getTotalPages())
                .lastPage(dtoPage.isLast()).build();
    }
}