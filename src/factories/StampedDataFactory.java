package factories;
import org.json.simple.JSONObject;

import hashgenerator.IHashGenerator;
import interfaces.IHashedDataFactory;
import interfaces.IStampedDataFactory;
import interfaces.ITimestampedData;
import models.TimestampedData;

public class StampedDataFactory<T> implements IStampedDataFactory<T>{	

	private IHashedDataFactory<T> factory;
	
	public StampedDataFactory( IHashedDataFactory<T> factory) {
		this.setFactory(factory);
	}
	
	public ITimestampedData<T> createStampedData(JSONObject jsonObject) {
		return new TimestampedData<T>( getFactory().createHashedData( jsonObject ), Long.parseLong( jsonObject.get("timestamp").toString() ) );
	}

	@SuppressWarnings("unchecked")
	public void toJSON(ITimestampedData<T> stampedData, JSONObject jsonObject) {
		jsonObject.put("timestamp", String.valueOf( stampedData.timestamp() ) );
		
		getFactory().toJSON( stampedData.hashedData(), jsonObject );
	}

	public IHashedDataFactory<T> getFactory() {
		return factory;
	}

	public void setFactory(IHashedDataFactory<T> factory) {
		this.factory = factory;
	}

	@Override
	public void setGeneradorHash(IHashGenerator<T> generadorHash) {
		this.getFactory().setHashGenerator( generadorHash );
		
	}

}
