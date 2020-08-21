package org.laarguelless.rest;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;
import org.laarguelless.domain.Item;
import org.laarguelless.rest.clients.ChildrenRestClient;
import org.laarguelless.rest.clients.ItemRestClient;
import org.laarguelless.rest.clients.dto.ChildDto;
import org.laarguelless.rest.clients.dto.ItemDto;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RestRepositoryImplTest {

    @Test
    void getById() {
        String id = "id";
        ItemRestClient itemRestClient = mock(ItemRestClient.class);
        ChildrenRestClient childrenRestClient = mock(ChildrenRestClient.class);
        Item expected = mock(Item.class);
        ItemDto dto = mock(ItemDto.class);;
        List<ChildDto> children = List.of(mock(ChildDto.class));

        when(childrenRestClient.getById(id)).thenReturn(children);
        when(itemRestClient.getById(id)).thenReturn(Optional.of(dto));
        when(dto.toDomain(children)).thenReturn(expected);

        assertEquals(Optional.of(expected),new RestRepositoryImpl(itemRestClient,childrenRestClient).getById(id));

    }
}