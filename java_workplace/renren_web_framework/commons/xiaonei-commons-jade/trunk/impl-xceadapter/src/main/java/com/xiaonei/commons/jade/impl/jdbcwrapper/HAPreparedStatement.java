package com.xiaonei.commons.jade.impl.jdbcwrapper;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;

/**
 * 实现可扩展数据库访问策略的: JDBC 参数化查询语句。
 * 
 * @author han.liao
 */
public abstract class HAPreparedStatement extends BasePreparedStatement {

    protected final HAConnection connection;

    /**
     * 构造: XcePreparedStatement 实例。
     * 
     * @param connection - 创建的连接。
     */
    public HAPreparedStatement(HAConnection connection) {

        this.connection = connection;
    }

    @Override
    public void addBatch(String sql) throws SQLException {

        if (connection.analyzeSql(sql)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.addBatch(sql);
    }

    @Override
    public boolean execute(String sql) throws SQLException {

        if (connection.analyzeSql(sql)) {

            freeStatement(); // 关闭当前的查询语句
        }

        return super.execute(sql);
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {

        if (connection.analyzeSql(sql)) {

            freeStatement(); // 关闭当前的查询语句
        }

        return super.execute(sql, autoGeneratedKeys);
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {

        if (connection.analyzeSql(sql)) {

            freeStatement(); // 关闭当前的查询语句
        }

        return super.execute(sql, columnIndexes);
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {

        if (connection.analyzeSql(sql)) {

            freeStatement(); // 关闭当前的查询语句
        }

        return super.execute(sql, columnNames);
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {

        if (connection.analyzeSql(sql)) {

            freeStatement(); // 关闭当前的查询语句
        }

        return super.executeUpdate(sql);
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {

        if (connection.analyzeSql(sql)) {

            freeStatement(); // 关闭当前的查询语句
        }

        return super.executeUpdate(sql, autoGeneratedKeys);
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {

        if (connection.analyzeSql(sql)) {

            freeStatement(); // 关闭当前的查询语句
        }

        return super.executeUpdate(sql, columnIndexes);
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {

        if (connection.analyzeSql(sql)) {

            freeStatement(); // 关闭当前的查询语句
        }

        return super.executeUpdate(sql, columnNames);
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {

        if (connection.analyzeSql(sql)) {

            freeStatement(); // 关闭当前的查询语句
        }

        return super.executeQuery(sql);
    }

    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.ARRAY)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setArray(parameterIndex, x);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.LONGVARBINARY)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.LONGVARBINARY)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.LONGVARBINARY)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setAsciiStream(parameterIndex, x);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.NUMERIC)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setBigDecimal(parameterIndex, x);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.LONGVARBINARY)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.LONGVARBINARY)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.LONGVARBINARY)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setBinaryStream(parameterIndex, x);
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.BLOB)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setBlob(parameterIndex, x);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length)
            throws SQLException {

        if (connection.analyzeParam(parameterIndex, inputStream, Types.BLOB)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setBlob(parameterIndex, inputStream, length);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {

        if (connection.analyzeParam(parameterIndex, inputStream, Types.BLOB)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setBlob(parameterIndex, inputStream);
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.BOOLEAN)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setBoolean(parameterIndex, x);
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.TINYINT)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setByte(parameterIndex, x);
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.VARBINARY)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setBytes(parameterIndex, x);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length)
            throws SQLException {

        if (connection.analyzeParam(parameterIndex, reader, Types.LONGVARCHAR)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length)
            throws SQLException {

        if (connection.analyzeParam(parameterIndex, reader, Types.LONGVARCHAR)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {

        if (connection.analyzeParam(parameterIndex, reader, Types.LONGVARCHAR)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setCharacterStream(parameterIndex, reader);
    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.CLOB)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setClob(parameterIndex, x);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {

        if (connection.analyzeParam(parameterIndex, reader, Types.CLOB)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setClob(parameterIndex, reader, length);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {

        if (connection.analyzeParam(parameterIndex, reader, Types.CLOB)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setClob(parameterIndex, reader);
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.DATE)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setDate(parameterIndex, x, cal);
    }

    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.DATE)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setDate(parameterIndex, x);
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.DOUBLE)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setDouble(parameterIndex, x);
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.REAL)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setFloat(parameterIndex, x);
    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.INTEGER)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setInt(parameterIndex, x);
    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.BIGINT)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setLong(parameterIndex, x);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length)
            throws SQLException {

        if (connection.analyzeParam(parameterIndex, value, Types.LONGNVARCHAR)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setNCharacterStream(parameterIndex, value, length);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {

        if (connection.analyzeParam(parameterIndex, value, Types.LONGNVARCHAR)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setNCharacterStream(parameterIndex, value);
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {

        if (connection.analyzeParam(parameterIndex, value, Types.NCLOB)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setNClob(parameterIndex, value);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {

        if (connection.analyzeParam(parameterIndex, reader, Types.NCLOB)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setNClob(parameterIndex, reader, length);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {

        if (connection.analyzeParam(parameterIndex, reader, Types.NCLOB)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setNClob(parameterIndex, reader);
    }

    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {

        if (connection.analyzeParam(parameterIndex, value, Types.NVARCHAR)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setNString(parameterIndex, value);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {

        if (connection.analyzeParam(parameterIndex, null, sqlType)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setNull(parameterIndex, sqlType, typeName);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {

        if (connection.analyzeParam(parameterIndex, null, sqlType)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setNull(parameterIndex, sqlType);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength)
            throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, targetSqlType)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, targetSqlType)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setObject(parameterIndex, x, targetSqlType);
    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.JAVA_OBJECT)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setObject(parameterIndex, x);
    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.REF)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setRef(parameterIndex, x);
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.ROWID)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setRowId(parameterIndex, x);
    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.SMALLINT)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setShort(parameterIndex, x);
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {

        if (connection.analyzeParam(parameterIndex, xmlObject, Types.SQLXML)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setSQLXML(parameterIndex, xmlObject);
    }

    @Override
    public void setString(int parameterIndex, String x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.VARCHAR)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setString(parameterIndex, x);
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.TIME)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setTime(parameterIndex, x, cal);
    }

    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.TIME)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setTime(parameterIndex, x);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.TIMESTAMP)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setTimestamp(parameterIndex, x, cal);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.TIMESTAMP)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setTimestamp(parameterIndex, x);
    }

    @Override
    @Deprecated
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {

        if (connection.analyzeParam(parameterIndex, x, Types.LONGVARCHAR)) {

            freeStatement(); // 关闭当前的查询语句
        }

        super.setUnicodeStream(parameterIndex, x, length);
    }

    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        // TODO Auto-generated method stub
        super.setURL(parameterIndex, x);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connection;
    }
}
