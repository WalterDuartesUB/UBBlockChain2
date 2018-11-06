package interfaces.hasheddata;

public interface IHashedData<T> extends IHashedDataRecuperable<T>{
	public String getDigestAlgorithm();
	public byte[] getHash();
}
