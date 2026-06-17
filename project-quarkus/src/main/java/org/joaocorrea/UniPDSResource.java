package org.joaocorrea;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/unipds")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class UniPDSResource {

    private int i = 0;

    @GET
    public int getI() {
        return i;
    }

    @POST
    public void addI() {
        i++;
    }

    @DELETE
    public void removeI() {
        i--;
    }

    @PUT
    public void setI(int i) {
        this.i = i;
    }
}
