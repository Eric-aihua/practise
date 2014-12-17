package com.eric.generic;

public class SimpleHolder {
    private Object obj;

    public SimpleHolder() {
        super();
    }

    public SimpleHolder(Object obj) {
        super();
        this.obj = obj;
    }

    /**
     * @return the obj
     */
    public Object getObj() {
        return obj;
    }

    /**
     * @param obj
     *            the obj to set
     */
    public void setObj(Object obj) {
        this.obj = obj;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SimpleHolder sh =new SimpleHolder();
        sh.setObj("Eric");
        String str=(String) sh.getObj();
    }
}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */
