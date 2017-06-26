package order.vo;

import product.vo.Product;

public class OrderItem
{
	private Integer itemid;
	private Integer count;//数量
	private Double subtotal;//小计
	// 商品外键:对应一个商品
	private Product product;
	// 订单外键:对应一个订单
	private Order order;
	public Integer getItemid()
	{
		return itemid;
	}
	public void setItemid(Integer itemid)
	{
		this.itemid=itemid;
	}
	public Integer getCount()
	{
		return count;
	}
	public void setCount(Integer count)
	{
		this.count=count;
	}
	public Double getSubtotal()
	{
		return subtotal;
	}
	public void setSubtotal(Double subtotal)
	{
		this.subtotal=subtotal;
	}
	public Product getProduct()
	{
		return product;
	}
	public void setProduct(Product product)
	{
		this.product=product;
	}
	public Order getOrder()
	{
		return order;
	}
	public void setOrder(Order order)
	{
		this.order=order;
	}
}
