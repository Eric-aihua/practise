package com.eric.storm.trident.windows.outbreakdetector;

import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityAssignment extends BaseFunction {
    private static final long serialVersionUID = 2935524282769673773L;
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    private Map<String,double[]> cities=new HashMap<String,double[]>();
    {
        //城市的基础坐标数据
        double [] phl={39.875365,-75.249524};
        double [] nyc={40.71448,-74.00498};
        double [] sf={-41.4150342,-62.0841809};
        double [] la={-34.052347,-118.24307};
        cities.put("PHL",phl);
        cities.put("NYC",nyc);
        cities.put("SF",sf);
        cities.put("LA",la);
    }


    /**
     * 将传过来的Event的坐标属性和预定义几个城市的坐标比较距离，选择一个最近的城市作为最后的结果
     * @param tridentTuple
     * @param tridentCollector
     */
    @Override
    public void execute(TridentTuple tridentTuple, TridentCollector tridentCollector) {
        DiagnosisEvent event=(DiagnosisEvent) tridentTuple.get(0);
        double leastDistance=Double.MAX_VALUE;
        String closeCity="None";
        for(String city:cities.keySet()){
            double[] cityLocation=cities.get(city);
            double R=6371;//KM
            double x=(cityLocation[0]-event.getLng())*Math.cos((cityLocation[0]+event.getLng())/2);
            double y=cityLocation[1]-event.getLat();
            double distance=Math.sqrt(x*x+y*y)*R;
            if (distance<leastDistance){
                leastDistance=distance;
                closeCity=city;
            }
            List<Object> result=new ArrayList<Object>();
            result.add(closeCity);
            logger.info("Event:"+event+"   Close City is:"+closeCity);
            tridentCollector.emit(result);
        }
    }
}
