package org.laarguelless.db;

import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.Test;
import org.laarguelless.db.dao.ItemDao;
import org.laarguelless.json.GsonBuilderFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class JdbiClientTest {

    @Test
    void deserialize() {
        String json = "{\"id\":\"MLU460998489\",\"title\":\"Google Pixel 32gb Silver - Impecable!\",\"category_id\":\"MLU1055\",\"price\":350,\"start_time\":\"2019-03-02T20:31:02Z\",\"stop_time\":\"2019-10-25T23:28:35Z\",\"children\":[{\"itemId\":\"MLU468887129\",\"stop_time\":\"2020-04-25T22:10:52Z\"}]}";
        Jdbi jdbi = mock(Jdbi.class);
        ItemDao dao = new JdbiClient(jdbi,GsonBuilderFactory.GSON).deserialize(json);
        assertEquals("MLU460998489",dao.id());
    }

    @Test
    void serialize() {
    }

    @Test
    void save() {
    }

    @Test
    void getById() {
    }
}