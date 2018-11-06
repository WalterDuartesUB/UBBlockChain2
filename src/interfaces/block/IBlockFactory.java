package interfaces.block;

import interfaces.timestamp.ITimestampedData;

public interface IBlockFactory<S> {
	public IBlock createBlockToPersist(String previousHash, ITimestampedData<S> stampedData);	
}
