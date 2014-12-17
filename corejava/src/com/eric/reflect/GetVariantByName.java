
 

package com.eric.reflect;

import java.lang.reflect.Field;

 

 

 


public class GetVariantByName {
    public static final String PCE_VERSION_CONTROL = "@(#) $RCSfile: $, $Revision: $, $Date: $";
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        Class<Constant> constant=(Class<Constant>)new  Constant().getClass();
        try {
           Field field= constant.getDeclaredField("NAME");
            System.out.println(field.get(new Constant()));
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
class Constant{
    public static final ConstantElement NAME=new ConstantElement("name","Eric");
    public static final ConstantElement AGE=new ConstantElement("age","15");
}
class ConstantElement{
    private String type;
    private String value;
    public ConstantElement(String type, String value) {
        super();
        this.type = type;
        this.value = value;
    }
    @Override
    public String toString() {
        return "type:"+type+"  value:"+value;
    }
    
    
}


/*

 * History:

 *

 * $Log: $

 */