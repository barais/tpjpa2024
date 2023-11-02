package DAO;

public interface DAO<T> {

	T getById(Long t);

	void insert(T t);

	void delete(T t);
}
