package tsproviders;
import interfaces.hasheddata.IHashedData;
import interfaces.timestamp.ITimestampedData;
import interfaces.tsproviders.ITimestampProvider;
import models.timestamp.TimestampedData;

public class TimestampProviderURL implements ITimestampProvider{

	@Override
	public <T> ITimestampedData<T> stamp(IHashedData<T> hashedData) {	
		
		/*
		 * Utilizo el mismo algoritmo de para generar el hash para reportarlo al 
		 * servicio de rfc 3161 para que queden atados univocamente
		 * 
		 * hashedData.getDigestAlgorithm();
		 */
			
		hashedData.getDigestAlgorithm();
		hashedData.getHash();
		
		return new TimestampedData<T>(hashedData, System.currentTimeMillis());
	}

}
