package soo.receipt_writer.pages;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Objects;

public record PageParams(
        @NonNull @Max(9999) Integer year,
        @NonNull @Min(1) @Max(12) Integer month,
        @NonNull String page,
        @NonNull String size,
        @NonNull ParamsOrder sort
) {
    public PageParams(Integer year, Integer month, String page, String size, ParamsOrder sort) {
        this.year = Objects.requireNonNullElse(year, LocalDateTime.now().getYear());
        this.month = Objects.requireNonNullElse(month, LocalDateTime.now().getMonthValue());
        this.page = Objects.requireNonNullElse(page, "0");
        this.size = Objects.requireNonNullElse(size, "20");
        this.sort = Objects.requireNonNullElse(sort, ParamsOrder.DESC);
    }
}
