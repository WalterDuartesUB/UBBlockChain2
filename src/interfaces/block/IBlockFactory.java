package interfaces.block;

import org.json.simple.parser.ParseException;

import interfaces.timestamp.ITimestampedData;

public interface IBlockFactory<S,R> {
	public IBlock createBlockToPersist(String previousHash, ITimestampedData<S> stampedData);
	public IBlockData<R> createFromBlock( IBlock block ) throws ParseException;
}
