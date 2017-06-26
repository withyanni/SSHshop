package user.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import user.dao.UserDao;
import user.vo.User;
import utils.Mail;
import utils.MyUtils;
import utils.PageBean;

/**
 * @author yanni
 * @time 2017年5月28日下午2:36:00
 */
@Transactional
public class UserService
{
	// 注入UserDao
	private UserDao userDao;

	public void setUserDao(UserDao userDao)
	{
		this.userDao=userDao;
	}

	/**
	 * 查找用户名是否存在，返回User对象判断
	 * 
	 * @param username
	 * @return
	 */
	public User findByUsername(String username)
	{
		return userDao.findByUsername(username);
	}

	/**
	 * 存储用户
	 * 
	 * @param user
	 */
	public void save(User user)
	{
		user.setCode(MyUtils.UUID()+MyUtils.UUID());
		user.setState(0);
		userDao.save(user);
		MyUtils.sendMail(new Mail(user.getEmail()),user.getCode());

	}

	/**
	 * 根据激活码查询用户
	 * 
	 * @param code
	 * @return
	 */
	public User findByCode(String code)
	{
		return userDao.findByCode(code);
	}

	/**
	 * 修改用户的激活状态
	 * 
	 * @param existUser
	 */
	public void update(User existUser)
	{
		userDao.update(existUser);
	}

	public User login(User user)
	{
		return userDao.login(user);
	}

	/**
	 * @param page
	 * @return
	 */
	// 业务层用户查询所有
	public PageBean<User> findByPage(Integer page)
	{
		PageBean<User> pageBean=new PageBean<User>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		// 显示5个
		int limit=5;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount=0;
		totalCount=userDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage=0;
		if(totalCount%limit==0)
		{
			totalPage=totalCount/limit;
		}
		else
		{
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合:
		int begin=(page-1)*limit;
		List<User> list=userDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public User findByUid(Integer uid)
	{
		return userDao.findByUid(uid);
	}

	public void delete(User existUser)
	{
		userDao.delete(existUser);
	}
}
