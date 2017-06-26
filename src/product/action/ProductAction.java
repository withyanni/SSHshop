package product.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import category.service.CategoryService;
import product.service.ProductService;
import product.vo.Product;
import utils.PageBean;

public class ProductAction extends ActionSupport implements ModelDriven<Product>
{
	//驱动模型
	private Product product=new Product();
	//注入商品的service
	private ProductService productService;
	// 注入一级分类的Service
	private CategoryService categoryService;
	// 接收分类cid
	private Integer cid;
	// 接收二级分类id
	private Integer csid;
	// 接收当前页数:
	private int page;

	public void setPage(int page)
	{
		this.page=page;
	}

	public void setProduct(Product product)
	{
		this.product=product;
	}

	public void setCid(Integer cid)
	{
		this.cid=cid;
	}

	public void setCsid(Integer csid)
	{
		this.csid=csid;
	}

	public void setCategoryService(CategoryService categoryService)
	{
		this.categoryService=categoryService;
	}

	public void setProductService(ProductService productService)
	{
		this.productService=productService;
	}

	@Override
	public Product getModel()
	{
		return product;
	}

	/**
	 * 根据商品id查询商品
	 * @return
	 */
	public String findByPid()
	{
		//调用service方法完成查询,product是驱动模型，默认就会在栈顶
		product=productService.findByPid(product.getPid());
		return "findByPid";
	}

	/**
	 * 根据分类的id查询商品
	 * @return
	 */
	public String findByCid()
	{
		// List<Category> cList = categoryService.findAll();
		PageBean<Product> pageBean=productService.findByPageCid(cid,page);// 根据一级分类查询商品,带分页查询
		// 将PageBean存入到值栈中:
		ActionContext.getContext().getValueStack().set("pageBean",pageBean);
		return "findByCid";
	}

	/**
	 * 根据二级分类id查询商品
	 * @return
	 */
	public String findByCsid()
	{
		// 根据二级分类查询商品
		PageBean<Product> pageBean=productService.findByPageCsid(csid,page);
		// 将PageBean存入到值栈中:
		ActionContext.getContext().getValueStack().set("pageBean",pageBean);
		return "findByCsid";
	}

}
