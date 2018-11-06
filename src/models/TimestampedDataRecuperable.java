package models;

import interfaces.timestamp.ITimestampedDataRecuperable;

public class TimestampedDataRecuperable<T> implements ITimestampedDataRecuperable<T> {
	private long	timestamp;
	private IHashedDataRecuperable<T> hashedData;
	
	public TimestampedDataRecuperable(IHashedDataRecuperable<T> hashedData, long timestamp) {
		this.timestamp(timestamp);
		this.hashedData(hashedData);
	}

	@Override
	public long timestamp() {
		return this.timestamp;
	}

	@Override
	public IHashedDataRecuperable<T> hashedData() {
		return this.hashedData;
	}

	private void hashedData(IHashedDataRecuperable<T> hashedData) {
		this.hashedData = hashedData;
	}

	private void timestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
