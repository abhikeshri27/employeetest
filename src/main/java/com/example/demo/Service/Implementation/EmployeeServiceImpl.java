package com.example.demo.Service.Implementation;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Employee;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.Service.EmployeeService;
import com.example.demo.Exception.EmployeeException;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
		private EmployeeRepository emprepository;

		@Override
		public Employee addEmployee(Employee employee) {
			if(emprepository.existsById(employee.getEid())) {
				throw new EmployeeException("Employee Already Exists");
			}
			return emprepository.save(employee);
		}
		
		
		 @Override
               public List<Employee> getEmployees() {
            	return emprepository.findAll();
           }

         @Override
          public Employee updateEmployee(Integer Eid, Employee employee)
         {
	        Employee emp = emprepository.findById(Eid).get();
	
	       if(0 < employee.getSalary()) {
		   emp.setSalary(employee.getSalary());
	           }
	
	    return emprepository.save(emp);
      }


         @Override
        public List<Employee> descendingSalary() {
	    return emprepository.findByOrderBySalaryDesc();
        }

        @Override
      public List<Employee> getEmployeesBySorting() {
	  List<Employee> emplist = emprepository.findAll(Sort.by("salary").descending().and(Sort.by("name")));
	  return emplist;
      }


		


		
     }
