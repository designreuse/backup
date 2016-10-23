package com.nvidia.cosmos.cloud.dtos.error;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bprasad
 */
public class ValidationErrorDTO {

    private List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();

    public ValidationErrorDTO() {

    }

    public void addFieldError(String field, String message) {
        FieldErrorDTO fieldError = new FieldErrorDTO(field, message);
        fieldErrors.add(fieldError);
    }

    public List<FieldErrorDTO> getFieldErrors() {
        return fieldErrors;
    }
}
