package com.eric.storm.olap;


import com.eric.storm.olap.state.DruidPartitionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.metamx.tranquility.config.DataSourceConfig;
import com.metamx.tranquility.config.PropertiesBasedConfig;
import com.metamx.tranquility.config.TranquilityConfig;
import com.metamx.tranquility.druid.DruidBeams;
import com.metamx.tranquility.tranquilizer.MessageDroppedException;
import com.metamx.tranquility.tranquilizer.Tranquilizer;
import com.twitter.util.FutureEventListener;
import scala.runtime.BoxedUnit;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by Eric on 2017/12/20.
 */
public class DruidTranquilitySender {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    private Tranquilizer<Map<String, Object>> sender;
    public static final DruidPartitionStatus STATUS = new DruidPartitionStatus();

    public DruidTranquilitySender(String configJson, String dataSource) {
        InputStream configStream = DruidTranquilitySender.class.getClassLoader().getResourceAsStream(configJson);
        TranquilityConfig<PropertiesBasedConfig> config = TranquilityConfig.read(configStream);
        DataSourceConfig<PropertiesBasedConfig> wikipediaConfig = config.getDataSource(dataSource);
        this.sender = DruidBeams.fromConfig(wikipediaConfig)
                .buildTranquilizer(wikipediaConfig.tranquilizerBuilder());
    }

    /**
     * 在调用sendMsg之前执行
     */


    public synchronized void  startSender(){
        sender.start();
    }

    /**
     * 在senderMsg调用完之后进行调用
     */
    public void finishedSender(){
        this.sender.flush();
        this.sender.close();
    }


    public void sendMsg(Map<String, Object> msg) {
        try {
            sender.send(msg).addEventListener(new FutureEventListener<BoxedUnit>() {

                @Override
                public void onSuccess(BoxedUnit boxedUnit) {
                    System.out.println("Sent message: %s" + msg);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    if (throwable instanceof MessageDroppedException) {
                        logger.error("Dropped message: %s" + msg);
                    } else {
                        logger.error("Failed to send message: %s" + msg);
                    }
                }
            });
        }catch (Exception  ex){
            ex.printStackTrace();
        }
    }


    public void sendMsg(String partitionId, List<Map<String, Object>> messages) {
        for (Map<String,Object> msg : messages){
            sendMsg(msg);
        }
    }
}
