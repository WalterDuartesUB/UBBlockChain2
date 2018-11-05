package repositories;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.json.simple.parser.ParseException;

import factories.BlockFactory;
import interfaces.IBlock;
import interfaces.IBlockData;

public class BlockRepository<T extends IBlock>{
	private Collection<T>	bloques;
	
	public BlockRepository()
	{
		this.setBloques( new LinkedList<T>() );
	}
	
	public void add(T dato)
	{
		this.getBloques().add( dato );
	}
		
	public <T2, R> void getAll( Collection<IBlockData<R>> bloques, BlockFactory<T2, R> factory ) throws ParseException
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
}
