package com.example.jpahibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
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

	public List<Course> getAllCoursesWhereQuery(String str, double price) {
		TypedQuery<Course> query = em.createQuery("select c from Course c where name like :str and price=:price",
				Course.class);
		return query.setParameter("str", "%" + str + "%").setParameter("price", price).getResultList();
	}
}
