package interfaces.repositories;

import java.util.Collection;

import org.json.simple.parser.ParseException;

import interfaces.block.IBlock;
import interfaces.block.IBlockData;
import interfaces.block.IBlockDataFactory;

public interface IBlockRepository<T extends IBlock> {

	void add(T dato);
	
	<R>void  getAll(Collection<IBlockData<R>> bloques, IBlockDataFactory<R> factory) throws ParseException;

	String getLastBlockHash();

}
