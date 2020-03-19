import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 如果是window系统，请把运行环境编码改变gbk
 * @author Administrator
 *
 */
public class MysqlTest {

	public static void main(String[] args) {
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		try {
			//1，加载驱动类
			Class.forName("com.mysql.jdbc.Driver");
			//2,得到连接(需要修改数据库地址，数据库名，用户名，密码,数据库编码)
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3307/consumer-loan?useUnicode=true&amp&characterEncoding=UTF-8","root","root");
			
			//3,得到statement
			stm=conn.createStatement();
			
			//4,执行SQL
			//executeTest(stm, conn);
			executeQueryTest(stm,conn);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException e){}
			if(stm!=null)try{stm.close();}catch(SQLException e){}
			if(conn!=null)try{conn.close();}catch(SQLException e){}
		}
	}
	
	private static void executeTest(Statement stm,Connection conn) throws SQLException {
		ResultSet rs = stm.executeQuery("select name from cl_test");
		while(rs.next()) {
			System.out.print(rs.getString(1)+"\t");
		}
		rs.close();
	}
	
	
	private static void executeQueryTest(Statement stm,Connection conn) throws SQLException{
		ResultSet rs=null;
		stm.execute("drop table if exists student;");
		stm.execute("create table student(id varchar(32) ,name varchar(32),course varchar(32)); ");
		Scanner sca=new Scanner(System.in);
		
		System.out.println("请输入学生的信息(学号，姓名，课程)(最好用英文，否则容易出现乱码，解决乱码的方式保证数据库的编码和jdbc连接的编码一致))，用，隔开：");
		int count = 3;
		while(count-- > 0 ){
			String[] student = sca.nextLine().split("，|,");
			while(student.length != 3) {
				System.out.println("输入错误，请重新输入：");
				student = sca.nextLine().split("，|,");
			}
			PreparedStatement pstm = conn.prepareStatement("insert into student values(?,?,?);");
			pstm.setString(1, student[0]);
			pstm.setString(2, student[1]);
			pstm.setString(3, student[2]);
			pstm.execute();
			pstm.close();
			System.out.println("输入成功");
		}
		System.out.println("按学号排序");
		rs=stm.executeQuery("select id,name,course  from student order by id asc");

		while(rs.next()){
			System.out.print(rs.getString(1)+"\t");
			System.out.print(rs.getString(2)+"\t");
			System.out.print(rs.getString(3)+"\t");
			System.out.println();
		}
		rs.close();
	}
}
