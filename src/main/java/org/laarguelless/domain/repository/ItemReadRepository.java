package org.laarguelless.domain.repository;

import org.laarguelless.domain.Item;

import java.util.Optional;

public interface ItemReadRepository {

    Optional<Item> getById(String itemId);
}
