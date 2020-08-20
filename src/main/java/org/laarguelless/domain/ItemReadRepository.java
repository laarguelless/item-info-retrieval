package org.laarguelless.domain;

import java.util.Optional;

public interface ItemReadRepository {

    Optional<Item> getById(String itemId);
}
