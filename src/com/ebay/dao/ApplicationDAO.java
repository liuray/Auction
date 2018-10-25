package com.ebay.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ebay.entity.SaleReportEntity;
import com.ebay.entity.SandP;

public class ApplicationDAO {
	public Connection getConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		 String connectionUrl = "jdbc:mysql://classvm58.cs.rutgers.edu:3306/yabe?autoReconnect=true";
		Connection connection = null;
		//String connectionUrl = "jdbc:mysql://localhost:3306/yabe";
		try {
			connection = DriverManager.getConnection(connectionUrl, "root", "GaoldBraid2");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}

	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertSandP(SandP sp)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		String insertsql = "insert into Supply_product	" + " (P_id, S_id)" + " values(?,?)";
		Connection dbConnection = getConnection();
		PreparedStatement preparedStatement = dbConnection.prepareStatement(insertsql);

		preparedStatement.setString(1, sp.getP_id());
		preparedStatement.setString(2, sp.getS_id());
		System.out.println("Supply_product added");

		preparedStatement.executeUpdate();

		preparedStatement.close();
		dbConnection.close();
	}

	@SuppressWarnings("unused")
	public SaleReportEntity generateReport()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		String sql = "{call sale_report(?,?,?,?,?,?,?,?)}";
		Connection dbConnection = getConnection();
		CallableStatement call = dbConnection.prepareCall(sql);
		call.registerOutParameter(1, java.sql.Types.DOUBLE);
		call.registerOutParameter(2, java.sql.Types.DOUBLE);
		call.registerOutParameter(3, java.sql.Types.DOUBLE);
		call.registerOutParameter(4, java.sql.Types.DOUBLE);
		call.registerOutParameter(5, java.sql.Types.DOUBLE);
		call.registerOutParameter(6, java.sql.Types.DOUBLE);
		call.registerOutParameter(7, java.sql.Types.VARCHAR);
		call.registerOutParameter(8, java.sql.Types.VARCHAR);
		call.executeUpdate();
		Double totalEarning = call.getDouble(1);
		Double earnpitem = call.getDouble(2);
		Double earntyped = call.getDouble(3);
		Double earntypet = call.getDouble(4);
		Double earntypel = call.getDouble(5);
		Double earnpseller = call.getDouble(6);
		String bsi = call.getString(7);
		String bb = call.getString(8);

		SaleReportEntity saleReport = new SaleReportEntity();
		if (totalEarning != null) {
			saleReport.setTotalEarning(totalEarning);
		} else {
			saleReport.setTotalEarning(0.0);
		}
		if (earnpitem != null) {
			saleReport.setEarnpitem(earnpitem);
		} else {
			saleReport.setEarnpitem(0.0);
		}
		if (earnpseller != null) {
			saleReport.setEarnpseller(earnpseller);
		} else {
			saleReport.setEarnpseller(0.0);
		}
		if (earntyped != null) {
			saleReport.setEarntyped(earntyped);
		} else {
			saleReport.setEarntyped(0.0);
		}
		if (earntypel != null) {
			saleReport.setEarntypel(earntypel);
		} else {
			saleReport.setEarntypel(0.0);
		}
		if (earntypet != null) {
			saleReport.setEarntypet(earntypet);
		} else {
			saleReport.setEarntypet(0.0);
		}
		if (bsi != null) {
			saleReport.setBsi(bsi);
		} else {
			saleReport.setBsi("");
		}
		if (bb != null) {
			saleReport.setBb(bb);
		} else {
			saleReport.setBb("none");
		}
		call.close();
		dbConnection.close();
		return saleReport;
	}

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		ApplicationDAO dao = new ApplicationDAO();
		Connection connection = dao.getConnection();
	}
}
