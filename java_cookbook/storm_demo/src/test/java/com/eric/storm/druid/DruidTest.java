package com.eric.storm.druid;

import com.eric.storm.olap.DruidTranquilitySender;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang.math.RandomUtils;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Eric on 2017/12/20.
 */
public class DruidTest {
    public static void main(String[] args) {
//        sendWikiData();
        sendStatSipData();
    }

    private static void sendStatSipData() {
        DruidTranquilitySender sender = new DruidTranquilitySender("example.json", "stat_sip");
        sender.startSender();
        // Send 10000 objects
        String[][] randomIPInfo = new String[100][3];
        String dipPrefix = "192.168";
        String sipPrefix = "222.168";
        String randomOrg = UUID.randomUUID().toString();
        String randomPartner = UUID.randomUUID().toString();
        String randomPG = UUID.randomUUID().toString();
        String randomDevice = UUID.randomUUID().toString();
        Random random = new Random();
        for (int index = 0; index < randomIPInfo.length; index++) {
            randomIPInfo[index][0] = dipPrefix + "." + random.nextInt(255) + "." + random.nextInt(255);
            randomIPInfo[index][1] = sipPrefix + "." + random.nextInt(255) + "." + random.nextInt(255);
        }
        for (int i = 0; i < 50000; i++) {
            // Build a sample event to send; make sure we use a current date
            String[] ipTuple = randomIPInfo[random.nextInt(randomIPInfo.length)];
            final Map<String, Object> obj = new HashMap<String, Object>();
            obj.put("timestamp", new DateTime().toString());
            obj.put("org", randomOrg);
            obj.put("partner", randomPartner);
            obj.put("device_no", randomDevice);
            obj.put("pg", randomPG);
            obj.put("dip", ipTuple[0]);
            obj.put("sip", ipTuple[1]);
            obj.put("in_bps",random.nextDouble()*100000000);
            obj.put("pa_bps",random.nextDouble()*10000000);
            obj.put("dr_bps",random.nextDouble()*1000000);
            obj.put("in_pps",random.nextDouble()*1000000);
            obj.put("dr_pps",random.nextDouble()*1000000);
            obj.put("pa_pps",random.nextDouble()*1000000);
            sender.sendMsg(obj);
        }
        sender.finishedSender();
    }

    private static void sendWikiData() {
        DruidTranquilitySender sender = new DruidTranquilitySender("example.json", "wikipedia-java-client");
        sender.startSender();
        // Send 10000 objects
        for (int i = 0; i < 10000; i++) {
            // Build a sample event to send; make sure we use a current date
            final Map<String, Object> obj = ImmutableMap.<String, Object>of(
                    "timestamp", new DateTime().toString(),
                    "page", "foo",
                    "added", i
            );
            sender.sendMsg(obj);
        }
        sender.finishedSender();
    }
}
