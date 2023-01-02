package digital_signature;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
	MessageDigest md;
	private String hash;

	public Hash() {
		
		this.hash = "SHA-512";
	}

	public String getHash() {
		return this.hash;
	}

	public void setHash(final String hash) {
		this.hash = hash;
	}

	public String hashText(final String data) throws NoSuchAlgorithmException {
		this.md = MessageDigest.getInstance(this.hash);
		final byte[] out = this.md.digest(data.getBytes());
		final BigInteger bi = new BigInteger(1, out);
		return bi.toString(16);
	}

	public String hashFile(final String path) throws NoSuchAlgorithmException, IOException {
		this.md = MessageDigest.getInstance(this.hash);
		final File file = new File(path);
		final DigestInputStream dis = new DigestInputStream(new BufferedInputStream(new FileInputStream(file)),
				this.md);
		final byte[] buff = new byte[1024];
		int i;
		do {
			i = dis.read(buff);
		} while (i != -1);
		final BigInteger bigInteger = new BigInteger(1, dis.getMessageDigest().digest());
		dis.close();
		return bigInteger.toString(16);
	}
	public String hashByte(final byte[] bytes) throws NoSuchAlgorithmException, IOException {
		this.md = MessageDigest.getInstance(this.hash);
		final byte[] out = this.md.digest(bytes);
		final BigInteger bi = new BigInteger(1, out);
		return bi.toString(16);
	}

	public static void main(final String[] args) {
	}
}
