package interfaces.hasheddata;

import org.json.simple.JSONObject;

import hashgenerator.IHashGenerator;

public interface IHashedDataFactory<S, R> {

	public IHashedDataRecuperable<R> createHashedData(JSONObject jsonObject);
	public void toJSON(IHashedData<S> hashedData, JSONObject jsonObject);
	public void setHashGenerator(IHashGenerator<S> generadorHash);

}
