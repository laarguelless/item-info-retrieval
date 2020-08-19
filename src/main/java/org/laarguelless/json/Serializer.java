package org.laarguelless.json;

public interface Serializer<T> {

    default String toJson(T t) {
        return GsonFactory.gson().toJson(t);
    }

    T fromJson(String json);
}
