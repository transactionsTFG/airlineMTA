package common.mapper;

import common.dto.result.Result;
import common.dto.soap.response.SoapResponse;

public class SoapResponseMapper {

    private SoapResponseMapper(){}

    public static <T> SoapResponse<T> toSoapResponse(Result<T> result) {
        return new SoapResponse<>(result.isSuccess(), result.getData(), result.getMessage());
    }

    public static <T> SoapResponse<T> toSoapResponse(String msg, T data, boolean success) {
        return new SoapResponse<>(success, data, msg);
    }

}