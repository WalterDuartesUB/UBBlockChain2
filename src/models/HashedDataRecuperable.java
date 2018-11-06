package models;

import interfaces.hasheddata.IHashedDataRecuperable;

public class HashedDataRecuperable<R> implements IHashedDataRecuperable<R> {
	private String dataHash;
	private R data;
	
	public HashedDataRecuperable(R data, String blockHash) {
		this.data(data);
		this.dataHash(blockHash);
	}

	@Override
	public R data() {
		return this.data;
	}

	@Override
	public String hashAsString() {
		return this.dataHash();
	}

	private String dataHash() {
		return dataHash;
	}

	private void dataHash(String dataHash) {
		this.dataHash = dataHash;
	}

	private void data(R data) {
		this.data = data;
	}

}
