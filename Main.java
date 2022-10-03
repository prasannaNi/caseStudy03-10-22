package com.gl.caseStudy2;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static String floatToString(double value) {
		DecimalFormat df = new DecimalFormat("0.00");
	    return df.format(value);  
	}
	
	public static String salesTaxCalculation(double billAmount) {
		  double tax = 0.0;
	        if (billAmount <= 1000) {
	            tax = billAmount * 0.125;
	        } 
	        else if (billAmount > 1000 && billAmount <= 2500) {
	            tax = billAmount * 0.10;
	        } 
	        else {
	            tax = billAmount * 0.075;
	        }
	        return floatToString(tax);
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Customer Name:");
		String cname=sc.nextLine();
		System.out.println("Enter number of items the customer purchases:");
		int n=Integer.parseInt(sc.nextLine());
		List<OrderedItem> orderedItemList=new ArrayList<>();
		
		for (int i=0;i<n;i++) {
			System.out.println("Enter name and quantity (comma separate format) of purchased item no "+(i+1));
			String str=sc.nextLine();
			String[] item = str.split(",");
			String itemName=item[0];
			String itemQuantity=item[1];
			
			if (ItemData.isAvailable(itemName)) {
				SnackItem si=ItemData.getItem(itemName);
				String rate=si.getRate();
				String discountRate=si.getDiscountRate();
				String discountQty=si.getDiscountQty();
				Double discountAmount=0.0;
				Double payAmount=Double.parseDouble(rate)*Double.parseDouble(itemQuantity);
				
				if (Double.parseDouble(itemQuantity)>=Double.parseDouble(discountQty)) {
					discountAmount=payAmount * (Double.parseDouble(discountRate)/100);
					payAmount= payAmount-discountAmount;
				}

				OrderedItem ordItem=new OrderedItem(itemName,rate,itemQuantity,floatToString(discountAmount),floatToString(payAmount) );
				orderedItemList.add(ordItem);	
			}
			
			else {
				System.out.println("Entered Item is not Available");
			}
			
		}
		DateFormat formatter = new SimpleDateFormat("dd-MM-yy");
		Calendar obj = Calendar.getInstance();
		String date = formatter.format(obj.getTime());
		System.out.println("\nCustomer Name:"+cname+"\t\t\t\t\tDate:"+date);
		System.out.println();
		String output=String.format("%-10s %-10s %-10s %-10s %-10s %-10s", "ITEM" ,"RATE","QUANTITY","PRICE","DISCOUNT","AMOUNT");
		System.out.println(output);
		Double billAmount=0.0;
		for (OrderedItem o:orderedItemList) {
			String name=o.getItemName();
			String rate=o.getRate();
			String quantity=o.getOrderQty();
			SnackItem s=ItemData.getItem(name);
			Double price=Double.parseDouble(s.getRate())*Double.parseDouble(quantity);
			String dis=o.getDiscountAmount();
			String amount=o.getPayAmount();
			billAmount+= Double.parseDouble(amount);
			
			System.out.println(String.format("%-10s %-10s %-10s %-10s %-10s %-10s", name,rate,quantity,price,dis,amount));
		}
		String taxCal=salesTaxCalculation(billAmount);
		Double totalAmount=billAmount+Double.parseDouble(taxCal);
		
		System.out.println("\n\t\t\t\tBill Amount:"+billAmount);
		System.out.println("\t\t\t\tAdd: Sales Tax:"+taxCal);
		System.out.println("\t\t\t\tFinal Amount:"+totalAmount);
	
		
	}

}
