package com.applyjobsmartly.ajs.util;

public enum StatusCodeEnum {

    SUCCESS(200, "Success"),
    CREATED(201, "Created successfully"),
    UPDATED(200, "Updated successfully"),
    DELETED(200, "Deleted successfully"),

    BAD_REQUEST(400, "Bad request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Resource not found"),

    INTERNAL_ERROR(500, "Internal server error");

    private final int code;
    private final String message;

    StatusCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
