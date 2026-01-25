package com.applyjobsmartly.ajs.util;

import org.springframework.http.ResponseEntity;

public class StatusCodeMapper {

    private StatusCodeMapper() {
        // utility class
    }

    public static <T> ResponseEntity<ResponseModel<T>> build(
            StatusCodeEnum status, T data) {

        ResponseModel<T> response = new ResponseModel<>(
                data,
                status.getMessage(),
                status.getCode(),
                null,
                null,
                null
        );

        return ResponseEntity.status(status.getCode()).body(response);
    }

    public static <T> ResponseEntity<ResponseModel<T>> build(
            StatusCodeEnum status,
            T data,
            int pageSize,
            int pageNumber,
            Long totalCount) {

        ResponseModel<T> response = new ResponseModel<>(
                data,
                status.getMessage(),
                status.getCode(),
                pageSize,
                pageNumber,
                totalCount
        );

        return ResponseEntity.status(status.getCode()).body(response);
    }


}
