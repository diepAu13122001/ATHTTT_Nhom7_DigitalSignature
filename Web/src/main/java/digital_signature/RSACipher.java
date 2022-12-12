package digital_signature;

import java.util.Base64;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.crypto.CipherInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import javax.crypto.NoSuchPaddingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.security.PrivateKey;

public class RSACipher {
	int[] keySizes;
	int keySize;
	PrivateKey privateKey;
	PublicKey publicKey;
	Cipher cipher;
	boolean encryptByPublicKey = true;
	boolean decryptByPrivateKey = true;

	public RSACipher() {
		this.keySizes = new int[] { 2048, 1024 };
		this.keySize = this.keySizes[0];

	}

	public boolean isEncryptByPublicKey() {
		return encryptByPublicKey;
	}

	public void setEncryptByPublicKey(boolean encryptByPublicKey) {
		this.encryptByPublicKey = encryptByPublicKey;
	}

	public boolean isDecryptByPrivateKey() {
		return decryptByPrivateKey;
	}

	public void setDecryptByPrivateKey(boolean decryptByPrivateKey) {
		this.decryptByPrivateKey = decryptByPrivateKey;
	}

	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}

	public int[] getKeySizes() {
		return this.keySizes;
	}

	public int getKeySize() {
		return this.keySize;
	}

	public void setKeySize(final int keySize) {
		this.keySize = keySize;
	}

	public void setPrivateKey(final PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public PublicKey getPublicKey() {
		return this.publicKey;
	}

	public void setPublicKey(final PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public void createKey() throws NoSuchAlgorithmException {
		final KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(this.keySize);
		final KeyPair keyPair = generator.generateKeyPair();
		this.publicKey = keyPair.getPublic();
		this.privateKey = keyPair.getPrivate();
	}

	public byte[] encrypt(byte[] input, PrivateKey privateKey) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		final byte[] bytes = cipher.doFinal(input);
		return bytes;
	}

	public String decrypt(byte[] input, PublicKey publicKey) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		final byte[] bytes = cipher.doFinal(input);
		return new String(bytes);
	}

	public void writeByte(final DataOutputStream out, final byte[] bytes) throws IOException {
		for (final byte b : bytes) {
			out.writeByte(b);
		}
	}

	public byte[] readByte(final DataInputStream dis, final int length) throws IOException {
		final byte[] bytes = new byte[length];
		for (int i = 0; i < bytes.length; ++i) {
			bytes[i] = dis.readByte();
		}
		return bytes;
	}

	public PublicKey publicKeyType(String keyString) throws InvalidKeySpecException, NoSuchAlgorithmException {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(keyString.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;

	}
	public PublicKey publicKeyFile(byte[] bytes) throws InvalidKeySpecException, NoSuchAlgorithmException {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;

	}

}