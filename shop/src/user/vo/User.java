package user.vo;

import java.util.HashSet;
import java.util.Set;

import order.vo.Order;

/**
 * 用户模块的实体类
 * @author yanni
 * @time   2017年5月28日下午2:13:56
 */
public class User
{
	private Integer uid;
	private String username;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String addr;
	private Integer state;
	private String code;

	// 一个用户对应多个订单:
	private Set<Order> orders=new HashSet<Order>();

	public Set<Order> getOrders()
	{
		return orders;
	}

	public void setOrders(Set<Order> orders)
	{
		this.orders=orders;
	}

	public Integer getUid()
	{
		return uid;
	}

	public void setUid(Integer uid)
	{
		this.uid=uid;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username=username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password=password;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name=name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email=email;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone=phone;
	}

	public String getAddr()
	{
		return addr;
	}

	public void setAddr(String addr)
	{
		this.addr=addr;
	}

	public Integer getState()
	{
		return state;
	}

	public void setState(Integer state)
	{
		this.state=state;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code=code;
	}
}
