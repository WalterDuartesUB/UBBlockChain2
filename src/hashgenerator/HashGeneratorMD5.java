package hashgenerator;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import interfaces.data.IDataHasheable;
import interfaces.hasheddata.IHashedData;
import models.hasheddata.HashedData;

public class HashGeneratorMD5 extends HashGenerator {

	@Override
	public <T extends IDataHasheable> IHashedData<T> hash(T data) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			return new HashedData<T>(data, md, md.digest( data.toBytes() ));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void validate(String hash) {
		// TODO validar que el hash sea un hexadecimal
		// No hay forma de validar contra el contenido, porque el contenido no existe en el bloquegit
	}
}
