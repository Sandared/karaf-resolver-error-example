package io.qbilon.process.agent;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.osgi.service.component.annotations.Component;

@Component(service = JavaAgentReceiver.class, property = { "osgi.jaxrs.resource=true" })
public class JavaAgentReceiver {

    @Path("/test")
    @PUT
    @Consumes("application/msgpack")
    @Produces(MediaType.TEXT_PLAIN)
    public String receiveTraces(Object something) {
        System.out.println("Hello World");
        
        return "";
    }

}