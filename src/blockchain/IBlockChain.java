package blockchain;
import java.util.Collection;

import models.Block;

public interface IBlockChain<T> {
	public void add(T data);
	public void getAll(Collection<Block<T>> bloques);

}
