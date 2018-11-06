package factories;
import org.json.simple.JSONObject;

import hashgenerator.IHashGenerator;
import interfaces.hasheddata.IHashedDataFactory;
import interfaces.timestamp.ITimestampedDataFactory;
import interfaces.timestamp.ITimestampedData;
import interfaces.timestamp.ITimestampedDataRecuperable;
import models.TimestampedDataRecuperable;

public class StampedDataFactory<T,R> implements ITimestampedDataFactory<T, R>{	

	private IHashedDataFactory<T, R> factory;
	
	public StampedDataFactory( IHashedDataFactory<T, R> factory) {
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
	public void setGeneradorHash(IHashGenerator<T> generadorHash) {
		this.getFactory().setHashGenerator( generadorHash );
		
	}

}
