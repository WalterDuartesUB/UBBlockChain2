package models;

import interfaces.IBlock;

public class AnotherBlock implements IBlock {
	private String previousHash;
	private String hash;
	
	public AnotherBlock(String previousHash, String hash) {
		this.setHash(hash);
		this.setPreviousHash(previousHash);
	}

	@Override
	public String previousHash() {
		return this.getPreviousHash();
	}

	@Override
	public String blockHash() {
		return this.getHash();
	}

	private String getPreviousHash() {
		return previousHash;
	}

	private void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	private String getHash() {
		return hash;
	}

	private void setHash(String hash) {
		this.hash = hash;
	}

	@Override
	public String toString() {
		return "AnotherBlock [previousHash=" + previousHash + ", hash=" + hash + "]";
	}	
	
}
