package interfaces.block;

import org.json.simple.parser.ParseException;

public interface IBlockDataFactory<R> {
	public IBlockData<R> createFromBlock( IBlock block ) throws ParseException;
}
