package interfaces.filter;

public class DataFilterAcceptAll<T> implements IDataFilter<T> {

	@Override
	public boolean accept(T data) {
		return true;
	}

}
