package br.com.dev.ia.booking;

import java.time.LocalDate;

public record Booking(
        Long id,
        String customerName,
        String destination,
        LocalDate startDate,
        LocalDate endData,
        BookingStatus status
) {}
