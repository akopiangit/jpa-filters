package ru.susanoo.jpa_filters.model;

import java.util.List;

public class FilterPackageDTO {
    private List<FilterDTO> filters;
    private List<FilterPackageDTO> filterPackage;
    private ExpressionType expressionType;

    public List<FilterDTO> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterDTO> filters) {
        this.filters = filters;
    }

    public List<FilterPackageDTO> getFilterPackage() {
        return filterPackage;
    }

    public void setFilterPackage(List<FilterPackageDTO> filterPackage) {
        this.filterPackage = filterPackage;
    }

    public ExpressionType getType() {
        return expressionType;
    }

    public void setType(ExpressionType expressionType) {
        this.expressionType = expressionType;
    }
}
