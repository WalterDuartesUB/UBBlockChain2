package interfaces.filter;

public interface IDataFilter<T> {
	boolean accept( T data);
}
