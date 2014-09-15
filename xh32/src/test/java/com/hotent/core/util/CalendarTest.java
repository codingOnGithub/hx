package com.hotent.core.util;

import java.util.Calendar;

public class CalendarTest {
	
	public static void main(String[] args){
		Calendar cal=Calendar.getInstance();
		for(int i=1;i<30;i++){
			cal.set(2012, 2, 1);
			System.out.println( cal.get(Calendar.DAY_OF_WEEK) +"," + Calendar.MONDAY + "," + Calendar.SUNDAY);
		}
		
		
	}

}
