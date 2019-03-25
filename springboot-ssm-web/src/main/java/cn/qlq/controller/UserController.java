package cn.qlq.controller;

import java.util.Date;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

import cn.qlq.annotation.MyLogAnnotation;
import cn.qlq.bean.user.User;
import cn.qlq.service.user.UserService;
import cn.qlq.utils.JSONResultUtil;
import cn.qlq.utils.ValidateCheck;

@Controller /** 自动返回的是json格式数据 ***/
@RequestMapping("user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Reference(version = "1.0.0", check = false)
	private UserService userService;

	/**
	 * 添加user
	 * 
	 * @param user
	 *            springMVC自动映射的实体
	 * @return
	 */
	@RequestMapping("addUser")
	@ResponseBody
	public JSONResultUtil addUser(User user) {
		user.setCreatetime(new Date());
		logger.info("user -> {}", user);
		userService.addUser(user);
		return JSONResultUtil.ok();
	}

	/**
	 * 分页查询user
	 * 
	 * @param condition
	 * @return
	 */
	@RequestMapping("getUsers")
	// @Cacheable(value = "usersCache", keyGenerator = "keyGenerator") //
	// 在redis中开启key为findAllUser开头的存储空间。key和keyGenerator只能使用一个
	@MyLogAnnotation(operateDescription = "分页查询用户")
	@ResponseBody
	public PageInfo<User> getUsers(@RequestParam Map condition) {
		if (ValidateCheck.isNull(MapUtils.getString(condition, "pageNum"))) { // 如果不为空的话改变当前页号
			condition.put("pageNum", 1);
		}
		if (ValidateCheck.isNull(MapUtils.getString(condition, "pageSize"))) { // 如果不为空的话改变当前页大小
			condition.put("pageSize", 6);
		}

		PageInfo<User> pageInfo = userService.getPageInfoUsers(condition);
		return pageInfo;
	}

	/**
	 * 删除user
	 * 
	 * @param user
	 *            springMVC自动映射的实体
	 * @return
	 */
	@RequestMapping("deleteUser")
	@ResponseBody
	public JSONResultUtil deleteUser(int id) {
		userService.deleteUser(id);
		return JSONResultUtil.ok();
	}

	/**
	 * 跳转打修改用户页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("updateUser")
	public String updateUser(int id, ModelMap map) {
		User user = userService.getUser(id);
		map.addAttribute("user", user);
		return "updateUser";
	}

	/**
	 * 添加user
	 * 
	 * @param user
	 *            springMVC自动映射的实体
	 * @return
	 */
	@RequestMapping("doUpdateUser")
	@ResponseBody
	public JSONResultUtil doUpdateUser(User user) {
		logger.info("user -> {}", user);
		userService.updateUser(user);
		return JSONResultUtil.ok();
	}
}