package com.eric.jvm.memeory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * GC overhead limit exceed检查是Hotspot VM 1.6定义的一个策略，
 * 通过统计GC时间来预测是否要OOM了，提前抛出异常，防止OOM发生。
 * Sun 官方对此的定义是：“并行/并发回收器在GC回收时间过长时会抛出OutOfMemroyError。过长的定义是，
 * 超过98%的时间用来做GC并且回收了不到2%的堆内存。用来避免内存过小造成应用不能正常工作。“
 *
 * 一般是应用程序在有限的内存上创建了大量的临时对象或者弱引用对象，从而导致该异常。
 * 虽然加大内存可以暂时解决这个问题，但是还是强烈建议去优化代码，后者更加有效。
 *
 *
 *为了便于问题重现，可以加上参数 -Xmx5m
 *
 * 如果想要隐藏GC overhead limit exceeded 异常可以加参数-XX:-UseGCOverheadLimit，
 * 但是这只是让问题暴露的更晚一点，并没有解决根本的问题
 *
 * 如果想在oom时将gc信息以及heap信息dump出来，可以按照下列方式进行配置
 * -Xmx5m -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError  -Xloggc:d:/dump.log -XX:HeapDumpPath=d:/heap.log
 *
 * 关于GC overhead limit exceeded 异常的说明，可以参见：https://plumbr.eu/outofmemoryerror/gc-overhead-limit-exceeded
 *
 *
 *  Created by dell on 2016/9/18.
 *
 */
public class GCOverheadLimitOOM {
    public static void main(String args[]) {
        Map<Integer,String> map=new HashMap<Integer, String>();
        Random random=new Random();
        while(true){
            map.put(random.nextInt(),"value");
        }

    }
}
