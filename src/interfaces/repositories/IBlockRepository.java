package interfaces.repositories;

import java.util.Collection;

import org.json.simple.parser.ParseException;

import interfaces.block.IBlock;
import interfaces.block.IBlockData;
import interfaces.block.IBlockDataFactory;
import interfaces.filter.IDataFilter;

public interface IBlockRepository<T extends IBlock> {

	void add(T dato);
	String getLastBlockHash();
	
	<R>void  getAll(Collection<IBlockData<R>> bloques, IBlockDataFactory<R> factory) throws ParseException;	
	<R>void  getAll(Collection<IBlockData<R>> bloques, IBlockDataFactory<R> factory, IDataFilter<R> filter) throws ParseException;	

}
