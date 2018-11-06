package interfaces.tsproviders;
import interfaces.hasheddata.IHashedData;
import interfaces.timestamp.ITimestampedData;

public interface ITimestampProvider {
	public <T> ITimestampedData<T> stamp( IHashedData<T> hashedData);
}
