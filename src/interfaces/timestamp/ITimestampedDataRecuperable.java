package interfaces.timestamp;

import models.IHashedDataRecuperable;

public interface ITimestampedDataRecuperable<T> {
	public long timestamp();
	public IHashedDataRecuperable<T> hashedData();
}
