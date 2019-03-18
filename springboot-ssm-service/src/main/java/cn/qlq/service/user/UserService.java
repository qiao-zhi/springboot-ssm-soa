package cn.qlq.service.user;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import cn.qlq.bean.user.User;

public interface UserService {

	/**
	 * 根据接口查询所用的用户
	 */
	public List<User> findAllUser();

	public void addUser(User user);

	public List<User> getUsers(Map condition);

	public PageInfo<User> getPageInfoUsers(Map condition);

	public void deleteUser(int id);

	public User getUser(int id);

	public void updateUser(User user);
}
