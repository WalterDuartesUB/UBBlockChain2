package interfaces.timestamp;

import interfaces.hasheddata.IHashedData;

public interface ITimestampedData<T>{
	public long timestamp();
	public IHashedData<T> hashedData();	
}
