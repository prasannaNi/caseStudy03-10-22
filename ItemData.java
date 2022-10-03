package com.gl.caseStudy2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ItemData {
	public static Map<String,SnackItem> itemMap;
	
	static {
		try {
			FileReader fr = new FileReader("D:/code/java-eclipse/caseStudies/caseStudy2/files/SnackItem.txt");
			BufferedReader br = new BufferedReader(fr);
			
			itemMap = new HashMap<>();
			while(true) {
				String str = br.readLine();
				if(str == null)
					break;
				String split[] = str.split("-");
				String itemName = split[0];
				String rate = split[1];
				String status = split[2];
				String discountRate = split[3];
				String discountQty = split[4];
				
				SnackItem sk = new SnackItem(itemName, rate, status, discountRate, discountQty);
				itemMap.put(itemName, sk);
				}
			}
			
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static  Map<String,SnackItem> getAllItems() {
		return itemMap;
	}
	
	public static SnackItem getItem(String itemName) {
		return itemMap.get(itemName);
	}
	
	public static boolean isAvailable(String itemName) {
		if(itemMap.containsKey(itemName))
			return true;
		else
			return false;
	}

}
