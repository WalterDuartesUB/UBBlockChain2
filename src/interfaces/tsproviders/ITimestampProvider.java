package interfaces.tsproviders;
import interfaces.hasheddata.IHashedData;
import interfaces.timestamp.ITimestampedData;

public interface ITimestampProvider<T> {
	public ITimestampedData<T> stamp( IHashedData<T> hashedData);
}
