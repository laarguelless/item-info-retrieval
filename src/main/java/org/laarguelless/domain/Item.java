package org.laarguelless.domain;

import io.vavr.collection.List;
import org.immutables.value.Value;

import java.math.BigInteger;
import java.time.OffsetDateTime;

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
