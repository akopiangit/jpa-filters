package ru.susanoo.jpa_filters.model;

import java.util.List;

public class SearchDTO {
    private int pageSize;
    private int pageNumber;
    private List<FilterPackageDTO> filters;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<FilterPackageDTO> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterPackageDTO> filters) {
        this.filters = filters;
    }

}

