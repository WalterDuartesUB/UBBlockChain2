package interfaces;

import org.json.simple.JSONObject;

public interface IDataFactory<T> {
	public T createData(JSONObject jsonObject);
	public void toJSON(T data, JSONObject jsonObject);
}
