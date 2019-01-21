package cn.lalin.gjp.controller;

import java.util.List;

import cn.lalin.gjp.domain.ZhangWu;
import cn.lalin.gjp.service.ZhangWuService;

/*
 * 控制器层
 * 接受视图层的数据，数据传递给service层
 * 成员位置，创建service对象
 */
public class ZhangWuController {
	private ZhangWuService service = new ZhangWuService();
	
	/*
	 * 定义方法,实现账务的删除
	 */
	public void deleteZhangWu(int zwid){
		service.deleteZhangWu(zwid);
	}
	/*
     * 定义方法,实现账务的编辑
     * 由视图层调用,传递参数,ZhangWu对象
     * 调用service层的方法,传递ZhangWu对象
	 */
	public void editZhangWu(ZhangWu zw){
		service.editZhangWu(zw);
	}
	/*
	 * 定义方法,实现账务的添加
	 * 由视图层调用,传递参数,(传递过来的不是5个参数,而是一个ZhangWu类型的对象)
	 * 方法调用service层的方法,传递ZhangWu对象,获取到添加后的结果集(添加成功的行数,int)
	 */
	public void addZhangWu(ZhangWu zw){
		service.addZhangWu(zw);
	}
	
	/*
	 * 定义方法，实现条件查询账务
	 * 方法由视图调用，传递两个日期字符串
	 * 调用service层的方法,获取结果集
	 * 结果集返回给视图
	 */
	public List<ZhangWu> select(String startdate, String enddate){
		return service.select(startdate, enddate);
	}
	/*
	 * 控制层类定义方法,实现查询所有的账务数据
	 * 方法由视图层调用,方法调用service层
	 */
	public List<ZhangWu> selectAll(){
		return service.selectAll();
		
	}
}
