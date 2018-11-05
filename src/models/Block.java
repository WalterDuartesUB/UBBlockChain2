package models;
import interfaces.IBlock;
import interfaces.ITimestampedData;

public class Block<T> implements IBlock {

	private String previousHash;
	private String hash;
	private ITimestampedData<T> stampedData;
	
	public Block(IBlock block, ITimestampedData<T> stampedData) {
		this.setPreviousHash(block.previousHash());
		this.setHash(block.blockHash());
		this.setStampedData(stampedData);
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

	public ITimestampedData<T> getStampedData() {
		return stampedData;
	}

	private void setStampedData(ITimestampedData<T> stampedData) {
		this.stampedData = stampedData;
	}

	private String getHash() {
		return hash;
	}

	private void setHash(String hash) {
		this.hash = hash;
	}

	@Override
	public String toString() {
		return "Block [previousHash=" + previousHash + ", hash=" + hash + ", stampedData=" + stampedData + "]";
	}

}
