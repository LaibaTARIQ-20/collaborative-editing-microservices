package com.user.authentication.user_service.dto.response;

import com.user.authentication.user_service.util.PEStatusConstants;
import org.springframework.http.HttpStatus;

public abstract class BaseResponse {
    public BaseResponseDTO response() {
        return response(PEStatusConstants.STATUS_CODE_SUCCESS, PEStatusConstants.STATUS_MSG_SUCCESS, null);
    }
    public <R> BaseResponseDTO response(R data) {
        return response(PEStatusConstants.STATUS_CODE_SUCCESS, PEStatusConstants.STATUS_MSG_SUCCESS, data);
    }
    public BaseResponseDTO response(int code, String value) {
        return response(code, value, null);
    }

    public <R> BaseResponseDTO response(int code, String value, R data) {
        BaseResponseDTO<R> baseResponseDTO = new BaseResponseDTO<R>();
        baseResponseDTO.setCode(code);
        baseResponseDTO.setValue(value);
        baseResponseDTO.setData(data);

        return baseResponseDTO;
    }
    public <R> BaseResponseDTO response(HttpStatus code, String value, R data) {
        BaseResponseDTO<R> baseResponseDTO = new BaseResponseDTO<R>();
        baseResponseDTO.setCode(code.value());
        baseResponseDTO.setValue(value);
        baseResponseDTO.setData(data);

        return baseResponseDTO;
    }
    public BaseResponseDTO response(HttpStatus code, String value) {
        return response(code, value, null);
    }
   


}
