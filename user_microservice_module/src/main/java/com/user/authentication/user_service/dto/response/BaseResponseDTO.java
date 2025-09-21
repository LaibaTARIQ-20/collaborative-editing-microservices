package com.user.authentication.user_service.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;
@Setter
@Getter
public class BaseResponseDTO<R> {  private int code;
    private String value;
    private R data;
    private List<R> dataList;
    private int page;
    private long totalRecords;
    public BaseResponseDTO(HttpStatus status, String message, R data, int page, long totalRecords) {
        this.code = status.value();
        this.value = message;
        this.data = data;
        this.page = page;
        this.totalRecords = totalRecords;
    }
    public  BaseResponseDTO(HttpStatus status, String message, List<R> dataList){
        this.code = status.value();
        this.value = message;
        this.dataList=dataList;

    }
    public BaseResponseDTO(HttpStatus status, String message) {
        this.code = status.value();
        this.value = message;
    }
    public BaseResponseDTO(HttpStatus status, String message, R data) {
        this.code = status.value();
        this.value = message;
        this.data = data;
    }
    public BaseResponseDTO() {
    }

}
