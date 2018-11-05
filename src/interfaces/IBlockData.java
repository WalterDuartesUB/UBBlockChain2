package interfaces;

public interface IBlockData<T> extends IBlock {
	public long timestamp();
	public String dataHash();
	public T data();
}
