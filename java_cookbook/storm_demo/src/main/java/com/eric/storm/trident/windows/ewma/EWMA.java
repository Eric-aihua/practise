package com.eric.storm.trident.windows.ewma;

import java.io.Serializable;

/**
 * EWMA实现定义了三个有用的alpha常量值:ONE_MINUTE_ALPHA，FIVE_MINUTE_ALPHA,FIFTEEN_MINUTE_ALPHA。这些对应于标准的alpha值用来计算平均负载在UNIX中。
 * alpha值也可以手动指定,或作为一个alpha窗口的函数。
 实现使用fluent-style构建器API。例如,您可以创建一个EWMA实例实现一分钟的滑动窗口和一个alpha值相当于UNIX一分钟间隔,使用以下代码片段所示:
 EWMA ewma= new EWMA().sliding(1.0, Time.MINUTES).withAlphaEWMA.ONE_MINUTE_ALPHA);
 mark()方法用于更新移动平均线。没有参数,mark()方法将使用当前时间来计算平均值。因为我们想使用原始的日志事件的时间戳,覆盖mark()方法允许我们定义一个特定的时间规范。
 getAverage()方法返回调用mark()之间的平均时间,以毫秒为单位。我们还增加了方便的getAverageIn()方法,它将返回按指定度量单位的平均时间(秒、分钟、小时等等)。getAverageRatePer()方法返回调用mark()在一个特定的时间测量的速率。
 你可能会发现,使用指数加权移动平均线有点棘手。找到合适的组值α以及可选的滑动窗口变化很大取决于具体的用例,找到正确的值主要是尝试和错误的问题。

 更多关于EWMA的说明请参考：http://metrics.dropwizard.io/3.1.0/apidocs/com/codahale/metrics/EWMA.html

 * Created by Eric on 2017/8/25.
 */
public class EWMA implements Serializable {
    private static final long serialVersionUID = 3590847048791392080L;

    public static enum Time {
        MILLISECONDS(1),
        SECONDS(1000),
        MINUTES(SECONDS.getTime() * 60),
        HOURS(MINUTES.getTime() * 60),
        DAYS(HOURS.getTime() * 24),
        WEEKS(DAYS.getTime() * 7);
        private long millis;
        private Time(long millis) {
            this.millis = millis;
        }
        public long getTime() {
            return this.millis;
        }
    }

    // Unix load average-style alpha constants
    public static final double ONE_MINUTE_ALPHA= 1 - Math.exp(-5d / 60d / 1d);
    public static final double IVE_MINUTE_ALPHA = 1 - Math.exp(-5d / 60d / 5d);
    public static final double FIFTEEN_MINUTE_ALPHA =  1 - Math.exp(-5d/ 60d / 15d);
    private long window;
    private long alphaWindow;
    private long last;
    private double average;
    private double alpha = -1D;
    private boolean sliding = false;
    public EWMA() {
    }

    public EWMA sliding(double count, Time time) {
        return this.sliding((long)(time.getTime() * count));
    }
    public EWMA sliding(long window) {
        this.sliding = true;
        this.window = window;
        return this;
    }

    public EWMA withAlpha(double alpha) {
        if (!(alpha > 0.0D && alpha<= 1.0D)) {
            throw new IllegalArgumentException("Alpha must be between 0.0 and 1.0");
        }
        this.alpha = alpha;
        return this;
    }
    public EWMA withAlphaWindow(long alphaWindow) {
        this.alpha = -1;
        this.alphaWindow = alphaWindow;
        return this;
    }

    public EWMA withAlphaWindow(double count,Time
            time) {
        return this.withAlphaWindow((long)(time.getTime() * count));
    }
    public void mark() {
        mark(System.currentTimeMillis());
    }

    public synchronized void mark(long time) {
        if (this.sliding) {
            if (time - this.last >this.window) {
                // reset the sliding window
                this.last = 0;
            }
        }
        if (this.last == 0) {
            this.average = 0;
            this.last = time;
        }
        long diff = time - this.last;
        double alpha = this.alpha != -1.0 ?this.alpha :
                Math.exp(-1.0 * ((double) diff/ this.alphaWindow));
        this.average = (1.0 - alpha) * diff +alpha * this.average;
        this.last = time;
    }
    public double getAverage() {
        return this.average;
    }
    public double getAverageIn(Time time) {
        return this.average == 0.0 ?this.average :
                this.average / time.getTime();
    }
    public double getAverageRatePer(Time time){
        return this.average == 0.0 ?this.average :
                time.getTime() / this.average;
    }
}