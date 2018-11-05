package models;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

import interfaces.IHashedData;

public class HashedData<T> implements IHashedData<T> {

	private T data;
	private byte[] hash;
	private String hashAsString;
	private MessageDigest messageDigest;

	private HashedData(T data) {
		this.data(data);
	}

	public HashedData(T data, MessageDigest messageDigest, byte[] hash) {
		this(data);
		this.setMessageDigest(messageDigest);
		this.setHash(hash);
	}

	public HashedData(T data, String hash) {
		this(data);
		this.setHashAsString(hash);
	}

	@Override
	public T data() {
		return this.data;
	}

	public void data(T data) {
		this.data = data;
	}

	@Override
	public String hashAsString() {
		return getHashAsString();
	}

	@Override
	public String toString() {
		return "HashedData [data=" + data + "]";
	}

	public byte[] getHash() {
		return hash;
	}

	private void setHash(byte[] hash) {
		this.hash = hash;

		this.setHashAsString(DatatypeConverter.printHexBinary(this.getHash()).toUpperCase());
	}

	private String getHashAsString() {
		return hashAsString;
	}

	private void setHashAsString(String hashAsString) {
		this.hashAsString = hashAsString;
	}

	@Override
	public String getDigestAlgorithm() {
		return this.getMessageDigest().getAlgorithm();
	}

	private MessageDigest getMessageDigest() {
		return messageDigest;
	}

	private void setMessageDigest(MessageDigest messageDigest) {
		this.messageDigest = messageDigest;
	}

}
