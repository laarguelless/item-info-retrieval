package org.laarguelless.json;

import java.lang.reflect.Type;

public class SerializerImpl<T> implements Serializer<T>{

    private final Type typeOf;

    public SerializerImpl(Class<T> typeOf) {
        this.typeOf = typeOf;
    }

    @Override
    public T fromJson(String json) {
        return GsonFactory.gson().fromJson(json,typeOf);
    }
}
