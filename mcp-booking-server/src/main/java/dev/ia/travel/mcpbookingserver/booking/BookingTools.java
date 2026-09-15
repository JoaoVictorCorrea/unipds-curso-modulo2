package dev.ia.travel.mcpbookingserver.booking;

import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingTools {

    private final BookingService bookingService;

    public BookingTools(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @McpTool(name = "getBookingDetails", description = "Obtém os detalhes completos de uma reserva com base em seu número de identificação (bookingId).")
    public String getBookingDetails(@ToolParam(description = "O ID numérico único da reserva (ex: 12345)") long bookingId){
        return bookingService.getBookingDetails(bookingId)
                .map(Booking::toString)
                .orElse("Reserva com ID " + bookingId + " não encontrada.");
    }

    @McpTool(name = "cancelBooking", description = "Cancela uma reserva existente com base no seu ID da reserva (bookingId). O usuário deve estar autenticado.")
    public String cancelBooking(@ToolParam(description = "ID da reserva a cancelar") long bookingId,
                                @ToolParam(description = "Usuário que está tentando cancelar a reserva") String name) {
        return bookingService.cancelBooking(bookingId, name)
                .map(booking -> "Reserva " + booking.id() + " cancelada com sucesso.")
                .orElse("Não foi possível cancelar a reserva. Verifique se o ID da reserva está correto e se você tem permissão.");
    }

    @McpTool(name = "listPackagesByCategory", description = "Lista os pacotes de viagem disponíveis para uma determinada categoria (ex: ADVENTURE. TREASURES).")
    public String listPackagesByCategory(@ToolParam(description = "Categoria utilizada como filtro para pacotes") Category category) {
        List<Booking> packages = bookingService.findPackagesByCategory(category);
        if(packages.isEmpty()){
            return "Nenhum pacote encontrado para a categoria: " + category;
        }
        return "Pacotes encontrados para a categoria '" + category + "': " + packages.stream()
                .map(Booking::destination)
                .toList().toString();
    }
}
