package jpa.CRUD;

import domain.Department;
import domain.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class EmployeeCRUD {
    private EntityManager manager;

    public EmployeeCRUD(EntityManager manager) {
        this.manager = manager;
    }

    public void createEmployee(String name, String departmentName, boolean persist) {
        TypedQuery<Employee> selectByName =  manager.createQuery("Select a From Employee a WHERE a.name = ?1", Employee.class);
        selectByName.setParameter(1, name);
        int count = selectByName.getResultList().size();
        if (count > 0) {
            System.out.println("Employee already exists");
            return;
        }

        TypedQuery<Department> selectDepartmentByName =  manager.createQuery("Select a From Department a WHERE a.name = ?1", Department.class);
        selectDepartmentByName.setParameter(1, departmentName);
        count = selectDepartmentByName.getResultList().size();
        if (count == 0) {
            System.out.println("Department does not exist");
            return;
        }

        Department department = selectDepartmentByName.getSingleResult();
        Employee employee = new Employee(name, department);
        if(persist) {
            manager.persist(employee);
        }
    }

    public void updateEmployeeName(String oldName, String newName) {
        Query updateQuery = manager.createQuery("UPDATE Employee e SET e.name = ?1 WHERE e.name = ?2");
        updateQuery.setParameter(1, newName);
        updateQuery.setParameter(2, oldName);
        updateQuery.executeUpdate();
    }

    public void deleteEmployee(String name) {
        Query deleteQuery = manager.createQuery("DELETE FROM Employee e WHERE e.name = ?1");
        deleteQuery.setParameter(1, name);
        deleteQuery.executeUpdate();
    }

    public List<Employee> listEmployees() {
        TypedQuery<Employee> selectAll =  manager.createQuery("Select a From Employee a", Employee.class);
        return selectAll.getResultList();
    }

    public void createDepartment(String name, boolean persist) {
        TypedQuery<Department> selectByName =  manager.createQuery("Select a From Department a WHERE a.name = ?1", Department.class);
        selectByName.setParameter(1, name);
        int count = selectByName.getResultList().size();
        if (count > 0) {
            System.out.println("Department already exists");
            return;
        }

        Department department = new Department(name);
        if(persist) {
            manager.persist(department);
        }
    }

    public void updateDepartmentName(String oldName, String newName) {
        Query updateQuery = manager.createQuery("UPDATE Department d SET d.name = ?1 WHERE d.name = ?2");
        updateQuery.setParameter(1, newName);
        updateQuery.setParameter(2, oldName);
        updateQuery.executeUpdate();
    }

    public void deleteDepartment(String name) {
        Query deleteQuery = manager.createQuery("DELETE FROM Department d WHERE d.name = ?1");
        deleteQuery.setParameter(1, name);
        deleteQuery.executeUpdate();
    }

    public List<Department> listDepartments() {
        TypedQuery<Department> selectAll =  manager.createQuery("Select a From Department a", Department.class);
        return new ArrayList<>(selectAll.getResultList());
    }

    public void printEmployees() {
        List<Employee> employees = listEmployees();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public void printDepartments() {
        List<Department> departments = listDepartments();
        for (Department department : departments) {
            System.out.println(department);
        }
    }
}
