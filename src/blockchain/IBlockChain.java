package blockchain;
import java.util.Collection;

import interfaces.block.IBlockData;

public interface IBlockChain<T, R> {
	public void add(T data);
	public void getAll(Collection<IBlockData<R>> bloques);

}
