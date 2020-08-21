package org.laarguelless.domain.repository;

import org.laarguelless.domain.Item;

public interface ItemWriteRepository {
    void save(Item item);
}
