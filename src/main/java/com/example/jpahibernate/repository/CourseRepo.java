package com.example.jpahibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.jpahibernate.model.Course;

@Repository
@Transactional
public class CourseRepo {

	@Autowired
	EntityManager em;

	public Course getCourseById(int id) {
		return em.find(Course.class, id);
	}

	public Course saveCourse(Course c) {
		em.persist(c);
		return c;
	}

	public List<Course> getAllCourses() {
		List resultList = em.createQuery("select c from Course c").getResultList();
		return resultList;
	}

	public List<Course> getAllCoursesByTypedQuery() {
		TypedQuery<Course> resultList = em.createQuery("select c from Course c", Course.class);
		return resultList.getResultList();
	}

	/* 
	 * 
	 * 
	 */
	public List<Course> getAllCoursesWhereQuery(String str, double price) {
		TypedQuery<Course> query = em.createQuery("select c from Course c where name like :str and price=:price",
				Course.class);
		return query.setParameter("str", "%" + str + "%").setParameter("price", price).getResultList();
	}

	/* 
	 * 
	 * 
	 */
	public List<Course> getAllCoursesWhereQuery2(double lowerBound, double upperBound) {
		TypedQuery<Course> query = em
				.createQuery("select c from Course c where price between :lowerBound and :upperBound", Course.class);
		return query.setParameter("lowerBound", lowerBound).setParameter("upperBound", upperBound).getResultList();
	}

	public List<Course> getAllCoursesUsingNamedQuery() {
		TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);
		return query.getResultList();
	}

	public List<Course> getAllCourses_Nativequery() {
		Query query = em.createNativeQuery("Select * from course", Course.class);
		return query.getResultList();
	}
}
