package org.laarguelless.rest.response;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;
import org.laarguelless.db.dao.ImmutableChildDao;
import org.laarguelless.domain.ImmutableChild;
import org.laarguelless.domain.ImmutableItem;
import org.laarguelless.json.GsonBuilderFactory;

import java.math.BigInteger;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ItemResponseTest {

    @Test
    void of() {
        ImmutableItem item = ImmutableItem.builder().id("MLU460998489")
                .categoryId("MLU1055")
                .title("Google Pixel 32gb Silver - Impecable!")
                .price(BigInteger.valueOf(350L))
                .startTime(OffsetDateTime.parse("2019-03-02T20:31:02Z"))
                .stopTime(OffsetDateTime.parse("2019-10-25T23:28:35Z"))
                .children(List.of(
                        ImmutableChild.builder()
                                .itemId("MLU468887129")
                                .stopTime(OffsetDateTime.parse("2020-04-25T22:10:52Z"))
                                .build()
                        )
                ).build();
        ImmutableItemResponse expected = ImmutableItemResponse.builder()
                .id("MLU460998489")
                .categoryId("MLU1055")
                .title("Google Pixel 32gb Silver - Impecable!")
                .price(BigInteger.valueOf(350L))
                .startTime(OffsetDateTime.parse("2019-03-02T20:31:02Z"))
                .stopTime(OffsetDateTime.parse("2019-10-25T23:28:35Z"))
                .children(List.of(
                        ImmutableChildResponse.builder()
                                .itemId("MLU468887129")
                                .stopTime(OffsetDateTime.parse("2020-04-25T22:10:52Z"))
                                .build()
                        )
                )
                .build();
        assertEquals(expected,ItemResponse.of(item));
    }

    @Test
    void serialize(){
        ImmutableItemResponse response = ImmutableItemResponse.builder()
                .id("MLU460998489")
                .categoryId("MLU1055")
                .title("Google Pixel 32gb Silver - Impecable!")
                .price(BigInteger.valueOf(350L))
                .startTime(OffsetDateTime.parse("2019-03-02T20:31:02Z"))
                .stopTime(OffsetDateTime.parse("2019-10-25T23:28:35Z"))
                .children(List.of(
                        ImmutableChildResponse.builder()
                                .itemId("MLU468887129")
                                .stopTime(OffsetDateTime.parse("2020-04-25T22:10:52Z"))
                                .build()
                        )
                )
                .build();
        String json = "{\"id\":\"MLU460998489\",\"title\":\"Google Pixel 32gb Silver - Impecable!\",\"categoryId\":\"MLU1055\",\"price\":350,\"startTime\":\"2019-03-02T20:31:02Z\",\"stopTime\":\"2019-10-25T23:28:35Z\",\"children\":[{\"itemId\":\"MLU468887129\",\"stopTime\":\"2020-04-25T22:10:52Z\"}]}";
        assertEquals(json, GsonBuilderFactory.GSON.toJson(response));
    }
}