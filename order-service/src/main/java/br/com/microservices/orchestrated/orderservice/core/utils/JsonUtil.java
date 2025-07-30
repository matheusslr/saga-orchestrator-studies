package br.com.microservices.orchestrated.orderservice.core.utils;

import br.com.microservices.orchestrated.orderservice.core.document.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
public class JsonUtil {
    private final ObjectMapper objectMapper;

    public String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Error serializing object to json", e);
            return "";
        }
    }

    public Event toEvent(String json) {
        try {
            return objectMapper.readValue(json, Event.class);
        } catch (Exception e) {
            log.error("Error deserializing json to object", e);
            return null;
        }
    }
}
