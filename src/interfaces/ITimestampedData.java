package interfaces;
public interface ITimestampedData<T>{
	public long timestamp();
	public IHashedData<T> hashedData();	
}
