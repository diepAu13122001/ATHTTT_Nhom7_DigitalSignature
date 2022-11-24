import java.io.*;

import Model.*;

public class Run {
	public static void main(String[] args) throws Exception{
		// create pdf file
		File file = new File("D:\\bill.pdf");
		file.delete();
		
		if (file.createNewFile()) {
			// write on file
			String text = "Hello mấy cưng, chụy Linda nè!";
            DAO dao = new DAO();
            dao.writeFileTXT(text, file);
			
			// hash the pdf file
		SHA512_Hashing sha = new SHA512_Hashing();
		String hashing = sha.hashFile(file.getAbsolutePath());
		System.out.println(hashing);
		
		
		// encrypt the hashing
		RSA_Algorithm rsa = new RSA_Algorithm(1024); //  or 2028 for size
		try (FileOutputStream fos = new FileOutputStream("public.key")) {
			fos.write(rsa.getPublicKey_code().getEncoded());
		}
		try (FileOutputStream fos = new FileOutputStream("private_key.pem")) {
			fos.write(rsa.getPrivateKey_code().getEncoded());
		}
		
		
		System.out.println("Encrypt: " + rsa.encryptString(hashing, "private_key.pem"));
		//System.out.println("Encrypt: " + rsa.decryptString(rsa.encryptString(hashing, "private_key.pem"), "public.key"));

		
		} else {
			System.out.println("File is not found!");
		}
	}

}
