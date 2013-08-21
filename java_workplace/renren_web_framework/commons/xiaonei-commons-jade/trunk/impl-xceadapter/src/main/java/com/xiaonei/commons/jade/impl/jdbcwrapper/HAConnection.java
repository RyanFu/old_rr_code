package com.xiaonei.commons.jade.impl.jdbcwrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.xiaonei.commons.jade.impl.util.JdbcUtils;

/**
 * 实现可扩展数据库访问策略的: JDBC 连接。
 * 
 * TODO: [NEED-FIX] 因为目前分表参数只在 HAConnection 中保存一份, 如果同时创建几个 Statement /
 * PreparedStatement, 由于覆盖共享信息，在执行时会切换到错误的数据库，或者生成错误的重写语句。
 * 
 * 目前看来, 这种同时创建多个 Statement 的用法很少见。
 * 
 * @author han.liao
 */
public class HAConnection extends BaseConnection {

    @Override
    public Statement createStatement() throws SQLException {

        return new HAStatement(this) {

            @Override
            protected Statement createStatement() throws SQLException {

                return connection.underlyCreateStatement();
            }
        };
    }

    @Override
    public Statement createStatement(final int resultSetType, final int resultSetConcurrency)
            throws SQLException {

        return new HAStatement(this) {

            @Override
            protected Statement createStatement() throws SQLException {

                return connection.underlyCreateStatement(resultSetType, resultSetConcurrency);
            }
        };
    }

    @Override
    public Statement createStatement(final int resultSetType, final int resultSetConcurrency,
            final int resultSetHoldability) throws SQLException {

        return new HAStatement(this) {

            @Override
            protected Statement createStatement() throws SQLException {

                return connection.underlyCreateStatement(resultSetType, resultSetConcurrency,
                        resultSetHoldability);
            }
        };
    }

    @Override
    public PreparedStatement prepareStatement(final String sql) throws SQLException {

        analyzeSql(sql);

        return new HAPreparedStatement(this) {

            @Override
            protected PreparedStatement createStatement() throws SQLException {

                // 重写查询语句。
                String rewritten = connection.rewriteSql(sql);

                return connection.underlyPrepareStatement(rewritten);
            }
        };
    }

    @Override
    public PreparedStatement prepareStatement(final String sql, final int resultSetType,
            final int resultSetConcurrency) throws SQLException {

        analyzeSql(sql);

        return new HAPreparedStatement(this) {

            @Override
            protected PreparedStatement createStatement() throws SQLException {

                // 重写查询语句。
                String rewritten = connection.rewriteSql(sql);

                return connection.underlyPrepareStatement(rewritten, resultSetType,
                        resultSetConcurrency);
            }
        };
    }

    @Override
    public PreparedStatement prepareStatement(final String sql, final int resultSetType,
            final int resultSetConcurrency, final int resultSetHoldability) throws SQLException {

        analyzeSql(sql);

        return new HAPreparedStatement(this) {

            @Override
            protected PreparedStatement createStatement() throws SQLException {

                // 重写查询语句。
                String rewritten = connection.rewriteSql(sql);

                return connection.underlyPrepareStatement(rewritten, resultSetType,
                        resultSetConcurrency, resultSetHoldability);
            }
        };
    }

    @Override
    public PreparedStatement prepareStatement(final String sql, final int autoGeneratedKeys)
            throws SQLException {

        analyzeSql(sql);

        return new HAPreparedStatement(this) {

            @Override
            protected PreparedStatement createStatement() throws SQLException {

                // 重写查询语句。
                String rewritten = connection.rewriteSql(sql);

                return connection.underlyPrepareStatement(rewritten, autoGeneratedKeys);
            }
        };
    }

    @Override
    public PreparedStatement prepareStatement(final String sql, final int[] columnIndexes)
            throws SQLException {

        analyzeSql(sql);

        return new HAPreparedStatement(this) {

            @Override
            protected PreparedStatement createStatement() throws SQLException {

                // 重写查询语句。
                String rewritten = connection.rewriteSql(sql);

                return connection.underlyPrepareStatement(rewritten, columnIndexes);
            }
        };
    }

    @Override
    public PreparedStatement prepareStatement(final String sql, final String[] columnNames)
            throws SQLException {

        analyzeSql(sql);

        return new HAPreparedStatement(this) {

            @Override
            protected PreparedStatement createStatement() throws SQLException {

                // 重写查询语句。
                String rewritten = connection.rewriteSql(sql);

                return connection.underlyPrepareStatement(rewritten, columnNames);
            }
        };
    }

    @Override
    public CallableStatement prepareCall(final String sql, final int resultSetType,
            final int resultSetConcurrency, final int resultSetHoldability) throws SQLException {

        analyzeSql(sql);

        return new HACallableStatement(this) {

            @Override
            protected CallableStatement createStatement() throws SQLException {

                // 重写查询语句。
                String rewritten = connection.rewriteSql(sql);

                return connection.underlyPrepareCall(rewritten, resultSetType,
                        resultSetConcurrency, resultSetHoldability);
            }
        };
    }

    @Override
    public CallableStatement prepareCall(final String sql, final int resultSetType,
            final int resultSetConcurrency) throws SQLException {

        analyzeSql(sql);

        return new HACallableStatement(this) {

            @Override
            protected CallableStatement createStatement() throws SQLException {

                // 重写查询语句。
                String rewritten = connection.rewriteSql(sql);

                return connection
                        .underlyPrepareCall(rewritten, resultSetType, resultSetConcurrency);
            }
        };
    }

    @Override
    public CallableStatement prepareCall(final String sql) throws SQLException {

        analyzeSql(sql);

        return new HACallableStatement(this) {

            @Override
            protected CallableStatement createStatement() throws SQLException {

                // 重写查询语句。
                String rewritten = connection.rewriteSql(sql);

                return connection.underlyPrepareCall(rewritten);
            }
        };
    }

    /**
     * 解析给定的查询语句，并且返回是否释放连接的标志。
     * 
     * @param sql - 指定的查询语句。
     * 
     * @return 是否释放连接的标志。
     * 
     * @throws SQLException
     */
    protected boolean analyzeSql(String sql) throws SQLException {

        // [IMPLEMENT-NOTICE]: 解析给定的查询语句，如果查询语句的应该发到另外的数据库，或者
        // 包含不同位置的数据表，或者在散表策略中需要切换，则应该调用 freeConnection(), 
        // 并且返回 <code>true</code>.

        if (logger.isInfoEnabled()) {
            logger.info("Ignoring analyzing: " + sql);
        }

        return false;
    }

    /**
     * 分析给定的查询语句，并返回重写的查询语句。
     * 
     * @param sql - 指定的查询语句。
     * 
     * @return 重写的查询语句。
     */
    protected String rewriteSql(String sql) {

        // [IMPLEMENT-NOTICE]: 解析给定的查询语句，替换其中的表名或参数名。

        if (logger.isInfoEnabled()) {
            logger.info("Ignoring rewriting: " + sql);
        }

        return sql;
    }

    /**
     * 解析给定的查询参数，并且返回是否释放查询语句的标志。
     * 
     * @param parameterIndex - 查询参数的索引。
     * 
     * @param x - 设置的参数值。
     * 
     * @return 是否释放查询语句的标志。
     * 
     * @throws SQLException
     */
    protected boolean analyzeParam(int parameterIndex, Object x, int sqlType) throws SQLException {

        // [IMPLEMENT-NOTICE]: 解析指定的查询参数，如果导致散表策略中需要切换数据库或者重写，则
        // 应该调用 freeConnection() 并返回 <code>true</code>.

        if (logger.isInfoEnabled()) {
            logger.info("Ignoring analyzing Param: [" + parameterIndex + "] "
                    + JdbcUtils.getTypeName(sqlType) + ", " + x);
        }

        return false;
    }

    /**
     * 解析给定的查询参数，并且返回是否释放查询语句的标志。
     * 
     * @param parameterName - 查询参数的索引。
     * 
     * @param x - 设置的参数值。
     * 
     * @return 是否释放查询语句的标志。
     * 
     * @throws SQLException
     */
    protected boolean analyzeParam(String parameterName, Object x, int sqlType) throws SQLException {

        // [IMPLEMENT-NOTICE]: 解析指定的查询参数，如果导致散表策略中需要切换数据库或者重写，则
        // 应该调用 freeConnection() 并返回 <code>true</code>.

        if (logger.isInfoEnabled()) {
            logger.info("Ignoring analyzing Param: [" + parameterName + "] " + sqlType + ", " + x);
        }

        return false;
    }

    @Override
    protected Connection getConnection() throws SQLException {

        // [IMPLEMENT-NOTICE]: 使用收集的信息获取正确的数据库连接。

        if (logger.isWarnEnabled()) {
            logger.warn("Call superclass [HAConnection].getConnection(), "
                    + "but it's NOT implemented.");
        }

        throw new UnsupportedOperationException("HAConnection#getConnection not supported.");
    }

    /**
     * 调用原始的 createStatement 方法。
     * 
     * @throws SQLException
     */
    private Statement underlyCreateStatement() throws SQLException {

        return super.createStatement();
    }

    /**
     * 调用原始的 createStatement 方法。
     * 
     * @throws SQLException
     */
    private Statement underlyCreateStatement(int resultSetType, int resultSetConcurrency)
            throws SQLException {

        return super.createStatement(resultSetType, resultSetConcurrency);
    }

    /**
     * 调用原始的 createStatement 方法。
     * 
     * @throws SQLException
     */
    private Statement underlyCreateStatement(int resultSetType, int resultSetConcurrency,
            int resultSetHoldability) throws SQLException {

        return super.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /**
     * 调用原始的 prepareStatement 方法。
     * 
     * @throws SQLException
     */
    private PreparedStatement underlyPrepareStatement(String sql) throws SQLException {

        return super.prepareStatement(sql);
    }

    /**
     * 调用原始的 prepareStatement 方法。
     * 
     * @throws SQLException
     */
    private PreparedStatement underlyPrepareStatement(String sql, int resultSetType,
            int resultSetConcurrency) throws SQLException {

        return super.prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    /**
     * 调用原始的 prepareStatement 方法。
     * 
     * @throws SQLException
     */
    private PreparedStatement underlyPrepareStatement(String sql, int resultSetType,
            int resultSetConcurrency, int resultSetHoldability) throws SQLException {

        return super.prepareStatement(sql, resultSetType, resultSetConcurrency,
                resultSetHoldability);
    }

    /**
     * 调用原始的 prepareStatement 方法。
     * 
     * @throws SQLException
     */
    private PreparedStatement underlyPrepareStatement(String sql, int autoGeneratedKeys)
            throws SQLException {

        return super.prepareStatement(sql, autoGeneratedKeys);
    }

    /**
     * 调用原始的 prepareStatement 方法。
     * 
     * @throws SQLException
     */
    private PreparedStatement underlyPrepareStatement(String sql, int[] columnIndexes)
            throws SQLException {

        return super.prepareStatement(sql, columnIndexes);
    }

    /**
     * 调用原始的 prepareStatement 方法。
     * 
     * @throws SQLException
     */
    private PreparedStatement underlyPrepareStatement(String sql, String[] columnNames)
            throws SQLException {

        return super.prepareStatement(sql, columnNames);
    }

    /**
     * 调用原始的 prepareCall 方法。
     * 
     * @throws SQLException
     */
    private CallableStatement underlyPrepareCall(String sql, int resultSetType,
            int resultSetConcurrency, int resultSetHoldability) throws SQLException {

        return super.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /**
     * 调用原始的 prepareCall 方法。
     * 
     * @throws SQLException
     */
    private CallableStatement underlyPrepareCall(String sql, int resultSetType,
            int resultSetConcurrency) throws SQLException {

        return super.prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    /**
     * 调用原始的 prepareCall 方法。
     * 
     * @throws SQLException
     */
    private CallableStatement underlyPrepareCall(String sql) throws SQLException {

        return super.prepareCall(sql);
    }
}
