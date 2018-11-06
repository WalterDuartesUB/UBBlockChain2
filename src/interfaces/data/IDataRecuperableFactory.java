package interfaces.data;

import org.json.simple.JSONObject;

public interface IDataRecuperableFactory<R> {
	public R createData(JSONObject jsonObject);
}
