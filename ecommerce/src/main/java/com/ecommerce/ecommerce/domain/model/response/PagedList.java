package com.ecommerce.ecommerce.domain.model.response;

import lombok.Data;

import java.util.List;

@Data
public class PagedList<T> {
    private int page;
    private int size;
    private int total;
    private List<T> data;

    public PagedList() {
    }

    public PagedList(int page, int size, List<T> data) {
        this.page = page;
        this.size = size;
        this.total = data.size();
        this.data = data.stream().skip((long) (page - 1) * size).limit(size).toList();
    }
}

