package hashgenerator;

import interfaces.data.IDataHasheable;
import interfaces.hasheddata.IHashedData;

public interface IHashGenerator {
	<T extends IDataHasheable> IHashedData<T> hash( T data );
}
