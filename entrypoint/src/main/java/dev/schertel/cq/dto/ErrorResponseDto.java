package dev.schertel.cq.dto;

import java.time.LocalDateTime;

public class ErrorResponseDto {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;

    private ErrorResponseDto(Builder builder) {
        this.timestamp = builder.timestamp;
        this.status = builder.status;
        this.error = builder.error;
        this.message = builder.message;
    }

    public static Builder builder() {
        return Builder.newInstance();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public static class Builder {
        private LocalDateTime timestamp;
        private Integer status;
        private String error;
        private String message;

        private Builder() {
        }

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder withTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withStatus(Integer status) {
            this.status = status;
            return this;
        }

        public Builder withError(String error) {
            this.error = error;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponseDto build() {
            return new ErrorResponseDto(this);
        }
    }
}
