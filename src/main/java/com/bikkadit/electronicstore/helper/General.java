package com.bikkadit.electronicstore.helper;

import com.bikkadit.electronicstore.dtos.PageableResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class General {

    public static <E, U> PageableResponse<U> getPageableResponse(Page<E> page, Class<U> type) {
        List<E> content = page.getContent();
        List<U> userData = content.stream().map((data) -> new ModelMapper().map(data, type)).collect(Collectors.toList());
        PageableResponse<U> response = new PageableResponse<U>();
        response.setContent(userData);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElement(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());
        return response;

    }
}
