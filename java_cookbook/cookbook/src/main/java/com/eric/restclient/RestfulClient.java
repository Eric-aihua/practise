package com.eric.restclient;

import com.eric.webservice.Main;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * Created by Eric on 2015/12/2.
 */
public class RestfulClient {
    @Test
    public void testGetFromMain() {
        WebTarget client = ClientBuilder.newClient().target(Main.BASE_URI);
        String result = client.path("myresource/getit").request().get(String.class);
        System.out.print(result);
    }

    @Test
    public void testGetFromES() {
        WebTarget client = ClientBuilder.newClient().target("http://localhost:9200/");
        String result = client.request().get(String.class);
        System.out.print(result);
    }

}
