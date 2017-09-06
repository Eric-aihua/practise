package com.eric.storm.graph.basic;

import org.neo4j.driver.v1.*;
import org.neo4j.io.pagecache.tracing.AutoCloseablePageCacheTracerEvent;

import static org.neo4j.driver.v1.Values.parameters;

/**
 * 使用Driver链接Remote DB
 * 參考文檔：http://neo4j.com/docs/developer-manual/current/drivers/get-started/
 * Created by Eric on 2017/8/30.
 */
public class Neo4JDriverAPISample implements AutoCloseablePageCacheTracerEvent {
    private final Driver driver;

    public Neo4JDriverAPISample(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() {
        driver.close();
    }

    public void printGreeting(final String message) {
        try {
            Session session = driver.session();
            String greeting = session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction tx) {
                    StatementResult result = tx.run("CREATE (a:Greeting) " +
                                    "SET a.message = $message " +
                                    "RETURN a.message + ', from node ' + id(a)",
                            parameters("message", message));
                    return result.single().get(0).asString();
                }
            });
            System.out.println(greeting);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String... args) throws Exception {
        try {
            Neo4JDriverAPISample greeter = new Neo4JDriverAPISample("bolt://10.5.25.18:7687", "neo4j", "admin");
            greeter.printGreeting("hello, world");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
