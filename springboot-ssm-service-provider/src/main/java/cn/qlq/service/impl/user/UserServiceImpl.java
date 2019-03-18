package cn.qlq.service.impl.user;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.qlq.bean.user.User;
import cn.qlq.bean.user.UserExample;
import cn.qlq.bean.user.UserExample.Criteria;
import cn.qlq.mapper.user.UserMapper;
import cn.qlq.service.user.UserService;

@Service
@com.alibaba.dubbo.config.annotation.Service(version = "1.0.0")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	public List<User> findAllUser() {
		UserExample userExample = new UserExample();
		List<User> list = userMapper.selectByExample(userExample);
		return list;
	}

	@Override
	public void addUser(User user) {
		userMapper.insert(user);
	}

	@Override
	public List<User> getUsers(Map condition) {
		UserExample userExample = new UserExample();
		if (StringUtils.isNotBlank(MapUtils.getString(condition, "username"))) {
			Criteria createCriteria = userExample.createCriteria();
			createCriteria.andUsernameLike("%" + MapUtils.getString(condition, "username") + "%");
		}
		List<User> list = userMapper.selectByExample(userExample);
		return list;
	}

	@Override
	public void deleteUser(int id) {
		userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public User getUser(int id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public PageInfo<User> getPageInfoUsers(Map condition) {

		PageHelper.startPage(MapUtils.getIntValue(condition, "pageNum"), MapUtils.getIntValue(condition, "pageSize"));
		UserExample userExample = new UserExample();
		if (StringUtils.isNotBlank(MapUtils.getString(condition, "username"))) {
			Criteria createCriteria = userExample.createCriteria();
			createCriteria.andUsernameLike("%" + MapUtils.getString(condition, "username") + "%");
		}
		List<User> list = userMapper.selectByExample(userExample);

		return new PageInfo<User>(list);
	}

}
