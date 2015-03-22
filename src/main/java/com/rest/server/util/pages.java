package com.rest.server.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.rest.model.Employee;
import com.rest.model.EmployeeRecord;
import com.rest.server.connection.ConnectionFactory;

@ManagedBean(name = "pages", eager = true)
@ApplicationScoped
public class pages {

	int endPage = 2;
	int currentPage = 0;
	final int rowCount = 3;
	ArrayList<Employee> emplist = new ArrayList<Employee>();

	public pages() {
		

	/*	try {
			File file = new File("D:/Anirban/employee.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(employees.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			employees ees = (employees) jaxbUnmarshaller.unmarshal(file);
			List<employee> elists = ees.getEmployees();
			System.out.print("-------------------");

			for (employee el : elists) {
				System.out.print(el.getId() + " " + el.getName() + " "
						+ el.getSalary() + "");
				emplist.add(el);
			}

		} catch (Exception ex) {
			System.out.print(ex.toString());

		}*/
		
		ConnectionFactory conn =ConnectionFactory.getInstance();
		EmployeeRecord record = conn.initClient().readEntity(EmployeeRecord.class);
		 for (Employee employee : record.getEmployee()) {
		      printEmployee(employee);
		    }

		
	}
	
	private static void printEmployee( Employee employee ) {
	    System.out.println("Employee Name : " + employee.getName());
	    System.out.println("Employee Id : " + employee.getId());
	    System.out.println("Employee Age :" + employee.getAge());
	    System.out.println("Employee Organization : " + employee.getOrganization());
	    System.out.println("Employee Exp : " + employee.getExperience());
	    System.out.println("Employee Skill : " + employee.getSkill());
	    System.out.println("Employee Address : " + employee.getAddress());
	    
	    /*System.out.println("Reviews: ");
	    for (Review review : book.reviews) {
	      System.out.println(review.content + " by " + review.author + " posted on " + review.getPostedOn());
	    }*/
	  }

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int rowCount) {
		System.out.print(endPage);
		this.endPage = rowCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		System.out.print(currentPage);
		this.currentPage = currentPage;
	}

	public ArrayList<Employee> getEmplist() {

		return emplist;

	}

	public int getRowCount() {
		return rowCount;
	}

	public void setpage() {
		int pageNum = 0;
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		pageNum = Integer.parseInt(externalContext.getRequestParameterMap()
				.get("currentPage"));
		if (pageNum <= 0) {
			this.currentPage = pageNum;
		} else {
			this.currentPage = (pageNum * rowCount) - rowCount;
		}
		this.endPage = this.currentPage + rowCount;

		System.out.print("--" + this.currentPage + "--" + this.endPage);
	}

}
