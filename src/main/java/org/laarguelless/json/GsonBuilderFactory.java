package org.laarguelless.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.vavr.gson.VavrGson;
import org.laarguelless.db.dao.GsonAdaptersChildDao;
import org.laarguelless.db.dao.GsonAdaptersItemDao;
import org.laarguelless.rest.clients.dto.GsonAdaptersChildDto;
import org.laarguelless.rest.clients.dto.GsonAdaptersItemDto;

import java.time.OffsetDateTime;

public class GsonBuilderFactory {

    public final static Gson GSON = createGson();

    private static Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(OffsetDateTime.class, new DateTimeTypeAdapter())
                .registerTypeAdapterFactory(new GsonAdaptersItemDto())
                .registerTypeAdapterFactory(new GsonAdaptersChildDto())
                .registerTypeAdapterFactory(new GsonAdaptersItemDao())
                .registerTypeAdapterFactory(new GsonAdaptersChildDao())
                .setPrettyPrinting()
                .setLenient();

        VavrGson.registerAll(gsonBuilder);
        return gsonBuilder.create();
    }
}
