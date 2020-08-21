package org.laarguelless.rest.clients.dto;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;
import org.laarguelless.domain.ImmutableChild;
import org.laarguelless.domain.ImmutableItem;

import java.math.BigInteger;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ItemDtoTest {

    @Test
    void fromDto() {
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
        ImmutableItemDto dto = ImmutableItemDto.builder()
                .id("MLU460998489")
                .categoryId("MLU1055")
                .title("Google Pixel 32gb Silver - Impecable!")
                .price(BigInteger.valueOf(350L))
                .startTime(OffsetDateTime.parse("2019-03-02T20:31:02Z"))
                .stopTime(OffsetDateTime.parse("2019-10-25T23:28:35Z")).build();
        List<ChildDto> children = List.of(ImmutableChildDto.builder()
                .id("MLU468887129")
                .stopTime(OffsetDateTime.parse("2020-04-25T22:10:52Z"))
                .build());
        assertEquals(expected,dto.toDomain(children));
    }
}