package model;

import java.util.Random;

public class IDRandom {
	public static void main(String[] args) {
	IDRandom r = new IDRandom();
	System.out.println("KH-"+r.getIDRandom());
	}
	
	public String getIDRandom() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		String split[] = characters.split("");
		String result = "";
		for(int i =0;i<7;i++) {
			result += split[random.nextInt(split.length)];
		}
		return result;
	}
}