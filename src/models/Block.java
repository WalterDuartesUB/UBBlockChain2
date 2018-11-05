package models;
import interfaces.IBlock;
import interfaces.IBlockData;
import interfaces.ITimestampedData;

public class Block<R> implements IBlockData<R>{

	private String previousHash;
	private String hash;
	private ITimestampedData<R> stampedData;
	
	public Block(IBlock block, ITimestampedData<R> iTimestampedData) {
		this.setPreviousHash(block.previousHash());
		this.setHash(block.blockHash());
		this.setStampedData(iTimestampedData);
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

	private ITimestampedData<R> getStampedData() {
		return stampedData;
	}

	private void setStampedData(ITimestampedData<R> stampedData) {
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
	
	@Override
	public R data()
	{
		return this.getStampedData().hashedData().data();
	}

	@Override
	public long timestamp() {
		return this.getStampedData().timestamp();
	}
	
	@Override
	public String dataHash() {
		return this.getStampedData().hashedData().hashAsString();
	}
}
