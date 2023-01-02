package Model;

import java.io.*;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.*;
import java.util.*;

import javax.crypto.*;

public class RSA_Algorithm {
	private PrivateKey privateKey;
	private PublicKey publicKey;
	private Cipher cipher;

	public RSA_Algorithm() throws Exception {
		this.cipher = Cipher.getInstance("RSA/ECB/NoPadding");
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);
		KeyPair pair = generator.generateKeyPair();
		this.privateKey = pair.getPrivate();
		this.publicKey = pair.getPublic();
	}

	public String encryptString(String plaintext, String keypath) throws Exception {
		this.cipher.init(Cipher.ENCRYPT_MODE, loadPrivateKey(keypath));
		byte[] encryptBytes = cipher.doFinal(plaintext.getBytes());
		return encode(encryptBytes);
	}

	public String encryptStringWithKeyString(String plaintext, String key) throws Exception {
		PrivateKey privatekey = loadPrivateKeyString(key);
				this.cipher.init(Cipher.ENCRYPT_MODE, privatekey);
		byte[] encryptBytes = cipher.doFinal(plaintext.getBytes());
		return encode(encryptBytes);
	}

	public String decryptString(String encryptText, String keypath) throws Exception {
		this.cipher.init(Cipher.DECRYPT_MODE, loadPublicKey(keypath));
		byte[] decryptBytes = cipher.doFinal(decode(encryptText));
		return new String(decryptBytes, "UTF8");
	}

	public PrivateKey loadPrivateKeyString(String stored) throws GeneralSecurityException, IOException {
		byte[] data = decode(stored);
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(data);
		KeyFactory fact = KeyFactory.getInstance("RSA");
		return fact.generatePrivate(spec);
	}

	// Please put attention on the extension of the files
	// path likes: public.key
	public PublicKey loadPublicKey(String keyPath) throws Exception {
		/*
		 * File publicKeyFile = new File(keyPath); byte[] publicKeyBytes =
		 * Files.readAllBytes(publicKeyFile.toPath()); KeyFactory keyFactory =
		 * KeyFactory.getInstance("RSA");
		 */
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(keyPath));
		byte[] bytes = bis.readAllBytes();
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytes);
		return keyFactory.generatePublic(publicKeySpec);
	}

	// path like: private_key.pem
	public PrivateKey loadPrivateKey(String keyPath) throws GeneralSecurityException, IOException {
		/*
		 * File f = new File(keyPath); FileInputStream fis = new FileInputStream(f);
		 * DataInputStream dis = new DataInputStream(fis); byte[] keyBytes = new
		 * byte[(int) f.length()]; dis.readFully(keyBytes); dis.close();
		 */

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(keyPath));
		byte[] bytes = bis.readAllBytes();

		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);

	}

	public PrivateKey getPrivateKey_code() {
		return this.privateKey;
	}

	public PublicKey getPublicKey_code() {
		return this.publicKey;
	}

	public String getPrivateKey() {
		return encode(this.publicKey.getEncoded());
	}

	public String getPublicKey() {
		return encode(this.privateKey.getEncoded());
	}

	public String encode(byte[] data) {
		return Base64.getEncoder().encodeToString(data);
	}

	public byte[] decode(String data) {
		return Base64.getDecoder().decode(data);
	}

	public static void main(String[] args) throws Exception {
		RSA_Algorithm rsa = new RSA_Algorithm();
		System.out.println(rsa.encode(rsa.loadPrivateKey("C:\\Users\\DELL\\Downloads\\private-key-kh.key").getEncoded()));
	}
}
