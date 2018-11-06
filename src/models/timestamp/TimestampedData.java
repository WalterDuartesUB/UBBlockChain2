package models.timestamp;

import interfaces.hasheddata.IHashedDataRecuperable;
import interfaces.timestamp.ITimestampedData;

public class TimestampedData<T> extends TimestampedDataRecuperable<T> implements ITimestampedData<T> {
	public TimestampedData(IHashedDataRecuperable<T> hashedData, long timestamp) {
		super(hashedData, timestamp);

	}
}
