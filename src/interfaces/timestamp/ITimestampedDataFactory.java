package interfaces.timestamp;

import org.json.simple.JSONObject;

import hashgenerator.IHashGenerator;

public interface ITimestampedDataFactory<S, R> {
	public ITimestampedDataRecuperable<R> createStampedData(JSONObject jsonObject);	
	public void toJSON(ITimestampedData<S> stampedData, JSONObject jsonObject) ;
	public void setHashValidator(IHashGenerator<S> generadorHash);
}
