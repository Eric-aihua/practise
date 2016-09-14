package com.eric.es.rest.sample.query;

import com.eric.es.rest.utils.ClientUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

/**
 * Created by Eric on 2015/12/8.
 */
public class QuerySample {
    public static void main(String args[]) {
        matchQuery();
    }

    public static void matchQuery() {
        Client client =ClientUtils.getClient("es","192.168.20.50",9300);
        SearchResponse searchResponse = client.prepareSearch().setIndices("first").
                setSearchType(SearchType.DFS_QUERY_THEN_FETCH).
                setQuery(QueryBuilders.matchQuery("content", "我们")).execute().actionGet();
        for(SearchHit searchHit:searchResponse.getHits().getHits()){
            System.out.println(searchHit.getSourceAsString());
        }
        client.close();
    }
}
