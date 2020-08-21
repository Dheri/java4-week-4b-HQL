/**
 * 
 */
package in.parteek.dao;

import java.util.*;
import javax.persistence.criteria.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import in.parteek.beans.*;

/**
 * Created on : 2019-01-31, 8:22:42 a.m.
 *
 * @author Parteek Dheri
 */
public class Dao {
	SessionFactory sessionFactory = new Configuration().configure("in/parteek/config/hibernate.cfg.xml")
			.buildSessionFactory();

	public void generateStudents() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		for (int i = 1; i < +10; i++) {
			Student s = new Student("Joe" + i, "prog" + (i * 7));
			session.save(s);
		}
		session.getTransaction().commit();
		session.close();
	}
//
//	public List<Student> getStudents() {
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//
//		List<Student> students = session.createQuery("from Student").getResultList();
//
//		session.getTransaction().commit();
//		session.close();
//
//		return students;
//	}

	public List<Student> getStudents() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Student> criteria = cb.createQuery(Student.class);
		Root<Student> root = criteria.from(Student.class);
		List<Student> students = session.createQuery(criteria).getResultList();

		session.getTransaction().commit();
		session.close();

//		System.out.println("***** ->" + students.size());
		return students;
	}

//	public List<Student> getStudentsLessFive() {
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//
//		List<Student> students = session.createQuery("from Student where id <= 5").getResultList();
//
//		session.getTransaction().commit();
//		session.close();
//
//		return students;
//	}

	public List<Student> getStudentsLessFive() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Student> criteria = cb.createQuery(Student.class);
		Root<Student> root = criteria.from(Student.class);

		criteria.where(cb.lessThanOrEqualTo(root.get("student_id"), 5));

		List<Student> students = session.createQuery(criteria).getResultList();

		session.getTransaction().commit();
		session.close();

		return students;
	}

	public List<Student> getStudentswithNamedQuerry(String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query q = session.getNamedQuery("Student.byName");
		q.setParameter("name", name);
		List<Student> students = q.getResultList();
		session.getTransaction().commit();
		session.close();
		return students;
	}

//	public List<Student> getStudentsWithId() {
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//
//		Query q = session.createQuery("from Student where id =:id or name =:name");
//		q.setParameter("id", 4);
//		q.setParameter("name", "p5");
//		List<Student> students = q.getResultList();
//		session.getTransaction().commit();
//		session.close();
//
//		return students;
//	}

	public List<Student> getStudentsWithId() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Student> criteria = cb.createQuery(Student.class);
		Root<Student> root = criteria.from(Student.class);

		criteria.where(cb.or
				(cb.between(root.get("student_id"), 3, 5),
						cb.like(root.get("name"), "Joe1%"))
				);

		List<Student> students = session.createQuery(criteria).getResultList();

		session.getTransaction().commit();
		session.close();

		return students;
	}

//	public List<Student> getStudentsFromSet(int start, int count) {
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//
//		Query querry = session.createQuery("from Student");
//		querry.setFirstResult(start - 1);
//		querry.setMaxResults(count);
//
//		List<Student> students = querry.getResultList();
//
//		session.getTransaction().commit();
//		session.close();
//
//		return students;
//	}

	public List<Student> getStudentsFromSet(int start, int count) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Student> criteria = cb.createQuery(Student.class);
		Root<Student> root = criteria.from(Student.class);

		criteria.where(cb.between(root.get("student_id"), start, start + count - 1));

		List<Student> students = session.createQuery(criteria).getResultList();

		session.getTransaction().commit();
		session.close();

		return students;
	}

	// not good with criteria
	public List<String> getStudentsNames(int start, int count) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query querry = session.createQuery("select name from Student");
		querry.setFirstResult(start - 1);
		querry.setMaxResults(count);

		List<String> studentsNames = querry.getResultList();

		session.getTransaction().commit();
		session.close();

		return studentsNames;
	}
}
