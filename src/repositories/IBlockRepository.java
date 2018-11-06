package repositories;

import java.util.Collection;

import org.json.simple.parser.ParseException;

import interfaces.block.IBlock;
import interfaces.block.IBlockData;
import interfaces.block.IBlockFactory;

public interface IBlockRepository<T extends IBlock> {

	void add(T dato);
	
	<S, R>void  getAll(Collection<IBlockData<R>> bloques, IBlockFactory<S, R> factory) throws ParseException;

	String getLastBlockHash();

}
