package cn.lalin.gjp.view;

import java.util.List;
import java.util.Scanner;

import cn.lalin.gjp.controller.ZhangWuController;
import cn.lalin.gjp.domain.ZhangWu;

/*
 * 视图层，用户看到和操作的界面
 * 数据传递给controller实现
 * 成员位置，创建controller对象
 */
public class MainView {
	private ZhangWuController controller = new ZhangWuController();
	/*
	 * 实现界面的效果
	 * 接收用户的输入
	 * 根据数据，调用不同的功能方法
	 */
	public void run(){
		//创建Scanner对象，键盘反复输入
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("--------------管家婆家庭记账软件---------------");
			System.out.println("1.添加账务;2.编辑账务;3.删除账务;4.查询账务;5.退出系统");
			System.out.println("请输入要操作的功能序号[1-5]");
			//接收用户的菜单选择
			int choose =sc.nextInt();
			//对选择的菜单判断，调用不同的功能
			switch(choose){
			case 1:
			//选择添加账务，调用添加账务的方法
				addZhangWu();
				break;
			case 2:
			//选择编辑账务，调用编辑账务的方法
				editZhangWu();
				break;
			case 3:
			//选择删除账务，调用删除账务的方法
				deleteZhangWu();
				break;
			case 4:
			//选择查询账务，调用查询账务的方法
				selectZhangWu();
				break;
			case 5:
				System.out.println("系统退出成功");
				System.exit(0);
				break;
			}
			
		}
	}
	/*
	 * 定义方法实现账务删除
	 * 接收用户的输入,传递一个主键
	 * 调用控制层方法,传递主键
	 */
	public void deleteZhangWu(){
		//调用查询所有账务查询的功能,显示出来
		//看到所有的数据,从中选择一项进行删除
		selectAll();
		System.out.println("选择的是删除功能,输入ID");
		Scanner sc = new Scanner(System.in);
		int zwid=sc.nextInt();
		//调用控制层方法,传递主键ID即可
		controller.deleteZhangWu(zwid);
		System.out.println("删除账务成功");
	}
	/*
	 * 编辑账务方法
	 * 实现思想:接收用户的输入
	 * 数据的信息,封装成ZhangWu对象
	 * 调用控制层的方法,传递ZhangWu对象,实现编辑
	 */
	public void editZhangWu(){
		//调用查询所有账务查询的功能,显示出来
		//看到所有的数据,从中选择一项进行编辑
		selectAll();
		System.out.println("选择的是编辑功能,请输入需要编辑的ID");
		Scanner sc = new Scanner(System.in);
		ZhangWu zw = new ZhangWu();
		System.out.println("请输入ID");
		zw.setZwid(sc.nextInt());
		System.out.println("请输入新类别");
		zw.setFlname(sc.next());
		System.out.println("请输入新账号");
		zw.setZhanghu(sc.next());
		System.out.println("请输入新金额");
		zw.setMoney(sc.nextDouble());
		System.out.println("请输入新日期");
		zw.setCreatetime(sc.next());
		System.out.println("请输入新描述");
		zw.setDescription(sc.next());
		controller.editZhangWu(zw);
		System.out.println("编辑成功");
	}
	
	/*
	 * 定义方法,addZhangWu
	 * 添加账务的方法,用户在界面选择1的时候调用
	 * 实现思想:接收键盘的输入,调用controller层方法
	 */
	public void addZhangWu(){
		System.out.println("选择添加账务的功能,请输入以下的内容");
		Scanner sc = new Scanner(System.in);
		System.out.println("输入分类的名称");
		String flname = sc.next();
		System.out.println(flname);
		System.out.println("输入账务的金额");
		Double money = sc.nextDouble();
		System.out.println("输入账务的账户");
		String Zhanghu=sc.next();
		System.out.println("输入账务的时间,格式:xxxx-xx-xx");
		String createtime=sc.next();
		System.out.println("输入账务的描述");
		String description=sc.next();
		//将接收到的数据,调用controller层的方法,传递参数
		//用户将输入所有的参数,封装成ZhangWu对象
		ZhangWu zw = new ZhangWu(0,flname,money,Zhanghu,createtime,description);
		controller.addZhangWu(zw);
		System.out.println("恭喜!添加账务成功");
	}
	/*
	 *定义selectZhangWu()
	 *显示查询的方式1 查询所有 2条件查询
	 *接收用户的选择
	 */
	public void selectZhangWu(){
		System.out.println("1 查询所有;2条件查询");
		Scanner sc = new  Scanner(System.in);
		int selectChooser=sc.nextInt();
		//根据用户的选择，调用不同的功能
		switch(selectChooser){
		case 1:
			//选择查询所有,调用查询所有的方法
			selectAll();
			break;
		case 2:
			//选择条件查询，调用条件查询的方法
			select();
			break;
		}

	}
	
	/*
	 * 定义方法,查询所有的账务数据
	 */
	public void selectAll(){
		//调用控制层中的方法,查询所有的账务数据
		List<ZhangWu> list = controller.selectAll();
		printZhangWu(list);
	}

	/*
	 * 定义方法,实现条件查询账务数据
	 * 提供用户的输入日期,开始日期、结束日期
	 * 两个日期传递到controller层
	 * 调用controller方法,传递两个日期参数
	 * 获取到controller打印出来
	 */
	public void select(){
		System.out.println("选择条件查询,输入日期格式XXXX-XX-XX");
		Scanner sc = new Scanner(System.in);
		System.out.println("输入开始日期");
		String startdate = sc.nextLine();
		System.out.println("输入结束日期");
		String enddate = sc.nextLine();
		//调用controller层的方法,传递日期,获取查询结果集
		List<ZhangWu> list = controller.select(startdate, enddate);
		if(list.size()!=0){
			printZhangWu(list);
		}else{
			System.out.println("暂无数据");
		}
	}
	private void printZhangWu(List<ZhangWu> list) {
		//输出表头
		System.out.println("ID\t类别\t账户\t金额\t时间\t\t说明");
		for (ZhangWu zw: list) {
			System.out.println(zw.getZwid()+"\t"+zw.getFlname()+"\t"+zw.getZhanghu()
					+"\t"+zw.getMoney()+"\t"+zw.getCreatetime()+"\t"+zw.getDescription());
		}
	}
}
