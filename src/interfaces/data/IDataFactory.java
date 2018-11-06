package interfaces.data;

import org.json.simple.JSONObject;

public interface IDataFactory<T, R> {
	public R createData(JSONObject jsonObject);
	public void toJSON(T data, JSONObject jsonObject);
}
