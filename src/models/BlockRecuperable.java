package models;
import interfaces.IBlock;
import interfaces.IBlockData;
import interfaces.ITimestampedData;

public class BlockRecuperable<R> extends BlockStoreable implements IBlockData<R>{
	private ITimestampedData<R> stampedData;
	
	public BlockRecuperable(IBlock block, ITimestampedData<R> iTimestampedData) {
		super(block.previousHash(), block.blockHash());
		this.setStampedData(iTimestampedData);
	}
	
	private ITimestampedData<R> getStampedData() {
		return stampedData;
	}

	private void setStampedData(ITimestampedData<R> stampedData) {
		this.stampedData = stampedData;
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
