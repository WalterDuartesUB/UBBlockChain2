package interfaces.hasheddata;

import org.json.simple.JSONObject;

import hashgenerator.IHashValidator;

public interface IHashedDataFactory<S, R> {

	public IHashedDataRecuperable<R> createHashedData(JSONObject jsonObject);
	public void toJSON(IHashedDataRecuperable<S> hashedData, JSONObject jsonObject);
	public void setHashValidator(IHashValidator generadorHash);

}
