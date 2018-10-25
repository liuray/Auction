package com.ebay.dao;

import java.sql.SQLException;
import java.util.LinkedList;

import com.ebay.entity.IphoneEntity;
import com.ebay.entity.NokiaEntity;
import com.ebay.entity.ProductEntity;
import com.ebay.entity.SumsungEntity;

public interface ProductDao {
	public LinkedList<ProductEntity> searchProduct(String pid)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

	public LinkedList<SumsungEntity> searchSumsung(String pid)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

	public LinkedList<NokiaEntity> searchNokia(String pid)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

	public LinkedList<IphoneEntity> searchIphone(String pid)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

	public void insertProduct(ProductEntity product)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException;

	public void insertIphone(IphoneEntity Iphone)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

	public void insertNokia(NokiaEntity Nokia)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

	public void insertSumsung(SumsungEntity Sumsung)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

	public void deleteProduct(String pid)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

}
