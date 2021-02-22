package org.fp.beauty.parlor.model.dao.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionPool.class);
    private ConnectionPool() {
    }

    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance(){
        if (instance==null)
            instance = new ConnectionPool();
        return instance;
    }


    public Connection getConnection(){
        Context ctx;
        Connection con = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/postgresPoolCon");
            con = ds.getConnection();
        } catch (NamingException e) {
            logger.error(e.getMessage(),e);
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
        return con;
    }

}
