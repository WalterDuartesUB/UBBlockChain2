package models.timestamp;

import interfaces.hasheddata.IHashedData;
import interfaces.timestamp.ITimestampedData;

public class TimestampedData<T> implements ITimestampedData<T> {

	private IHashedData<T> hashedData;
	private long timestamp;

	public TimestampedData(IHashedData<T> hashedData, long timestamp) {
		this.setHashedData(hashedData);
		this.timestamp(timestamp);
	}

	public IHashedData<T> getHashedData() {
		return hashedData;
	}

	public void setHashedData(IHashedData<T> hashedData) {
		this.hashedData = hashedData;
	}

	@Override
	public long timestamp() {
		return this.timestamp;
	}

	public void timestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public IHashedData<T> hashedData() {
		return this.getHashedData();
	}

}
