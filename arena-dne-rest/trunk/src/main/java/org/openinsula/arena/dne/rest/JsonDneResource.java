package org.openinsula.arena.dne.rest;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

@Component
@Path("json")
@Produces(MediaType.APPLICATION_JSON)
public class JsonDneResource extends DneResource {
	
}
