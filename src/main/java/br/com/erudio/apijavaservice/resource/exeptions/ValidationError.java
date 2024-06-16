package br.com.erudio.apijavaservice.resource.exeptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StanderdError {
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> fieldMessages = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
        this.fieldMessages = new ArrayList<>();
    }

    public ValidationError(List<FieldMessage> fieldMessages) {
        this.fieldMessages = fieldMessages;
    }

    public ValidationError() {
    }

    public List<FieldMessage> getFieldMessages() {
        return fieldMessages;
    }

    public void addError(String fieldName, String message) {
        this.fieldMessages.add(new FieldMessage(fieldName, message));
    }
}
