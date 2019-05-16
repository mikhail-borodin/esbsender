package com.cdek.sortline.esbsender.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.postgresql.geometric.PGpoint;
import org.postgresql.geometric.PGpolygon;

import java.io.IOException;

public class JsonPGpolygonSerializer extends JsonSerializer<PGpolygon>
{
    public JsonPGpolygonSerializer() {
    }

    @Override
    public void serialize(PGpolygon value, JsonGenerator jgen,
                          SerializerProvider serializers) throws IOException,
            JsonProcessingException {

        jgen.writeStartArray();

        if (value != null) {
            for (PGpoint point : value.points) {
                jgen.writeStartArray();

                jgen.writeNumber(point.x);
                jgen.writeNumber(point.y);

                jgen.writeEndArray();
            }
        }

        jgen.writeEndArray();
    }

}

