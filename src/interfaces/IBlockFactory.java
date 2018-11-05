package interfaces;

import org.json.simple.parser.ParseException;

public interface IBlockFactory<T> {
	public T createFromBlock( IBlock block ) throws ParseException;		
}
