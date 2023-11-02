package DAO;

import Model.Rdv;

import java.util.List;

public interface DAO<T> {

	public T getById(Long t);

	void insert(T t);

	void delete(T t);
}
