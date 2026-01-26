package com.applyjobsmartly.ajs.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel<T> {

    private T data;
    private String message;
    private int statusCode;

    // pagination (optional)
    private Integer pageSize;
    private Integer pageNumber;
    private Long totalCount;
    
    public ResponseModel(T data, String message, int statusCode,
            int pageSize, int pageNumber, Long totalCount) {
this.data = data;
this.message = message;
this.statusCode = statusCode;
this.pageSize = pageSize;
this.pageNumber = pageNumber;
this.totalCount = totalCount;
}
}
