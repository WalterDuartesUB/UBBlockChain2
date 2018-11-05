package hashgenerator;

import interfaces.IHashedData;

public interface IHashGenerator<T> {
	IHashedData<T> hash( T data );

	void validate(String hash);
}
