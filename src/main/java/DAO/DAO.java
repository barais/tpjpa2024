package DAO;

import Model.Rdv;

import java.util.List;

public interface DAO<T> {

	void insert(T t);

	void delete(T t);
}
