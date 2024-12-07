package common.dto.result;

import common.consts.SASuccess;

public class Result<T> {
	private final boolean success;
	private final T data;
    private final String message;
    
    private Result(boolean success, T data, String msg) {
        this.success = success;
        this.data = data;
        this.message = msg;
    }
    
    public static <T> Result<T> success(T data) {
        return new Result<>(true, data, SASuccess.GENERIC);
    }
    
    public static <T> Result<T> failure(String errorMessage) {
        return new Result<>(false, null, errorMessage);
    }
    
    public boolean isSuccess() {
        return this.success;
    }

    public T getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }
    
}
