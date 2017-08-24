package com.eric.storm.trident.windows.outbreakdetector;

import org.apache.storm.trident.state.map.NonTransactionalMap;

/**
 * Created by Eric on 2017/8/24.
 */
public class OutBreakTrendState extends NonTransactionalMap<Long> {
    public OutBreakTrendState(OutBreakTrendBackMapping outBreakTrendBackMapping) {
        super(outBreakTrendBackMapping);
    }
}
