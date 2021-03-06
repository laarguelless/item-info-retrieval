package org.laarguelless.rest.response;

import io.vavr.collection.List;
import org.immutables.value.Value;
import org.laarguelless.domain.Child;
import org.laarguelless.domain.Item;

import java.math.BigInteger;
import java.time.OffsetDateTime;

@Value.Immutable
public abstract class ItemResponse {

    public abstract String id();
    public abstract String title();
    public abstract String categoryId();
    public abstract BigInteger price();
    public abstract OffsetDateTime startTime();
    public abstract OffsetDateTime stopTime();
    public abstract List<ChildResponse> children();

    public static ItemResponse of(Item item){
        return ImmutableItemResponse.builder()
                .id(item.id())
                .categoryId(item.categoryId())
                .title(item.title())
                .price(item.price())
                .startTime(item.startTime())
                .stopTime(item.stopTime())
                .children(of(item.children()))
                .build();
    }

    private static List<ChildResponse> of(List<Child> children){
        return children.map(child -> ImmutableChildResponse
                .builder()
                .itemId(child.itemId())
                .stopTime(child.stopTime())
                .build());
    }
}
