package user.adminaction;

import com.opensymphony.xwork2.ActionContext;

import user.service.UserService;
import user.vo.User;
import utils.PageBean;

/**
 * @author yanni
 * @time 2017年6月10日 下午2:31:15
 */
public class UserAdminAction
{
	// 模型驱动使用的类
	private User user=new User();

	public User getModel()
	{
		return user;
	}

	// 注入用户的Service
	private UserService userService;

	public void setUserService(UserService userService)
	{
		this.userService=userService;
	}

	// 接受page参数
	private Integer page;

	public void setPage(Integer page)
	{
		this.page=page;
	}

	// 后台查询所有用户的方法带分页:
	public String findAll()
	{
		PageBean<User> pageBean=userService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean",pageBean);
		return "findAll";
	}

	// 后台用户的删除
	public String delete()
	{
		User existUser=userService.findByUid(user.getUid());
		userService.delete(existUser);
		return "deleteSuccess";
	}

	// 后台用户的编辑
	public String edit()
	{
		user=userService.findByUid(user.getUid());
		return "editSuccess";
	}

	// 后台用户的修改:
	public String update()
	{
		userService.update(user);
		return "updateSuccess";
	}
}
