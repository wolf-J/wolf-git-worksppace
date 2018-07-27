package com.aia.ui.utils;

import java.time.LocalDate;
import java.time.Month;

public class NewDateUtil {
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDate lDate=LocalDate.of(2017, 4, 25);
		int year=lDate.getYear();
		Month month=lDate.getMonth();
		System.out.println(month);
	}

}
