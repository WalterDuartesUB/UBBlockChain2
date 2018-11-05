package cipher;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class BidiriectionalCipherAES implements IBidirectionalCipher{

	@Override
	public String encrypt(String dataToEncrypt) {
		try {
			return this.encrypt(dataToEncrypt.getBytes());
	
		} catch (Exception ex) {
			
		}
		return null;
	}

	@Override
	public String decrypt(String encrytedData) {
		
		try {
			return this.decrypt(this.getKey(), this.getInitVector(), encrytedData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private String encrypt(byte[] data) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		return this.encrypt(this.getKey(), this.getInitVector(), data);
	}

	private String getKey() {
		return "Bar12345Bar12345";
	}

	private String getInitVector() {
		return "RandomInitVector";
	}

	private String encrypt(String key, String initVector, byte[] value) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		IvParameterSpec iv = getInitVectorParam(initVector);
		SecretKeySpec skeySpec = getSecretKeySpec(key);

		Cipher cipher = getCipherAlgoritm();
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

		byte[] encrypted = cipher.doFinal(value);

		return Base64.getEncoder().encodeToString(encrypted);
	}

	private String decrypt(String key, String initVector, String encrypted) throws UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {	
		IvParameterSpec iv = getInitVectorParam(initVector);
		SecretKeySpec skeySpec = getSecretKeySpec(key);

		Cipher cipher = getCipherAlgoritm();
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

		byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
		return new String(original);
	}

	private SecretKeySpec getSecretKeySpec(String key) throws UnsupportedEncodingException {
		return new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	}

	private IvParameterSpec getInitVectorParam(String initVector) throws UnsupportedEncodingException {
		return new IvParameterSpec(initVector.getBytes("UTF-8"));
	}

	private Cipher getCipherAlgoritm() throws NoSuchAlgorithmException, NoSuchPaddingException {
		return Cipher.getInstance("AES/CBC/PKCS5PADDING");
	}
}
