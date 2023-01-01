package utitls;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Test {
	public static void main(String[] args) {
		Date date = new Date("2023-01-01 00:37:28");
		System.out.println(date);
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
