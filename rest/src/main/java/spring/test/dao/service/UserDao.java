package spring.test.dao.service;

import java.util.List;

import spring.test.dao.model.UserEntity;

public interface UserDao {

	public void insert(UserEntity user);

	public UserEntity getUserById(int id);

	public List<UserEntity> getAllUser();

	public void updateUser(UserEntity user,int id);

	public void deleteUser(int id);

	public UserEntity getUserByName(String name);
}
