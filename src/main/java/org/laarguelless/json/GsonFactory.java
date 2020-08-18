package org.laarguelless.json;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.OffsetDateTime;

public class GsonFactory {
    private static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(OffsetDateTime.class, new DateTimeTypeAdapter())
            .setLenient()
            .create();

    public static Gson gson(){ return GSON; }
}
