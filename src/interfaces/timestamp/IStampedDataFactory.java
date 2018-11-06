package interfaces.timestamp;

import org.json.simple.JSONObject;

import hashgenerator.IHashGenerator;

public interface IStampedDataFactory<T, R> {
	public ITimestampedDataRecuperable<R> createStampedData(JSONObject jsonObject);
	
	public void toJSON(ITimestampedData<T> stampedData, JSONObject jsonObject) ;
	public void setGeneradorHash(IHashGenerator<T> generadorHash);
}
