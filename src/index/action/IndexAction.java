package index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import category.service.CategoryService;
import category.vo.Category;
import product.service.ProductService;
import product.vo.Product;

/**
 * 首页访问的action
 * @author yanni
 * @time   2017年5月28日上午9:36:58
 */
public class IndexAction extends ActionSupport
{
	// 注入一级分类的Service:
	private CategoryService categoryService;
	//注入商品的Service	
	private ProductService productService;

	public void setProductService(ProductService productService)
	{
		this.productService=productService;
	}

	public void setCategoryService(CategoryService categoryService)
	{
		this.categoryService=categoryService;
	}
	/*
	 * 不需要接收数据，所以不用implements ModelDriven
	 */

	@Override
	public String execute() throws Exception
	{
		// 查询所有一级分类集合
		List<Category> cList=categoryService.findAll();
		// 将一级分类存入到Session的范围:
		ActionContext.getContext().getSession().put("cList",cList);
		// 查询热门商品:
		List<Product> hList=productService.findHot();
		// 保存到值栈中:
		ActionContext.getContext().getValueStack().set("hList",hList);
		
		// 查询最新商品:
		List<Product> nList=productService.findNew();
		// 保存到值栈中:
		ActionContext.getContext().getValueStack().set("nList",nList);
		return "index";
	}
}
