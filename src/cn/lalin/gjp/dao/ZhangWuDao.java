package cn.lalin.gjp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.lalin.gjp.domain.ZhangWu;
import cn.lalin.gjp.tools.JDBCUtils;
/*
 * 实现对数据表gjp_zhangwu数据增删改查
 * dbutils工具类完成，类成员创建QueryRunner对象，指定数据源
 */
public class ZhangWuDao {
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	
	/*
	 * 定义方法,实现账务的删除
	 */
	public void deleteZhangWu(int zwid){
		try{
			//删除语句
			String sql = "delete from gjp_zhangwu where zwid=?";
			qr.update(sql, zwid);
		}catch(SQLException ex){
			System.out.println(ex);
			throw new RuntimeException("删除账务失败");
		}

	}
	
	/*
	 * 定义方法,实现账务编辑的功能
	 * 由业务层调用,传递ZhangWu对象
	 * 将ZhangWu对象中的数据,更新到数据表
	 */
	public void editZhangWu(ZhangWu zw){
		try{
		//更新数据库的语句
		String sql="update gjp_zhangwu set flname=?,money=?,zhanghu=?,createtime=?,description=? where zwid=?";
		//定义数组对象,存入所有的参数
		Object [] params = {zw.getFlname(),zw.getMoney(),zw.getZhanghu(),zw.getCreatetime(),zw.getDescription(),zw.getZwid()};
		//调用qr的方法,updatez执行更新
		qr.update(sql, params);	
		}catch(SQLException ex){
			System.out.println(ex);
			throw new RuntimeException("账务编辑失败");
		}
		
	}
	/*
	 * 定义方法,实现账务添加的功能
	 * 由业务层调用,传递ZhangWu对象
	 * 将ZhangWu对象中的数据,添加到数据表
	 */
	public void addZhangWu(ZhangWu zw){
		try{
		//拼写添加数据的SQL
		String sql="insert into gjp_zhangwu(flname,money,zhanghu,createtime,description)values(?,?,?,?,?)";
		//创建数组对象,存储5个占位符的实际参数
		//实际参数来源是,传递过来的对象ZhangWu
		Object [] params = {zw.getFlname(),zw.getMoney(),zw.getZhanghu(),zw.getCreatetime(),zw.getDescription()};
		//调用qr对象中的方法update执行添加
		qr.update(sql, params);
		}catch(SQLException ex){
			System.out.println(ex);
			throw new RuntimeException("账务添加失败");
		}
	}
	/*
	 * 定义方法,查询数据库,带有条件去查询账务表
	 * 有业务层service调用,查询结果集存储到List集合
	 * 调用者传递两个日期字符串
	 */
	public List<ZhangWu> select(String startdate,String enddate){
		try {
			//拼写条件查询的SQL语句
			String sql="select * from gjp_zhangwu where createtime between ? and ?";
			//定义对象数组,存储?占位符
			Object [] params = {startdate,enddate};
			return qr.query(sql, new BeanListHandler<ZhangWu>(ZhangWu.class), params);
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException("条件查询失败");
		}
	}
	/*
	 * 定义方法,查询数据库,获取所有的账务数据
	 * 此方法由业务层调用
	 * 结果集,将所有的账务数据,存储到Bean对象中,存储到集合中
	 */
	public List<ZhangWu> selectAll(){
		try {
			//查询zhangWu的SQL语句
			String sql="select * from gjp_zhangwu";
			//调用qr对象的方法,query方法,结果集BeanListHandler
			List<ZhangWu> list = qr.query(sql, new BeanListHandler(ZhangWu.class));
			return list;
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException("查询账务所有数据失败");
		}
	}
}
