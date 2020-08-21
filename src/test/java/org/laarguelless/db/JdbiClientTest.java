package org.laarguelless.db;

import io.vavr.collection.List;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.Test;
import org.laarguelless.db.dao.ImmutableChildDao;
import org.laarguelless.db.dao.ImmutableItemDao;
import org.laarguelless.db.dao.ItemDao;
import org.laarguelless.domain.Item;
import org.laarguelless.json.GsonBuilderFactory;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class JdbiClientTest {

    @Test
    void deserialize() {
        String json = "{\"id\":\"MLU460998489\",\"title\":\"Google Pixel 32gb Silver - Impecable!\",\"category_id\":\"MLU1055\",\"price\":350,\"start_time\":\"2019-03-02T20:31:02Z\",\"stop_time\":\"2019-10-25T23:28:35Z\",\"children\":[{\"itemId\":\"MLU468887129\",\"stop_time\":\"2020-04-25T22:10:52Z\"}]}";
        Jdbi jdbi = mock(Jdbi.class);
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
                ).build();
        assertEquals(expected, new JdbiClient(jdbi, GsonBuilderFactory.GSON).deserialize(json));
    }

    @Test
    void serialize() {
        ItemDao dao = ImmutableItemDao.builder()
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
        String expected = "{\n" +
                "  \"id\": \"MLU460998489\",\n" +
                "  \"title\": \"Google Pixel 32gb Silver - Impecable!\",\n" +
                "  \"category_id\": \"MLU1055\",\n" +
                "  \"price\": 350,\n" +
                "  \"start_time\": \"2019-03-02T20:31:02Z\",\n" +
                "  \"stop_time\": \"2019-10-25T23:28:35Z\",\n" +
                "  \"children\": [\n" +
                "    {\n" +
                "      \"itemId\": \"MLU468887129\",\n" +
                "      \"stop_time\": \"2020-04-25T22:10:52Z\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        Jdbi jdbi = mock(Jdbi.class);
        assertEquals(expected, new JdbiClient(jdbi, GsonBuilderFactory.GSON).serialize(dao));
    }

    @Test
    void save() {
        Item item = mock(Item.class);
        Jdbi jdbi = mock(Jdbi.class);
        new JdbiClient(jdbi, GsonBuilderFactory.GSON).save(item);
        verify(jdbi).useHandle(any());
    }

    @Test
    void getById() {
        Item item = mock(Item.class);
        Jdbi jdbi = mock(Jdbi.class);
        when(jdbi.withHandle(any())).thenReturn(Optional.of(item));
        Optional<Item> result = new JdbiClient(jdbi, GsonBuilderFactory.GSON).getById("id");
        assertEquals(Optional.of(item), result);
    }
}