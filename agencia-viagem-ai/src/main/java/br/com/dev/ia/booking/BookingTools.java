package br.com.dev.ia.booking;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Tool("Cancela uma reserva existente com base no seu ID da reserva (bookingId). O usuário deve estar autenticado.")
    public String cancelBooking(long bookingId) {
        return bookingService.cancelBooking(bookingId)
                .map(booking -> "Reserva " + booking.id() + " cancelada com sucesso.")
                .orElse("Não foi possível cancelar a reserva. Verifique se o ID da reserva está correto e se você tem permissão.");
    }

    @Tool("Lista os pacotes de viagem disponíveis para uma determinada categoria (ex: ADVENTURE. TREASURES).")
    public String listPackagesByCategory(Category category) {
        List<Booking> packages = bookingService.findPackagesByCategory(category);
        if(packages.isEmpty()){
            return "Nenhum pacote encontrado para a categoria: " + category;
        }
        return "Pacotes encontrados para a categoria '" + category + "': " + packages.stream()
                .map(Booking::destination)
                .toList().toString();
    }
}
