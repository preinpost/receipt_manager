package soo.receipt_writer.pages;

import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Objects;

public record PageParams(
        @NonNull String year,
        @NonNull String month,
        @NonNull String page,
        @NonNull String size,
        @NonNull ParamsOrder sort
) {
    public PageParams(String year, String month, String page, String size, ParamsOrder sort) {
        this.year = Objects.requireNonNullElse(year, String.valueOf(LocalDateTime.now().getYear()));
        this.month = Objects.requireNonNullElse(month, String.valueOf(LocalDateTime.now().getMonthValue()));
        this.page = Objects.requireNonNullElse(page, "0");
        this.size = Objects.requireNonNullElse(size, "20");
        this.sort = Objects.requireNonNullElse(sort, ParamsOrder.DESC);
    }
}
