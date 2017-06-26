package category.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import categorysecond.vo.CategorySecond;

/**
 * 
 * @author yanni
 * @time   2017年5月29日下午4:11:48
 */
public class Category implements Serializable
{
	private Integer cid;
	private String cname;
	// 一级分类中存放二级分类的集合:
	private Set<CategorySecond> categorySeconds=new HashSet<CategorySecond>();

	public Category()
	{
		
	}

	public Category(Integer cid,String cname,
			Set<CategorySecond> categorySeconds)
	{
		super();
		this.cid=cid;
		this.cname=cname;
		this.categorySeconds=categorySeconds;
	}

	public Integer getCid()
	{
		return cid;
	}

	public void setCid(Integer cid)
	{
		this.cid=cid;
	}

	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname=cname;
	}

	public Set<CategorySecond> getCategorySeconds()
	{
		return categorySeconds;
	}

	public void setCategorySeconds(Set<CategorySecond> categorySeconds)
	{
		this.categorySeconds=categorySeconds;
	}
}
