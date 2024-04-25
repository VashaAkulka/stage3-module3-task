package com.mjc.school.service.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NO_SUCH_AUTHOR("201", "NO SUCH AUTHOR"),
    NO_SUCH_NEWS("202", "NO SUCH NEWS"),
    NO_SUCH_TAG("203", "NO SUCH TAG"),
    EMPTY_FIELD("204", "EMPTY FIELD"),
    FIELD_TITLE_INVALID_LENGTH("205", "INVALID LENGTH TITLE"),
    FIELD_CONTENT_INVALID_LENGTH("206", "INVALID LENGTH CONTENT"),
    FIELD_NAME_INVALID_LENGTH("207", "INVALID LENGTH NAME");

    private final String errorCode;
    private final String errorMessage;

    public String getErrorData() {
        return "CODE: " +
                errorCode +
                ", MESSAGE: " +
                errorMessage;
    }
}
