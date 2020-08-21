package org.laarguelless.rest.response;

import org.immutables.value.Value;
import org.laarguelless.domain.Child;

import java.time.OffsetDateTime;

@Value.Immutable
@Value.Style(strictBuilder = true)
public abstract class ChildResponse {

    public abstract String itemId();
    public abstract OffsetDateTime stopTime();

    public ChildResponse fromDomain(Child child){
        return ImmutableChildResponse.builder()
                .itemId(child.itemId())
                .stopTime(child.stopTime())
                .build();
    }
}
