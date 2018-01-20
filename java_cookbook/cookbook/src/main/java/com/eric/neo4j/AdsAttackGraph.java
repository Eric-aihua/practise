package com.eric.neo4j;
import org.neo4j.driver.v1.*;
import static org.neo4j.driver.v1.Values.parameters;

import java.io.*;


/**
 * 通过分析ADS的攻击日志，生成Attack的Graph图
 * Lable: 攻击源，攻击目标
 * Relation:DDos攻击
 * Created by Eric on 2017/8/31.
 */
public class AdsAttackGraph {
    private final Driver driver;

    public AdsAttackGraph(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }



    public void genAttackEventGraph(final String logFilePath) {
        try {
            Session session = driver.session();
            new ADSAttackTransactionWork(logFilePath,session).execute();
            session.close();
            driver.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String... args) throws Exception {
        try {
            AdsAttackGraph adsAttackGraph = new AdsAttackGraph("bolt://10.5.25.18:7687", "neo4j", "admin");
            adsAttackGraph.genAttackEventGraph("C:\\Users\\Eric\\Desktop\\000001");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

class ADSAttackTransactionWork {
    private String asdFilePath;
    private Session session;
    public ADSAttackTransactionWork(String adsFilePath,Session session){
        this.asdFilePath=adsFilePath;
        this.session=session;
    }

    public String execute() {
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(this.asdFilePath));
            String line=null;
            StringBuilder sb=null;
            while ((line=bufferedReader.readLine())!=null){
                System.out.println(line);
                String[] result=line.split("\t");
                String deviceIp=result[0].replace('.','_');
                String desIP=result[4].replace('.','_');
                String attackType=result[5];
                String attackPort=result[6];
                String beginTime=result[8];
                String endTime=result[9];
                sb=new StringBuilder();
                sb.append("CREATE  ( src_").append(deviceIp).append(":Device) ,(dst_").append(desIP).append(":TargetIP) ,(src_").append(deviceIp);
                StatementResult statementResult=session.run(sb.toString()+")-[:pass {attackType:{attackType},attackPort:{attackPort},beginTime:{beginTime},endTime:{endTime}}]->({destIP}) ",
                        parameters("attackType",attackType,"attackPort",attackPort,"beginTime",beginTime,"endTime",endTime,"destIP",desIP));
                while ( statementResult.hasNext() )
                {
                    Record record = statementResult.next();
                    System.out.println( record );
                }

            }
            System.out.println("Parsed finished!");
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
