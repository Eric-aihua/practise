package com.eric.storm.olap;

import java.io.Serializable;

/**
 * Created by Eric on 2017/10/26.
 */
public class FixMessageDto  implements Serializable{


    private static final long serialVersionUID = 7011012703269908813L;
    private String mid;
    private String msgType;
    private Float price;
    private String symbol;

    public FixMessageDto() {
    }

    public FixMessageDto(String mid, String msgType, Float price, String symbol) {
        this.mid = mid;
        this.msgType = msgType;
        this.price = price;
        this.symbol = symbol;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "FixMessageDto{" +
                "mid='" + mid + '\'' +
                ", msgType='" + msgType + '\'' +
                ", price=" + price +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
