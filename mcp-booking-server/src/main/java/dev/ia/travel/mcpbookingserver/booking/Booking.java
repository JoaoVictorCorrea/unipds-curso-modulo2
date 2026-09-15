package dev.ia.travel.mcpbookingserver.booking;

import java.time.LocalDate;

public record Booking(
        Long id,
        String customerName,
        String destination,
        LocalDate startDate,
        LocalDate endData,
        BookingStatus status,
        Category category
) {}
