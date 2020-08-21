package org.laarguelless.rest.clients.dto;

import com.google.gson.annotations.SerializedName;
import io.vavr.collection.List;
import org.immutables.gson.Gson;
import org.immutables.value.Value;
import org.immutables.value.internal.$processor$.meta.$GsonMirrors;
import org.laarguelless.domain.Child;
import org.laarguelless.domain.ImmutableChild;
import org.laarguelless.domain.ImmutableItem;
import org.laarguelless.domain.Item;

import java.math.BigInteger;
import java.time.OffsetDateTime;

@Gson.TypeAdapters
@Value.Immutable
public abstract class ItemDto {

    public abstract String id();
    public abstract String title();
    @SerializedName("category_id")
    public abstract String categoryId();
    public abstract BigInteger price();
    @SerializedName("start_time")
    public abstract OffsetDateTime startTime();
    @SerializedName("stop_time")
    public abstract OffsetDateTime stopTime();

    public Item toDomain(List<ChildDto> children){
        return ImmutableItem.builder()
                .id(id())
                .title(title())
                .categoryId(categoryId())
                .price(price())
                .startTime(startTime())
                .stopTime(stopTime())
                .children(childrenFromDto(children)).build();
    }

    private List<Child> childrenFromDto(List<ChildDto> children){
        return children.map(dto -> ImmutableChild.builder()
                .itemId(dto.id())
                .stopTime(dto.stopTime())
                .build()
        );
    }
}
