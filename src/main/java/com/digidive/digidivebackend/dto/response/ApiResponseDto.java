package com.digidive.digidivebackend.dto.response;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseDto<T> {
    private boolean isSuccess;
    private String message;
    T response;
}
