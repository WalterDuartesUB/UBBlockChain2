package interfaces;

import org.json.simple.JSONObject;

public interface IHashedDataFactory<T> {

	public IHashedData<T> createHashedData(JSONObject jsonObject);
	public void toJSON(IHashedData<T> hashedData, JSONObject jsonObject);

}
