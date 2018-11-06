package models;

import interfaces.hasheddata.IHashedDataRecuperable;
import interfaces.timestamp.ITimestampedDataRecuperable;

public class TimestampedDataRecuperable<R> implements ITimestampedDataRecuperable<R> {
	private long	timestamp;
	private IHashedDataRecuperable<R> hashedData;
	
	public TimestampedDataRecuperable(IHashedDataRecuperable<R> hashedData, long timestamp) {
		this.timestamp(timestamp);
		this.hashedData(hashedData);
	}

	@Override
	public long timestamp() {
		return this.timestamp;
	}

	@Override
	public IHashedDataRecuperable<R> hashedData() {
		return this.hashedData;
	}

	private void hashedData(IHashedDataRecuperable<R> hashedData) {
		this.hashedData = hashedData;
	}

	private void timestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
