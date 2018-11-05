package interfaces;

import org.json.simple.JSONObject;

import hashgenerator.IHashGenerator;

public interface IHashedDataFactory<T, R> {

	public IHashedData<R> createHashedData(JSONObject jsonObject);
	public void toJSON(IHashedData<T> hashedData, JSONObject jsonObject);
	public void setHashGenerator(IHashGenerator<T> generadorHash);

}
