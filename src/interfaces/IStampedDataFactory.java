package interfaces;

import org.json.simple.JSONObject;

public interface IStampedDataFactory<T> {
	public ITimestampedData<T> createStampedData(JSONObject jsonObject);
	public void toJSON(ITimestampedData<T> stampedData, JSONObject jsonObject) ;
}
