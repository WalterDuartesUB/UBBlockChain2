package interfaces.timestamp;

import org.json.simple.JSONObject;

import hashgenerator.IHashValidator;

public interface ITimestampedDataFactory<S, R> {
	public ITimestampedDataRecuperable<R> createStampedData(JSONObject jsonObject);	
	public void toJSON(ITimestampedData<S> stampedData, JSONObject jsonObject) ;
	public void setHashValidator(IHashValidator generadorHash);
}
