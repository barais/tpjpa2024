package DAO;

import Model.Rdv;

import java.util.List;

public interface DAO<T> {

	public T getById(T t);

	void insert(T t);

	void delete(T t);
}
