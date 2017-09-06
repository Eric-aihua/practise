package com.eric.storm.graph.basic;

import org.neo4j.cypher.internal.ExecutionEngine;
import org.neo4j.cypher.internal.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;

/**
 * 使用EmbeddedDatabase的方式进行演示
 * Created by Eric on 2017/8/30.
 */
public class Neo4JCypherAPISample {
    public static void main(String args[]){
            GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();
            GraphDatabaseService graphDb = graphDbFactory.newEmbeddedDatabase(new File("C:/TPNeo4jDB"));
        //由于API发生变化，暂时不进行验证
//            ExecutionEngine execEngine = new ExecutionEngine(graphDb);
//            ExecutionResult execResult = execEngine.execute("MATCH (java:JAVA) RETURN java");
//            String results = execResult.dumpToString();
//            System.out.println(results);
    }
}
