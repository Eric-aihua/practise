package com.eric.neo4j;
import org.neo4j.driver.v1.*;
import org.neo4j.io.pagecache.tracing.AutoCloseablePageCacheTracerEvent;

import java.io.*;

import static org.neo4j.driver.v1.Values.parameters;

/**
 * 通过分析ADS的攻击日志，生成Attack的Graph图
 * Lable: 攻击源，攻击目标
 * Relation:DDos攻击
 * Created by Eric on 2017/8/31.
 */
public class AdsAttackGraph implements AutoCloseablePageCacheTracerEvent {
    private final Driver driver;

    public AdsAttackGraph(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    public void close() {
        driver.close();
    }

    public void genAttackEventGraph(final String logFilePath) {
        try {
            Session session = driver.session();
            String greeting = session.writeTransaction(new ADSAttackTransactionWork(logFilePath));
            System.out.println(greeting);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String... args) throws Exception {
        try {
            AdsAttackGraph adsAttackGraph = new AdsAttackGraph("bolt://10.5.25.18:7687", "neo4j", "admin");
            adsAttackGraph.genAttackEventGraph("C:\\Users\\Eric\\Desktop\\show.archive_2016080311_0000_00");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

class ADSAttackTransactionWork implements  TransactionWork<String> {
    private String asdFilePath;
    public ADSAttackTransactionWork(String adsFilePath){
        this.asdFilePath=adsFilePath;
    }

    public String execute(Transaction transaction) {
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(this.asdFilePath));
            String line=null;
            StringBuilder sb=null;
            while ((line=bufferedReader.readLine())!=null){
                String[] result=line.split("\t");
                String deviceIp=result[0];
                String desIP=result[4];
                String attackType=result[5];
                String attackPort=result[6];
                String beginTime=result[8];
                String endTime=result[9];
                sb=new StringBuilder();
                sb.append("CREATE (").append(deviceIp).append(":Device) ,(").append(desIP).append(":TargetIP) ,(").append(deviceIp);
                StatementResult statementResult = transaction.run(sb.toString()+")-[:pass {attackType:$attackType,attackPort:$attackPort,beginTime:$beginTime,endTime:$endTime}]->($destIP) ",
                        parameters("deviceIp", deviceIp,"desIP",desIP,"attackType",attackType,"attackPort",attackPort,"beginTime",beginTime,"endTime",endTime));
               System.out.println( statementResult.single().get(0).asString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        return result.single().get(0).asString();
        return "Successful";
    }
}
}
