package com.rkostiuk.task.dto.response;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public record ApiErrorResponse(int httpStatus, Instant timestamp, Map<String, Object> details) {

    public static ApiErrorResponse validationFail(List<ApiValidationError> apiValidationErrors) {
        return badRequest(Map.of("validationErrors", apiValidationErrors));
    }

    public static ApiErrorResponse badRequest(Map<String, Object> details) {
        return of(HttpStatus.BAD_REQUEST.value(), details);
    }

    public static ApiErrorResponse of(int httpStatus, Map<String, Object> details) {
        return new ApiErrorResponse(httpStatus, Instant.now(), details);
    }
}
