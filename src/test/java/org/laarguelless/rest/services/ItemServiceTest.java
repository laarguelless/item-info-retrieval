package org.laarguelless.rest.services;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;
import org.laarguelless.db.JdbiRepository;
import org.laarguelless.domain.ImmutableChild;
import org.laarguelless.domain.ImmutableItem;
import org.laarguelless.domain.Item;
import org.laarguelless.rest.RestRepository;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    @Test
    void getItemIdRest() {
        String id = "";
        RestRepository restRepository = mock(RestRepository.class);
        JdbiRepository jdbiRepository = mock(JdbiRepository.class);
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

        when(jdbiRepository.getById(id)).thenReturn(Optional.empty());
        when(restRepository.getById(id)).thenReturn(Optional.of(item));

        ItemService itemService = new ItemService(restRepository, jdbiRepository);
        itemService.getItemId(id);
        verify(jdbiRepository).getById(id);
        verify(restRepository).getById(id);
        verify(jdbiRepository).save(item);
    }

    @Test
    void getItemIdDB() {
        String id = "";
        RestRepository restRepository = mock(RestRepository.class);
        JdbiRepository jdbiRepository = mock(JdbiRepository.class);
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

        when(jdbiRepository.getById(id)).thenReturn(Optional.of(item));

        ItemService itemService = new ItemService(restRepository, jdbiRepository);
        itemService.getItemId(id);
        verify(jdbiRepository).getById(id);
        verify(jdbiRepository,times(0)).save(item);
        verify(restRepository,times(0)).getById(id);
    }
}