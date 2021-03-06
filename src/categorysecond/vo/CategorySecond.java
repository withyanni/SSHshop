package categorysecond.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import category.vo.Category;
import product.vo.Product;

/**
 * 
 * @author yanni
 * @time   2017年6月1日上午11:35:12
 */
public class CategorySecond  implements Serializable
{

	private Integer csid;
	private String csname;
	// 所属一级分类.存的是一级分类的对象.
	private Category category=new Category();
	// 配置商品集合
	private Set<Product> products=new HashSet<Product>();



	public Set<Product> getProducts()
	{
		return products;
	}

	public void setProducts(Set<Product> products)
	{
		this.products=products;
	}

	public Integer getCsid()
	{
		return csid;
	}

	public void setCsid(Integer csid)
	{
		this.csid=csid;
	}

	public String getCsname()
	{
		return csname;
	}

	public void setCsname(String csname)
	{
		this.csname=csname;
	}

	public Category getCategory()
	{
		return category;
	}

	public void setCategory(Category category)
	{
		this.category=category;
	}



	//	public Set<Product> getProducts()
	//	{
	//		return products;
	//	}
	//
	//	public void setProducts(Set<Product> products)
	//	{
	//		this.products=products;
	//	}
}
