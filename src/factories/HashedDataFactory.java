package factories;
import org.json.simple.JSONObject;

import interfaces.IDataFactory;
import interfaces.IHashedData;
import interfaces.IHashedDataFactory;
import models.HashedData;

public class HashedDataFactory<T> implements IHashedDataFactory<T>{
	
	private IDataFactory<T> factory;
	
	public HashedDataFactory( IDataFactory<T> factory )
	{
		this.setFactory(factory);
	}

	public IHashedData<T> createHashedData(JSONObject jsonObject) {
		return new HashedData<T>( getFactory().createData(jsonObject), jsonObject.get("hash").toString() );
	}

	@SuppressWarnings("unchecked")
	public void toJSON(IHashedData<T> hashedData, JSONObject jsonObject) {
		jsonObject.put("hash", hashedData.hashAsString() );
		
		getFactory().toJSON( hashedData.data(), jsonObject );
		
	}

	public IDataFactory<T> getFactory() {
		return factory;
	}

	public void setFactory(IDataFactory<T> factory) {
		this.factory = factory;
	}

}
