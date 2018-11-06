package hashgenerator;

import interfaces.hasheddata.IHashedData;

public interface IHashGenerator<T> {
	IHashedData<T> hash( T data );

	void validate(String hash);
}
