package models.block;
import interfaces.block.IBlock;
import interfaces.block.IBlockData;
import interfaces.timestamp.ITimestampedDataRecuperable;

public class BlockRecuperable<R> extends BlockStoreable implements IBlockData<R>{
	private ITimestampedDataRecuperable<R> stampedData;
	
	public BlockRecuperable(IBlock block, ITimestampedDataRecuperable<R> iTimestampedData) {
		super(block.previousHash(), block.blockHash());
		this.setStampedData(iTimestampedData);
	}
	
	private ITimestampedDataRecuperable<R> getStampedData() {
		return stampedData;
	}

	private void setStampedData(ITimestampedDataRecuperable<R> stampedData) {
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
