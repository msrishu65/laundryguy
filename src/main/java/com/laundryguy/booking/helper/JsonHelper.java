package com.laundryguy.booking.helper;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by saarthak on 18/5/15.
 */
@Component
public class JsonHelper extends JsonSerializer<Date> {

    private static final Logger logger = LogManager.getLogger(JsonHelper.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(Include.NON_NULL);
    }

    public String toJson(Object object) {
        if (object == null)
            return null;

        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            logger.error("unable to serialise object " + object, e);
            throw new RuntimeException(e);
        }
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        try {
            return (T)objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error("unable to deserialise object " + json, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        String formattedDate = dateFormat.format(date);
        gen.writeString(formattedDate);
    }
}
