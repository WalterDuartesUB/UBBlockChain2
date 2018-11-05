package factories;
import org.json.simple.JSONObject;

import hashgenerator.IHashGenerator;
import interfaces.IDataFactory;
import interfaces.IHashedData;
import interfaces.IHashedDataFactory;
import models.HashedData;

public class HashedDataFactory<T, R> implements IHashedDataFactory<T, R>{
	
	private IDataFactory<T, R> factory;
	private IHashGenerator<T> hashGenerator;
	
	public HashedDataFactory( IDataFactory<T, R> factory )
	{
		this.setFactory(factory);
	}

	public IHashedData<R> createHashedData(JSONObject jsonObject) {		
		this.getHashGenerator().validate( jsonObject.get("hash").toString() );
		
		return new HashedData<R>( getFactory().createData(jsonObject), jsonObject.get("hash").toString() );
	}

	@SuppressWarnings("unchecked")
	public void toJSON(IHashedData<T> hashedData, JSONObject jsonObject) {
		jsonObject.put("hash", hashedData.hashAsString() );
		
		getFactory().toJSON( hashedData.data(), jsonObject );
		
	}

	public IDataFactory<T, R> getFactory() {
		return factory;
	}

	public void setFactory(IDataFactory<T, R> factory) {
		this.factory = factory;
	}

	public IHashGenerator<T> getHashGenerator() {
		return hashGenerator;
	}

	public void setHashGenerator(IHashGenerator<T> hashGenerator) {
		this.hashGenerator = hashGenerator;
	}

}
