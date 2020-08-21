package org.laarguelless.domain;

import org.immutables.value.Value;

import java.time.OffsetDateTime;

@Value.Immutable
public interface Child {
    String itemId();
    OffsetDateTime stopTime();

}
