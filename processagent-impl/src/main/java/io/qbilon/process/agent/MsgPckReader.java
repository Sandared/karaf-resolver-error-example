package io.qbilon.process.agent;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import org.msgpack.jackson.dataformat.MessagePackFactory;
import org.osgi.service.component.annotations.Component;

/**
 * MsgPckReaderWriter
 */
@Component(property = { "osgi.jaxrs.extension=true", "osgi.jaxrs.media.type=application/msgpack" })
public class MsgPckReader implements MessageBodyReader {

    // Instantiate ObjectMapper for MessagePack
    private ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());

    // Mediatype this reader is usable for
    private static final MediaType msgPackMediaType = new MediaType("application", "msgpack");

    @Override
    public boolean isReadable(Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return mediaType.isCompatible(msgPackMediaType); 
    }

    @Override
    public Object readFrom(Class type, Type genericType, Annotation[] annotations, MediaType mediaType,
            MultivaluedMap httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        Object result;
        try {
            if(null != genericType){
                // use the generic java type to decode the message
                JavaType genericJavaType =TypeFactory.defaultInstance().constructType(genericType);
                result = objectMapper.readValue(entityStream, genericJavaType);
            } else {
                // we're using the plain type
                result = objectMapper.readValue(entityStream, type);
            }
        } catch (Exception e) {
            throw new IOException("Error during parsing of msgpack message",e);
        }
        return result;
    }
}