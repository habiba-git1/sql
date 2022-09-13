package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Crud {
	private String url = "jdbc:mysql://localhost:3306/deloitte_sql";
	private String username = "root";
	private static String password = "habiba@MySQL1";
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Crud crud = new Crud();
		List<Account> account_list = crud.findAll();
		System.out.println(account_list);
		Account ac = crud.findById(3);
		ac.setAccount_type("updated");
		ac.setStatus("updated");

		crud.update(2, ac);

		System.out.println(crud.findById(3));
		
		Account account = new Account(0, password, null, password, password, password, null);

		account.setAccount_id(6);
		account.setAccount_type("Loan");
		account.setOpening_bal((float) 15000);
		account.setUser_id("8186");
		account.setStatus("UnActive");
		account.setAccount_number("678934567");
		account.setInterest_rate((float)12.5);
		
		System.out.println(account);
		crud.save(account);

		System.out.println(crud.findAll());

		crud.delete(5);

		System.out.println(crud.findAll());

	}

	public List<Account> findAll() throws SQLException {
		List<Account> account_list = new ArrayList<Account>();
		Crud crud = new Crud();
		conn = crud.getConnection(url, username, password);
		String selectAllQuery = "select * from account";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(selectAllQuery);
		while (rs.next()) {
			Account ac = new Account(0, selectAllQuery, null, selectAllQuery, selectAllQuery, selectAllQuery, null);
			ac.setAccount_id(rs.getInt(1));
			ac.setAccount_type(rs.getString(2));
			ac.setOpening_bal(rs.getFloat(3));
			ac.setUser_id(rs.getString(4));
			ac.setStatus(rs.getString(5));
			ac.setAccount_number(rs.getString(6));
			ac.setInterest_rate(rs.getFloat(7));
			account_list.add(ac);
		}
		return account_list;
	}

	public Account findById(int id) throws SQLException {
		Account ac = new Account(id, url, null, url, url, url, null);
		Crud crud = new Crud();
		conn = crud.getConnection(url, username, password);
		String selectAllQuery = "select * from account where Account_id=" + id;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(selectAllQuery);
		if (rs.next()) {
			ac.setAccount_id(rs.getInt(1));
			ac.setAccount_type(rs.getString(2));
			ac.setOpening_bal(rs.getFloat(3));
			ac.setUser_id(rs.getString(4));
			ac.setStatus(rs.getString(5));
			ac.setAccount_number(rs.getString(6));
			ac.setInterest_rate(rs.getFloat(7));
		}
		return ac;
	}

	public void save(Account ac) throws SQLException {
		Crud crud = new Crud();
		conn = crud.getConnection(url, username, password);
		String insertQuery = "insert into account (Account_id,Account_type,opening_bal,user_id,Status,Account_number,interest_rate) values (?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(insertQuery);
		pstmt.setInt(1, ac.getAccount_id());
		pstmt.setString(2, ac.getAccount_type());
		pstmt.setFloat(3, ac.getOpening_bal());
		pstmt.setString(4, ac.getUser_id());
		pstmt.setString(5, ac.getStatus());
		pstmt.setString(6, ac.getAccount_number());
		pstmt.setFloat(7, ac.getInterest_rate());
		
		int insertStatus = 0;
		insertStatus = pstmt.executeUpdate();

		if (insertStatus > 0)
			System.out.println("1 Record Inserted Successfully!!!");
	}

	public void update(int id, Account ac) throws SQLException {
		Crud crud = new Crud();
		conn = crud.getConnection(url, username, password);
		String updateQuery = "update  account set opening_bal=?,status=?,interest_rate=? where Account_id= ?";
		pstmt = conn.prepareStatement(updateQuery);
		pstmt.setInt(1, ac.getAccount_id());
		pstmt.setFloat(2, ac.getOpening_bal());
		pstmt.setString(3, ac.getStatus());
		pstmt.setFloat(4, ac.getInterest_rate());
		
		int updateStatus = 0;
		updateStatus = pstmt.executeUpdate();

		if (updateStatus > 0)
			System.out.println("1 Record Updated Successfully!!!");
	}

	public void delete(int id) throws SQLException {
		Crud crud = new Crud();
		conn = crud.getConnection(url, username, password);
		String deleteQuery = "delete from account where Account_id=" + id;
		stmt = conn.createStatement();
		int deleteStatus = 0;
		deleteStatus = stmt.executeUpdate(deleteQuery);

		if (deleteStatus > 0)
			System.out.println("1 Record Deleted Successfully!!!");
	}

	public Connection getConnection(String url, String username, String password) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public void closeResource() throws SQLException {
		if (stmt != null)
			stmt.close();
		if (pstmt != null)
			pstmt.close();
		if (rs != null)
			rs.close();
		if (conn != null)
			conn.close();
	}
}







package com.jdbc;

public class Account {
		private int Account_id;
		private String Account_type;
		private Float opening_bal;
		private String User_id;
		private String Status;
		private String Account_number;
		private Float interest_rate;
		public int getAccount_id() {
			return Account_id;
		}
		public void setAccount_id(int account_id) {
			Account_id = account_id;
		}
		public String getAccount_type() {
			return Account_type;
		}
		public void setAccount_type(String account_type) {
			Account_type = account_type;
		}
		public Float getOpening_bal() {
			return opening_bal;
		}
		public void setOpening_bal(Float opening_bal) {
			this.opening_bal = opening_bal;
		}
		public String getUser_id() {
			return User_id;
		}
		public void setUser_id(String user_id) {
			User_id = user_id;
		}
		public String getStatus() {
			return Status;
		}
		public void setStatus(String status) {
			Status = status;
		}
		public String getAccount_number() {
			return Account_number;
		}
		public void setAccount_number(String account_number) {
			Account_number = account_number;
		}
		public Float getInterest_rate() {
			return interest_rate;
		}
		public void setInterest_rate(Float interest_rate) {
			this.interest_rate = interest_rate;
		}
		public Account(int account_id, String account_type, Float opening_bal, String user_id, String status,
				String account_number, Float interest_rate) {
			super();
			Account_id = account_id;
			Account_type = account_type;
			this.opening_bal = opening_bal;
			User_id = user_id;
			Status = status;
			Account_number = account_number;
			this.interest_rate = interest_rate;
		}
		
		@Override
		public String toString() {
			return "account [Account_id=" + Account_id + ", Account_type=" + Account_type + ", opening_bal=" + opening_bal
					+ ", User_id=" + User_id + ", Status=" + Status + ", Account_number=" + Account_number
					+ ", interest_rate=" + interest_rate + "]";
		}
		

	
}



