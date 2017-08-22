package com.eric.storm.trident.windows.outbreakdetector;

import org.apache.storm.generated.StormTopology;
import org.apache.storm.trident.Stream;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.trident.operation.builtin.Count;
import org.apache.storm.tuple.Fields;


public class OutBreakDetectionTopology {
    public static StormTopology buildTopology(){
        TridentTopology tridentTopology=new TridentTopology();
        DiagnosisEventSpot diagnosisEventSpot=new DiagnosisEventSpot();
        Stream stream=tridentTopology.newStream("spout",diagnosisEventSpot);
        stream.each(new Fields("spout"),new DiseaseFilter()).
                each(new Fields("spout"),new CityAssignment(),new Fields("withCity"))
                .each(new Fields("withCity"),new HourAssignment(),new Fields("withCityHour")).
                groupBy(new Fields("withCityHour")).
                persistentAggregate(new OutBreakTrandStateFactory(), new Count(),new Fields("count")).newValuesStream()
                .each(new Fields("withCityHour"),new OutBreakDetector(),new Fields("alter")).each(new Fields("alter"),new DispatchAlter(),new Fields());
        return tridentTopology.build();
    }


}
