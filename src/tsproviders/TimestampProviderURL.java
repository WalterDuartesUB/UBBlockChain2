package tsproviders;
import interfaces.hasheddata.IHashedData;
import interfaces.timestamp.ITimestampedData;
import models.TimestampedData;

public class TimestampProviderURL<T> implements ITimestampProvider<T>{

	@Override
	public ITimestampedData<T> stamp(IHashedData<T> hashedData) {	
		
		/*
		 * Utilizo el mismo algoritmo de para generar el hash para reportarlo al 
		 * servicio de rfc 3161 para que queden atados univocamente
		 * 
		 * hashedData.getDigestAlgorithm();
		 */
		
		return new TimestampedData<T>(hashedData, System.currentTimeMillis());
	}

}
