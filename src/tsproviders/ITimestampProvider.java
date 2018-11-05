package tsproviders;
import interfaces.IHashedData;
import interfaces.ITimestampedData;

public interface ITimestampProvider<T> {
	public ITimestampedData<T> stamp( IHashedData<T> hashedData);
}
