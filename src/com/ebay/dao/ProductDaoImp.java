package com.ebay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.ebay.entity.ProductEntity;
import com.ebay.entity.IphoneEntity;
import com.ebay.entity.NokiaEntity;
import com.ebay.entity.SumsungEntity;

public class ProductDaoImp implements ProductDao {
	ApplicationDAO applicationDao = new ApplicationDAO();

	public LinkedList<ProductEntity> searchProduct(String pid)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		LinkedList<ProductEntity> ps = new LinkedList<ProductEntity>();
		String selectSql = "select * from Product as p where p.P_id = '" + pid + "'";
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(selectSql);
		int length = 0;
		ResultSet rs = pS.executeQuery();
		while (rs.next()) {
			ps.add(new ProductEntity(rs.getString("P_id"), rs.getString("P_description"), rs.getDouble("P_price"),
					rs.getString("P_name")));
		}
		pS.close();
		dataBasesConnection.close();

		return ps;
	}

	public LinkedList<SumsungEntity> searchSumsung(String pid)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		LinkedList<SumsungEntity> listt = new LinkedList<SumsungEntity>();
		String selectSql = "select * from P_Sumsung as p where p.P_id = '" + pid + "'";
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(selectSql);
		int length = 0;
		ResultSet rs = pS.executeQuery();
		while (rs.next()) {
			listt.add(new SumsungEntity(rs.getString("P_id"), rs.getInt("S_storage_inGB"), rs.getString("S_type")));
		}
		pS.close();
		dataBasesConnection.close();

		return listt;
	}

	public LinkedList<NokiaEntity> searchNokia(String pid)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		LinkedList<NokiaEntity> listt = new LinkedList<NokiaEntity>();
		String selectSql = "select * from P_Nokia as p where p.P_id = '" + pid + "'";
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(selectSql);
		int resLength = 0;
		ResultSet rs = pS.executeQuery();
		while (rs.next()) {
			listt.add(new NokiaEntity(rs.getString("P_id"), rs.getString("N_storage_inGB"), rs.getString("N_type")));
		}
		pS.close();
		dataBasesConnection.close();

		return listt;
	}

	public LinkedList<IphoneEntity> searchIphone(String pid)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		LinkedList<IphoneEntity> listt = new LinkedList<IphoneEntity>();
		String selectSql = "select * from P_Iphone as p where p.P_id = '" + pid + "'";
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(selectSql);
		int length = 0;
		ResultSet rs = pS.executeQuery();
		while (rs.next()) {
			listt.add(new IphoneEntity(rs.getString("P_id"), rs.getInt("I_storage_inGB"), rs.getString("I_type")));
		}
		pS.close();
		dataBasesConnection.close();

		return listt;
	}

	public void insertProduct(ProductEntity product)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		String insertsql = "insert into Product" + " (P_id, P_description, P_price, P_name)" + " values(?,?,?,?)";
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(insertsql);

		pS.setString(1, product.getP_id());
		pS.setString(2, product.getP_description());
		pS.setDouble(3, product.getP_price());
		pS.setString(4, product.getP_name());
		System.out.println("Product added");

		pS.executeUpdate();

		pS.close();
		dataBasesConnection.close();
	}

	public void insertIphone(IphoneEntity Iphone)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		String insertsql = "insert into P_Iphone" + " (P_id, I_storage_inGB, I_type)" + " values(?,?,?)";
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(insertsql);

		pS.setString(1, Iphone.getP_id());
		pS.setInt(2, Iphone.getI_storage_inGB());
		pS.setString(3, Iphone.getI_type());
		System.out.println("Iphone added");

		pS.executeUpdate();

		pS.close();
		dataBasesConnection.close();
	}

	public void insertNokia(NokiaEntity Nokia)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		String insertsql = "insert into P_Nokia" + " (P_id, N_storage_inGB, N_type)" + " values(?,?,?)";
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(insertsql);

		pS.setString(1, Nokia.getP_id());
		pS.setString(2, Nokia.getN_storage_inGB());
		pS.setString(3, Nokia.getN_type());
		System.out.println("Nokia added");

		pS.executeUpdate();

		pS.close();
		dataBasesConnection.close();
	}

	public void insertSumsung(SumsungEntity Sumsung)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		String insertsql = "insert into P_Sumsung" + " (P_id, S_storage_inGB, S_type)" + " values(?,?,?)";
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(insertsql);

		pS.setString(1, Sumsung.getP_id());
		pS.setInt(2, Sumsung.getS_storage_inGB());
		pS.setString(3, Sumsung.getS_type());
		System.out.println("Sumsung added");

		pS.executeUpdate();

		pS.close();
		dataBasesConnection.close();
	}

	public void deleteProduct(String pid)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		String deleteSql = "delete from Product where P_id = '" + pid + "'";
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(deleteSql);
		pS.execute();
		pS.close();
		dataBasesConnection.close();
	}
}
