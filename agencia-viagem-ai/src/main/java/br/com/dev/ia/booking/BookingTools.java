package br.com.dev.ia.booking;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class BookingTools {

    private final BookingService bookingService;

    public BookingTools(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Tool("Obtém os detalhes completos de uma reserva com base em seu número de identificação (bookingId).")
    public String getBookingDetails(long bookingId){
        return bookingService.getBookingDetails(bookingId)
                .map(Booking::toString)
                .orElse("Reserva com ID " + bookingId + " não encontrada.");
    }

    @Tool("""
            Cancela uma reserva existente.
            Para confirmar o cancelamento, é necessário fornecer o ID da reserva (bookingId)
            e o último nome do cliente (customerLastName).
            """)
    public String cancelBooking(long bookingId, String customerLastName) {
        return bookingService.cancelBooking(bookingId, customerLastName)
                .map(booking -> "Reserva " + bookingId + " cancelada com sucesso. Status atual: " + booking.status())
                .orElse("Não foi possível cancelar a reserva. Verifique se o ID da reserva e o sobrenome do cliente estão corretos.");
    }
}
