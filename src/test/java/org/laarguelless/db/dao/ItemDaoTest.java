package org.laarguelless.db.dao;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;
import org.laarguelless.domain.ImmutableChild;
import org.laarguelless.domain.ImmutableItem;

import java.math.BigInteger;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ItemDaoTest {

    @Test
    void toDomain() {
        ImmutableItem expected = ImmutableItem.builder().id("MLU460998489")
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
        ImmutableItemDao dao = ImmutableItemDao.builder()
                .id("MLU460998489")
                .categoryId("MLU1055")
                .title("Google Pixel 32gb Silver - Impecable!")
                .price(BigInteger.valueOf(350L))
                .startTime(OffsetDateTime.parse("2019-03-02T20:31:02Z"))
                .stopTime(OffsetDateTime.parse("2019-10-25T23:28:35Z"))
                .children(List.of(
                        ImmutableChildDao.builder()
                                .itemId("MLU468887129")
                                .stopTime(OffsetDateTime.parse("2020-04-25T22:10:52Z"))
                                .build()
                        )
                )
                .build();
        assertEquals(expected,dao.toDomain());
    }

    @Test
    void fromDomain() {
        ImmutableItemDao expected = ImmutableItemDao.builder()
                .id("MLU460998489")
                .categoryId("MLU1055")
                .title("Google Pixel 32gb Silver - Impecable!")
                .price(BigInteger.valueOf(350L))
                .startTime(OffsetDateTime.parse("2019-03-02T20:31:02Z"))
                .stopTime(OffsetDateTime.parse("2019-10-25T23:28:35Z"))
                .children(List.of(
                        ImmutableChildDao.builder()
                                .itemId("MLU468887129")
                                .stopTime(OffsetDateTime.parse("2020-04-25T22:10:52Z"))
                                .build()
                        )
                )
                .build();
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
        assertEquals(expected,ItemDao.fromDomain(item));
    }
}