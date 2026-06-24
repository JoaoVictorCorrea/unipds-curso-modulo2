package org.joaocorrea;

import io.micrometer.core.annotation.Counted;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @GET
    @Counted(value = "counted.getPerson")
    public List<Person> getPerson(){
        return Person.listAll();
    }

    @POST
    @Transactional
    public Person createPerson(Person person){
        person.id = null;
        person.persist();
        return person;
    }

    @PUT
    @Transactional
    public Person updatePerson(Person person){
        Person p = Person.findById(person.id);
        p.name = person.name;
        p.yearOfBirth = person.yearOfBirth;
        p.persist();
        return p;
    }

    @DELETE
    @Transactional
    public void deletePerson(int id){
        Person.deleteById(id);
    }

    @GET
    @Path("findByYearOfBirth")
    public List<Person> getPersonByYearOfBirth(@QueryParam("yearOfBirth") int yearOfBirth){
        return Person.findByYearOfBirth(yearOfBirth);
    }
}
