package interfaces.hasheddata;

import models.IHashedDataRecuperable;

public interface IHashedData<T> extends IHashedDataRecuperable<T>{
	public String getDigestAlgorithm();
	public byte[] getHash();
}
