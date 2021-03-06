package factories;
import org.json.simple.JSONObject;

import hashgenerator.IHashValidator;
import interfaces.hasheddata.IHashedDataFactory;
import interfaces.timestamp.ITimestampedData;
import interfaces.timestamp.ITimestampedDataFactory;
import interfaces.timestamp.ITimestampedDataRecuperable;
import models.timestamp.TimestampedDataRecuperable;

public class TimestampedDataFactory<T,R> implements ITimestampedDataFactory<T, R>{	

	private IHashedDataFactory<T, R> factory;
	
	public TimestampedDataFactory( IHashedDataFactory<T, R> factory) {
		this.setFactory(factory);
	}
	
	public ITimestampedDataRecuperable<R> createStampedData(JSONObject jsonObject) {
		return new TimestampedDataRecuperable<R>( getFactory().createHashedData( jsonObject ), Long.parseLong( jsonObject.get("timestamp").toString() ) );
	}

	@SuppressWarnings("unchecked")
	public void toJSON(ITimestampedData<T> stampedData, JSONObject jsonObject) {
		jsonObject.put("timestamp", String.valueOf( stampedData.timestamp() ) );
		
		getFactory().toJSON( stampedData.hashedData(), jsonObject );
	}

	public IHashedDataFactory<T, R> getFactory() {
		return factory;
	}

	public void setFactory(IHashedDataFactory<T, R> factory) {
		this.factory = factory;
	}

	@Override
	public void setHashValidator(IHashValidator hashValidator) {
		this.getFactory().setHashValidator( hashValidator );
		
	}

}
