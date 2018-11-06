package interfaces.data;

import org.json.simple.JSONObject;

public interface IDataFactory<S, R> extends IDataRecuperableFactory<R>{
	public void toJSON(S data, JSONObject jsonObject);
}
