package interfaces;
public interface IHashedData<T>{
	public T data();
	public String hashAsString();
	String getDigestAlgorithm();	
}
