package org.laarguelless.db.dao;

import com.google.gson.annotations.SerializedName;
import io.vavr.collection.List;
import org.immutables.gson.Gson;
import org.immutables.value.Value;
import org.laarguelless.domain.Child;
import org.laarguelless.domain.ImmutableChild;
import org.laarguelless.domain.ImmutableItem;
import org.laarguelless.domain.Item;

import java.math.BigInteger;
import java.time.OffsetDateTime;


@Gson.TypeAdapters
@Value.Immutable
public abstract class ItemDao {

    public abstract String id();
    public abstract String title();
    @SerializedName("category_id")
    public abstract String categoryId();
    public abstract BigInteger price();
    @SerializedName("start_time")
    public abstract OffsetDateTime startTime();
    @SerializedName("stop_time")
    public abstract OffsetDateTime stopTime();
    public abstract List<ChildDao> children();

    public Item toDomain(){
        return ImmutableItem.builder()
                .id(id())
                .title(title())
                .categoryId(categoryId())
                .price(price())
                .startTime(startTime())
                .stopTime(stopTime())
                .children(childrenToDomain(children()))
                .build();
    }

    public List<Child> childrenToDomain(List<ChildDao> children){
        return children.map(dao -> ImmutableChild.builder().itemId(dao.itemId()).stopTime(dao.stopTime()).build());
    }

    public static ItemDao fromDomain(Item item){
       return ImmutableItemDao
                .builder()
                .id(item.id())
                .title(item.title())
                .categoryId(item.categoryId())
                .price(item.price())
                .startTime(item.startTime())
                .stopTime(item.stopTime())
                .children(childrenFromDomain(item.children()))
                .build();
    }

    private static List<ChildDao> childrenFromDomain(List<Child> children){
        return children.map(child -> ImmutableChildDao.builder()
                .itemId(child.itemId())
                .stopTime(child.stopTime())
                .build()
        );
    }


}