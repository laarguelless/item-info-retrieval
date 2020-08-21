package org.laarguelless.domain;

import io.vavr.collection.List;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.math.BigInteger;
import java.time.OffsetDateTime;

@Gson.TypeAdapters
@Value.Immutable
public interface Item {

    String id();
    String title();
    String categoryId();
    BigInteger price();
    OffsetDateTime startTime();
    OffsetDateTime stopTime();
    List<Child> children();

}
