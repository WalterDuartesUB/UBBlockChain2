package models;

public interface IHashedDataRecuperable<T> {
	public T data();
	public String hashAsString();
}
