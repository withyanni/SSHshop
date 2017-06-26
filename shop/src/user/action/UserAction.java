package user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import user.service.UserService;
import user.vo.User;

/**
 * 
 * @author yanni
 * @time   2017年5月28日上午11:50:19
 */
public class UserAction extends ActionSupport implements ModelDriven<User>
{
	//模型驱动使用的对象
	private User user=new User();

	public User getModel()
	{
		return user;
	}

	private String checkCode;

	public void setCheckCode(String checkCode)
	{
		this.checkCode=checkCode;
	}

	//注入UserService
	private UserService userService;

	public void setUserService(UserService userService)
	{
		this.userService=userService;
	}

	/**
	 * 跳转到注册页面的方法
	 */
	public String registPage()
	{
		return "registPage";
	}

	/**
	 * AJAX异步校验用户名的方法
	 * @throws IOException 
	 */
	public String findByName() throws IOException
	{
		// 调用Service进行查询:
		User existUser=userService.findByUsername(user.getUsername());
		// 获得response对象,项页面输出:
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// 判断
		if(existUser!=null)
		{
			// 查询到该用户:用户名已经存在
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		}
		else
		{
			// 没查询到该用户:用户名可以使用
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}

	/**
	 * 用户注册的方法
	 * @return
	 */
	public String regist()
	{
		if(!checkCode.equalsIgnoreCase((String)ServletActionContext.getRequest().getSession().getAttribute("checkCode"))){
			this.addActionError("验证码输入错误!");
			return "checkcodeFail";
		}
		userService.save(user);
		this.addActionMessage("注册成功,请激活");
		return "msg";
	}

	/**
	 * 用户激活方法
	 * @return
	 */
	public String active()
	{
		//模型驱动user已经接收到了
		User existUser=userService.findByCode(user.getCode());
		// 判断
		if(existUser==null)
		{
			// 激活码是错误的
			this.addActionMessage("激活失败:激活码错误!");
		}
		else
		{
			// 激活成功
			// 修改用户的状态
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("激活成功:请去登录!");
		}
		return "msg";
	}

	/**
	 * 登录界面
	 * @return
	 */
	public String loginPage()
	{

		return "loginPage";
	}

	public String login()
	{
		User existUser=userService.login(user);
		if(existUser==null)
		{
			this.addActionError("登录失败:用户名或密码错误或用户未激活!");
			return LOGIN;
		}
		else
		{
			ServletActionContext.getRequest().getSession()
					.setAttribute("existUser",existUser);
			return "loginSucess";
		}
	}

	public String quit()
	{
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}

}
