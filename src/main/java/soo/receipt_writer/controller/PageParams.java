package soo.receipt_writer.controller;

import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.Objects;

public record PageParams(
        @Nullable String month,
        @Nullable String page,
        @Nullable String size,
        @Nullable ParamsOrder sort
) {
    public PageParams(String month, String page, String size, ParamsOrder sort) {
        this.month = Objects.requireNonNullElse(month, String.valueOf(LocalDateTime.now().getMonthValue()));
        this.page = Objects.requireNonNullElse(page, "0");
        this.size = Objects.requireNonNullElse(size, "20");
        this.sort = Objects.requireNonNullElse(sort, ParamsOrder.DESC);
    }
}
