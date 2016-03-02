package com.eric.es.rest.utils;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * 用于生成
 * Created by Eric on 2015/12/8.
 */
public class ClientUtils {


    private static final String DEFAULT_CLUSTER_NAME ="es-cluster" ;

    public static Client getClient(String clusterName,String hostName,int port){
        Settings settings= ImmutableSettings.settingsBuilder().put("cluster.name", clusterName != null ? clusterName : DEFAULT_CLUSTER_NAME).build();
        Client client=new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress(hostName,port));
        return client;

    }
}
