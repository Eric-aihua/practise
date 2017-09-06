package com.eric.storm.graph.basic;

import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;

/**
 *  使用EmbeddedDatabase的方式进行演示
 * Created by Eric on 2017/8/30.
 */
public class Neo4JNativeAPISample {
    public static void main(String [] args){
        new Neo4JNativeAPISample().useNodeAndRelationship();
    }

    public enum Tutorials implements Label {
        JAVA,SCALA,SQL,NEO4J;
    }

    public enum TutorialRelationships implements RelationshipType{
        JVM_LANGIAGES,NON_JVM_LANGIAGES;
    }

    public void useNodeAndRelationship() {
        GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
        GraphDatabaseService db = dbFactory.newEmbeddedDatabase(new File("E:/TPNeo4jDB"));
        Transaction tx = db.beginTx();
        try {


            Node javaNode = db.createNode(Tutorials.JAVA);
            javaNode.setProperty("TutorialID", "JAVA001");
            javaNode.setProperty("Title", "Learn Java");
            javaNode.setProperty("NoOfChapters", "25");
            javaNode.setProperty("Status", "Completed");



            Node scalaNode = db.createNode(Tutorials.SCALA);
            scalaNode.setProperty("TutorialID", "SCALA001");
            scalaNode.setProperty("Title", "Learn Scala");
            scalaNode.setProperty("NoOfChapters", "20");
            scalaNode.setProperty("Status", "Completed");

            Relationship relationship = javaNode.createRelationshipTo(scalaNode,
                    TutorialRelationships.JVM_LANGIAGES);


            relationship.setProperty("Id","1234");
            relationship.setProperty("OOPS","YES");
            relationship.setProperty("FP","YES");
            tx.success();
        } finally {
            System.out.println("Insert successful!");
            tx.success();
        }
    }
}
