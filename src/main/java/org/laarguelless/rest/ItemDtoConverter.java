package org.laarguelless.rest;

import io.vavr.collection.List;
import org.laarguelless.domain.Child;
import org.laarguelless.domain.Item;
import org.laarguelless.rest.dto.ChildDto;
import org.laarguelless.rest.dto.ItemDto;


public class ItemDtoConverter {

    public Item fromDto(ItemDto itemDto, List<ChildDto> children){
        return Item.builder()
                .id(itemDto.getId())
                .title(itemDto.getTitle())
                .categoryId(itemDto.getCategoryId())
                .price(itemDto.getPrice())
                .startTime(itemDto.getStartTime())
                .stopTime(itemDto.getStopTime())
                .children(fromDto(children)).build();
    }

    private List<Child> fromDto(List<ChildDto> children){
        return children.map(dto -> new Child(dto.getId(),dto.getStopTime()));
    }
}
