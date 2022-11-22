package Model;

import java.io.*;
import java.math.*;
import java.security.*;

public class SHA512_Hashing {
	 private MessageDigest md;

	    public SHA512_Hashing() throws NoSuchAlgorithmException {
	        this.md = MessageDigest.getInstance("SHA-512");
	    }

	    public String hashText(String text) {
	        byte[] data = this.md.digest(text.getBytes());
	        BigInteger convertText = new BigInteger(1, data);
	        return convertText.toString(16);
	    }

	    public String hashFile(String filePath) throws FileNotFoundException, IOException {
	        FileInputStream fis = new FileInputStream(filePath);
	        BufferedInputStream bis = new BufferedInputStream(fis);
	        DigestInputStream dis = new DigestInputStream(bis, this.md);
	        byte[] bytes = new byte[1024];
	        int i = -1;
	        do {
	            i = dis.read(bytes);
	        } while (i != -1);

	        bis.close();
	        dis.close();
	        BigInteger convertText = new BigInteger(1, dis.getMessageDigest().digest());
	        return convertText.toString(16);
	    }
}
