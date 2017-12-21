package com.eric.storm.olap;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by Eric on 2017/10/26.
 */
public class FixMessageDto  implements Serializable{


    private static final long serialVersionUID = 7011012703269908813L;
    private String org;
    private String partner;
    private String pg;
    private String dip;
    private String deviceNo;
    private String sip;


    public FixMessageDto(String org, String partner, String pg, String dip, String deviceNo, String sip) {
        this.org = org;
        this.partner = partner;
        this.pg = pg;
        this.dip = dip;
        this.deviceNo = deviceNo;
        this.sip = sip;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("org", org)
                .append("partner", partner)
                .append("pg", pg)
                .append("dip", dip)
                .append("deviceNo", deviceNo)
                .append("sip", sip)
                .toString();
    }
}
