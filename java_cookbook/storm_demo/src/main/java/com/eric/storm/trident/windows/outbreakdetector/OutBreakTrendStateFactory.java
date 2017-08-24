package com.eric.storm.trident.windows.outbreakdetector;

import org.apache.storm.task.IMetricsContext;
import org.apache.storm.trident.state.State;
import org.apache.storm.trident.state.StateFactory;

import java.util.Map;

public class OutBreakTrendStateFactory implements StateFactory {
    private static final long serialVersionUID = -6860219568547283565L;

    @Override
    public State makeState(Map map, IMetricsContext iMetricsContext, int i, int i1) {
        return new OutBreakTrendState(new OutBreakTrendBackMapping());
    }
}
