package com.tresbu.nvidia.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.mysql.jdbc.PreparedStatement;

public class DBConnection {

	@Autowired
	private DataSource mDataSource;
	
	protected ResultSet mResultSet = null;
	protected PreparedStatement mPreparedStatement = null;
	
	public PreparedStatement prepareStatement(String prepareStatement) throws SQLException {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = (PreparedStatement) getConnection().prepareStatement(prepareStatement);
		} catch (SQLException e) {
			throw e;
		}

		return preparedStatement;
	}

	public void setAutoCommit(boolean pSetAutoCommit) throws SQLException {
		try {
			if (getConnection() != null && !getConnection().isClosed()) {
				getConnection().setAutoCommit(pSetAutoCommit);
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	public void commit() throws SQLException {
		try {
			if (getConnection() != null && !getConnection().isClosed()) {
				getConnection().commit();
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	public void rollback() throws SQLException {
		try {
			if (getConnection() != null && !getConnection().isClosed())
				getConnection().rollback();
		} catch (SQLException e) {
			throw e;
		}
	}

	public void close() throws SQLException {
		try {
			
			if (mPreparedStatement != null && !mPreparedStatement.isClosed()) {
				mPreparedStatement.close();
				mPreparedStatement = null;
			}
			
			if (mResultSet != null && !mResultSet.isClosed()) {
				mResultSet.close();
				mResultSet = null;
			}
			
			if (getConnection() != null && !getConnection().isClosed()) {
				getConnection().close();
				
			}
			
		} catch (SQLException e) {
			throw e;
		}
	}

	public ResultSet getResultSet() {
		return mResultSet;
	}

	public DataSource getDataSource() {
		return mDataSource;
	}
	
	public Connection getConnection() throws SQLException{
		return mDataSource.getConnection();
	}

}
