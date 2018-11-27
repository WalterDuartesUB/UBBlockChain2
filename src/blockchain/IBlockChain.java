package blockchain;
import java.util.Collection;

import interfaces.block.IBlockData;
import interfaces.data.IDataFactory;
import interfaces.filter.IDataFilter;

public interface IBlockChain<T, R> {
	public void add(T data);
	public void getAll(Collection<IBlockData<R>> bloques);
	public <R2>void getAll(Collection<IBlockData<R2>> bloques, IDataFactory<T, R2> factory);
	public <R2>void getAll(Collection<IBlockData<R2>> bloques, IDataFactory<T, R2> factory, IDataFilter<R2> filter);
}
