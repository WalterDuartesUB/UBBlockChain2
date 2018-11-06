package interfaces.data;

import org.json.simple.JSONObject;

public interface IDataFactory<S, R> {
	public R createData(JSONObject jsonObject);
	public void toJSON(S data, JSONObject jsonObject);
}
