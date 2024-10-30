package com.ecommerce.exceptions;

public class ResourceNotFoundException extends  RuntimeException {
    String resourceName;
    String fieldName;
    String Field;
    Long fieldId;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String resourceName, String fieldName, String field) {
        super(String.format("%s not found with %s: %s",resourceName ,fieldName ,field ));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.Field = field;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldId) {
        super(String.format("%s not found with %s: %s",resourceName , fieldName , fieldId));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.Field = fieldName;
    }
}
