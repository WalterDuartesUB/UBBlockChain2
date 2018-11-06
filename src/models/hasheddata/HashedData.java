package models.hasheddata;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

import interfaces.hasheddata.IHashedData;

public class HashedData<T> extends HashedDataRecuperable<T> implements IHashedData<T> {	
	private byte[] hash;
	private MessageDigest messageDigest;

	public HashedData(T data, MessageDigest messageDigest, byte[] hash) {
		super( data, DatatypeConverter.printHexBinary(hash).toUpperCase());
		
		this.setMessageDigest(messageDigest);		
		this.setHash(hash);
	}
	
	@Override
	public byte[] getHash() {
		return hash;
	}
	
	@Override
	public String getDigestAlgorithm() {
		return this.getMessageDigest().getAlgorithm();
	}
	
	private void setHash(byte[] hash) {
		this.hash = hash;
	}

	private MessageDigest getMessageDigest() {
		return messageDigest;
	}

	private void setMessageDigest(MessageDigest messageDigest) {
		this.messageDigest = messageDigest;
	}

}
