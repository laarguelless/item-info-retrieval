package org.laarguelless.json;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import java.time.OffsetDateTime;

public class GsonBuilderFactory {

    public final static GsonBuilder GSON_BUILDER= new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(OffsetDateTime .class, new DateTimeTypeAdapter())
            .setLenient();
}
