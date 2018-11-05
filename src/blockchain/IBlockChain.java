package blockchain;
import java.util.Collection;

import interfaces.IBlockData;

public interface IBlockChain<T> {
	public void add(T data);
	public void getAll(Collection<IBlockData<T>> bloques);

}
