package org.laarguelless.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.OffsetDateTime;

class DateTimeTypeAdapter extends TypeAdapter<OffsetDateTime> {
    public OffsetDateTime read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
        String dateTimeString = reader.nextString();
        return DateTimeParser.parse(dateTimeString);
    }

    public void write(JsonWriter writer, OffsetDateTime value) throws IOException {
        if (value == null) {
            writer.nullValue();
            return;
        }
        writer.value(DateTimeParser.format(value));
    }
}
