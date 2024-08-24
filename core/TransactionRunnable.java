package com.echo.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * 事务工作接口
 * 任何需要事务的业务都可以实现该接口，比如出库入库操作
 */
public interface TransactionRunnable {

    List<Statement> execute(Connection conn) throws Exception;

}
