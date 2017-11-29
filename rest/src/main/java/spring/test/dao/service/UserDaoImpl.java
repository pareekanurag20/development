package spring.test.dao.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.test.dao.model.UserEntity;

@Repository
public class UserDaoImpl implements UserDao {

	final static Logger LOGGER=LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	public void insert(UserEntity user) {
		Session session = this.sessionFactory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			session.persist(user);			
			tx.commit();
		}catch (Exception e) {
			LOGGER.error("got error during the persist for user [{}] ",user,e);
		}finally {
			session.close();
		}
		
	}

	@Transactional(readOnly=true)
	public UserEntity getUserById(int id) {
		Session session = this.sessionFactory.openSession();
		UserEntity userEntity=null;
		try {
			Transaction tx = session.beginTransaction();
			 userEntity= (UserEntity) session.get(UserEntity.class, id);
			 tx.commit();
		}catch (Exception e) {
			LOGGER.error("got error during fetching the user with id [{}] ",id,e);
		}finally {
			session.close();
		}
		return userEntity;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<UserEntity> getAllUser() {
		Session session = this.sessionFactory.openSession();
		List<UserEntity> personList = null;
		try {
			Transaction tx = session.beginTransaction();
			 personList = session.createQuery("from UserEntity").list();
			 tx.commit();
		}catch (Exception e) {
			LOGGER.error("got error during fetching the user list ",e);
		}finally {
			session.close();
		}

		return personList;
	}

	public void updateUser(UserEntity user,int id) {
		Session session = this.sessionFactory.openSession();
		try {
			UserEntity userEntity= (UserEntity) session.get(UserEntity.class,id);
			Transaction tx = session.beginTransaction();
			userEntity.setName(user.getName());
			userEntity.setSalary(user.getSalary());
			userEntity.setAge(user.getAge());
			
			session.update(userEntity);
			tx.commit();
		}catch (Exception e) {
			LOGGER.error("got error while updating the user with id [{}] ",id,e);
		}finally {
			session.close();
		}	
		
	}

	public void deleteUser(int id) {
		
		Session session = this.sessionFactory.openSession();
		UserEntity userEntity=null;
		try {
			Transaction tx = session.beginTransaction();
			 userEntity= (UserEntity) session.get(UserEntity.class, id);
			 session.delete(userEntity);
			 tx.commit();
		}catch (Exception e) {
			LOGGER.error("got error during delete the user with id [{}] ",id,e);
		}finally {
			session.close();
		}
		
	}

	@Transactional(readOnly=true)
	public UserEntity getUserByName(String name){
		Session session = this.sessionFactory.openSession();
		UserEntity userEntity=null;
		try {
			Transaction tx = session.beginTransaction();
			Query<UserEntity> query = session.createNamedQuery("GET_USER_BY_NAME",UserEntity.class);
			query.setParameter("name", name);
			userEntity=(UserEntity) query.uniqueResult();
			tx.commit();
		}catch (Exception e) {
			LOGGER.error("got error during fetching the user with name [{}] ",name,e);
		}finally {
			session.close();
		}
		return userEntity;
	}

}
