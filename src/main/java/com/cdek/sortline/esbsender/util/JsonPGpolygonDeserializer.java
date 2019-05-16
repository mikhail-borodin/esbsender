package com.cdek.sortline.esbsender.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.postgresql.geometric.PGpoint;
import org.postgresql.geometric.PGpolygon;

import java.io.IOException;

public class JsonPGpolygonDeserializer extends JsonDeserializer<PGpolygon> {
    @Override
    public PGpolygon deserialize(JsonParser jp, DeserializationContext cTxt) throws IOException {
        Double[][] source = jp.readValueAs(Double[][].class);
        if (source.length == 0) {
            return null;
        }

        PGpoint points[] = new PGpoint[source.length];
        for (int i = 0; i < source.length; i++) {
            Double[] point = source[i];
            points[i] = new PGpoint(point[0], point[1]);
        }

        return new PGpolygon(points);
    }

}
