package hibernate.Util;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.Entity.Company;
import hibernate.Entity.Department;
import hibernate.Entity.Employee;
import hibernate.Entity.Skill;

public class Application {
	public static void main(String[] args) {

		Department d = new Department();
		d.setDepartmentId(123);
		d.setDepartmentName("Software Professional");

		Employee emp = new Employee();
		emp.setEmployeeName("Akaash");
		emp.setEmployeeId(111);
		emp.setDesignation("Senior Software Engineer");
		emp.setSalary(50000);

		Employee emp1 = new Employee();
		emp1.setEmployeeName("Adhavan");
		emp1.setEmployeeId(112);
		emp1.setDesignation("Intermediate Software Engineer");
		emp1.setSalary(40000);

		Employee emp2 = new Employee();
		emp2.setEmployeeName("Goutham");
		emp2.setEmployeeId(113);
		emp2.setDesignation("Junior Software Engineer");
		emp2.setSalary(30000);

		Skill sk = new Skill();
		sk.setSkillId(1);
		sk.setSkillName("Coding");

		Skill sk1 = new Skill();
		sk1.setSkillId(2);
		sk1.setSkillName("Testing");

		Skill sk2 = new Skill();
		sk2.setSkillId(3);
		sk2.setSkillName("Automation");

		emp.getSkills().add(sk);
		emp1.getSkills().add(sk1);
		emp2.getSkills().add(sk2);

		d.getEmployees().add(emp);
		d.getEmployees().add(emp1);
		d.getEmployees().add(emp2);

		Company c = new Company();
		c.setCompanyId(0011);
		c.setCompanyName("Kovan Labs");

		c.getEmployees().add(emp);
		c.getEmployees().add(emp1);
		c.getEmployees().add(emp2);

		c.getDepartments().add(d);

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(c);
			session.save(d);
			session.save(emp);
			session.save(emp1);
			session.save(emp2);
			session.save(sk);
			session.save(sk1);
			session.save(sk2);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Employee> emps = session.createQuery("from Employee order by salary desc", Employee.class).list();
			System.out.println("The employees in sorted order of their salary:");
			emps.forEach(e -> System.out.println( e.getEmployeeName()));
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
}