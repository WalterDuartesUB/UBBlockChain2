package factories;
import org.json.simple.JSONObject;

import hashgenerator.IHashGenerator;
import interfaces.data.IDataFactory;
import interfaces.hasheddata.IHashedData;
import interfaces.hasheddata.IHashedDataFactory;
import interfaces.hasheddata.IHashedDataRecuperable;
import models.HashedDataRecuperable;

public class HashedDataFactory<T, R> implements IHashedDataFactory<T, R>{
	
	private IDataFactory<T, R> factory;
	private IHashGenerator<T> hashValidator;
	
	public HashedDataFactory( IDataFactory<T, R> factory )
	{
		this.setFactory(factory);
	}

	@Override
	public IHashedDataRecuperable<R> createHashedData(JSONObject jsonObject) {		
		this.getHashValidator().validate( jsonObject.get("hash").toString() );
		
		return new HashedDataRecuperable<R>( getFactory().createData(jsonObject), jsonObject.get("hash").toString() );
	}

	@SuppressWarnings("unchecked")
	@Override
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

	public IHashGenerator<T> getHashValidator() {
		return hashValidator;
	}

	public void setHashValidator(IHashGenerator<T> hashValidator) {
		this.hashValidator = hashValidator;
	}

}
