package cipher;

public interface IBidirectionalCipher {
	public String encrypt(String dataToEncrypt);
	public String decrypt(String encrytedData);
}
