package interfaces.timestamp;

import interfaces.hasheddata.IHashedDataRecuperable;

public interface ITimestampedDataRecuperable<T> {
	public long timestamp();
	public IHashedDataRecuperable<T> hashedData();
}
