package org.laarguelless.rest.clients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.leangen.geantyref.TypeToken;
import io.vavr.collection.List;
import io.vavr.gson.VavrGson;
import org.junit.jupiter.api.Test;
import org.laarguelless.json.GsonBuilderFactory;
import org.laarguelless.rest.clients.dto.ChildDto;
import org.laarguelless.rest.clients.dto.ImmutableChildDto;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChildrenRestClientTest{

    private final Type listType = new TypeToken<List<ChildDto>>() {}.getType();

    @Test
    void testParseValueFromResponse() {

        Gson gson = GsonBuilderFactory.GSON;

        String json = "[{\"id\":\"MLU468887129\",\"site_id\":\"MLU\",\"title\":\"Google Pixel 32gb Silver - Impecable!\",\"subtitle\":null,\"seller_id\":79404795,\"category_id\":\"MLU1055\",\"official_store_id\":null,\"price\":330,\"base_price\":330,\"original_price\":null,\"currency_id\":\"USD\",\"initial_quantity\":1,\"available_quantity\":1,\"sold_quantity\":0,\"sale_terms\":[{\"id\":\"WARRANTY_TYPE\",\"name\":\"Tipo de garantía\",\"value_id\":\"6150835\",\"value_name\":\"Sin garantía\",\"value_struct\":null,\"values\":[{\"id\":\"6150835\",\"name\":\"Sin garantía\",\"struct\":null}]}],\"buying_mode\":\"buy_it_now\",\"listing_type_id\":\"gold_special\",\"start_time\":\"2019-12-17T12:31:52.000Z\",\"historical_start_time\":\"2019-03-02T20:31:02.000Z\",\"stop_time\":\"2020-04-25T22:10:52.000Z\",\"condition\":\"used\",\"permalink\":\"https://articulo.mercadolibre.com.uy/MLU-468887129-google-pixel-32gb-silver-impecable-_JM\",\"thumbnail\":\"\",\"secure_thumbnail\":\"\",\"pictures\":null,\"video_id\":null,\"descriptions\":null,\"accepts_mercadopago\":true,\"non_mercado_pago_payment_methods\":null,\"shipping\":null,\"international_delivery_mode\":\"none\",\"seller_contact\":null,\"location\":null,\"coverage_areas\":null,\"attributes\":null,\"warnings\":null,\"listing_source\":\"\",\"variations\":null,\"status\":\"closed\",\"sub_status\":[],\"tags\":[\"dragged_bids_and_visits\",\"poor_quality_picture\",\"immediate_payment\",\"cart_eligible\"],\"warranty\":\"Sin garantía\",\"catalog_product_id\":\"MLU6292200\",\"domain_id\":\"MLU-CELLPHONES\",\"parent_item_id\":\"MLU460998489\",\"differential_pricing\":null,\"deal_ids\":[],\"automatic_relist\":false,\"date_created\":\"2019-12-17T12:31:52.000Z\",\"last_updated\":\"2020-05-06T12:07:18.000Z\",\"total_listing_fee\":null,\"health\":0.71,\"catalog_listing\":false}]";

        Response response = mock(Response.class);
        when(response.readEntity(String.class)).thenReturn(json);
        Client client = mock(Client.class);
        String url = "";

        ChildrenRestClient childrenRestClient = new ChildrenRestClient(client,url,gson);
        List<ChildDto> children = childrenRestClient.parseValueFromResponse(response);
        ChildDto expectedDto = ImmutableChildDto.builder()
                .id("MLU468887129")
                .stopTime(OffsetDateTime.parse("2020-04-25T22:10:52Z"))
                .build();
        assertTrue(children.contains(expectedDto));

    }

    @SuppressWarnings("unchecked")
    @Test
    void getEmptyValue() {
        Client client = mock(Client.class);
        String url = "";

        ChildrenRestClient childrenRestClient = new ChildrenRestClient(client,url, new Gson());
        assertEquals(childrenRestClient.getEmptyValue(),List.empty());
    }
}