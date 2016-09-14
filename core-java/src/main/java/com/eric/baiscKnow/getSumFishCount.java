package com.eric.baiscKnow;

public class getSumFishCount {
	public static void main(String[] args) {
		boolean flag=true;
		int num=0;
		for (int i = 6	; flag; i++) {
			int count=0;
			for(int j=i,k=0;k<=5;k++){
				num++;
				if((j-1)%5==0){
					j=4*(j-1)/5;
					count++;
					System.out.print("    count:"+count+"  j:"+j);
					if(count==5){
						flag=false;
						System.out.println(j);
						return;
					}
				}
			}
			num++;
			System.out.println("  i's value is: "+i);
			System.out.println("  num is:"+num);
			
		}
	}
}
