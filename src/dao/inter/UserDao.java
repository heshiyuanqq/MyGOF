package dao.inter;

import java.util.List;

import pojo.User;

public interface UserDao {
	
	
	public void addUser(User user);
	public User getUserById(String id);
	public void delUserById(String id);
	public List<User>  listUsers();

}
