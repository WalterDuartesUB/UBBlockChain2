package interfaces.hasheddata;

import org.json.simple.JSONObject;

import hashgenerator.IHashGenerator;
import models.IHashedDataRecuperable;

public interface IHashedDataFactory<T, R> {

	public IHashedDataRecuperable<R> createHashedData(JSONObject jsonObject);
	public void toJSON(IHashedData<T> hashedData, JSONObject jsonObject);
	public void setHashGenerator(IHashGenerator<T> generadorHash);

}
