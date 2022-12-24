package utitls;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Test {
	public static void main(String[] args) {
		SecureRandom random = new SecureRandom();
		String randomCode = new BigInteger(30, random).toString(32).toUpperCase();
		System.out.println(randomCode);
	}
	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
}
