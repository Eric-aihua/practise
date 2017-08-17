package com.eric.storm.trident.windows;

/**
 * 本程序主要通过按照地址位置来统计各种疾病在一段时间内出现的次数，会按照小时的粒度对数据进行分组，主要使用滑动窗口的模型，来计算移动平均值的情况。
 * 程序中使用最简单的阀值来判断疫情是否爆发，如果某个小时事件发生的次数超过了阀值，系统就会产生告警
 * 为了维护历史数据，还需要将每个城市，小时，疾病的统计情况持久化存储
 *
 * */
public class OutBreakDetectionTopology {
    public static void main(String[] args){

    }
}
