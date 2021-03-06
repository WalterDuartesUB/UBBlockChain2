package repositories;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.json.simple.parser.ParseException;

import interfaces.block.IBlock;
import interfaces.block.IBlockData;
import interfaces.block.IBlockDataFactory;
import interfaces.filter.IDataFilter;
import interfaces.repositories.IBlockRepository;

public class BlockRepository<T extends IBlock> implements IBlockRepository<T>{
	private Collection<T>	bloques;
	
	public BlockRepository()
	{
		this.setBloques( new LinkedList<T>() );
	}
	
	@Override
	public void add(T dato)
	{
		this.getBloques().add( dato );
	}
	
	@Override
	public <R> void getAll( Collection<IBlockData<R>> bloques, IBlockDataFactory<R> factory ) throws ParseException
	{		
		for( T block : this.getBloques())
			bloques.add( factory.createFromBlock(block) );
	}
	
	private Collection<T> getBloques() {
		return bloques;
	}
	
	private void setBloques(Collection<T> datos) {
		this.bloques = datos;
	}

	@Override
	public String getLastBlockHash() {
		String hash = "";
		
		try {
	        final Iterator<T> itr = this.getBloques().iterator();
	        T lastElement = itr.next();

	        while(itr.hasNext()) {
	            lastElement=itr.next();
	        }

	        hash = lastElement.blockHash();	
		} catch (NoSuchElementException e) {
			hash = "000000000";
		}
		
		return hash;
	}

	@Override
	public <R> void getAll(Collection<IBlockData<R>> bloques, IBlockDataFactory<R> factory, IDataFilter<R> filter) throws ParseException {
		for( T block : this.getBloques()) {
			IBlockData<R> blockData = factory.createFromBlock(block);			
			if( filter.accept( blockData.data() ) )
				bloques.add( blockData );
		}
	}
}
