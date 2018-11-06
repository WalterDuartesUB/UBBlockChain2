package interfaces.timestamp;

import interfaces.hasheddata.IHashedDataRecuperable;

public interface ITimestampedDataRecuperable<R> {
	public long timestamp();
	public IHashedDataRecuperable<R> hashedData();
}
