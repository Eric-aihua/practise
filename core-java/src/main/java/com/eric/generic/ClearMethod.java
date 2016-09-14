package com.eric.generic;

import java.util.Date;

public class ClearMethod extends Pari<Date>{
	public void setSecond(Date d){
		if(d.compareTo(getFirst())>0){
			super.setSecond(d);
		}
	}
}
