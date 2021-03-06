           Java Persistence for Relational Databases
           =========================================

REQUIREMENTS

* MySQL JDBC Driver which can be downloaded from http://www.mysql.com
* Java Software Development Kit 1.4.x for JDBC 3 functionality which can be 
obtained from http://java.sun.com
* MySQL run environment, either the MySQL Control Center from http://mysql.com
or the command line MysQL interface

INSTALLATION

* Download and install JDBC driver.  Copy mysql-connector-java-<version>-bin.jar 
to lib/ext. 
* Build the files by running ant.
* Create the database by running sql/jdbc21.sql and sql/jdbc3.sql

The commands to run the JDBC 2.1 code are:
run com.apress.javapersistence.chapter04.jdbc21.BrowseDatabase root password
run com.apress.javapersistence.chapter04.jdbc21.BatchUpdate root password
run com.apress.javapersistence.chapter04.jdbc21.PreparedStatementBatchUpdate root password

When running the JDBC 2.0 optional code pooled connection you need to have a 
JNDI service running:
run com.apress.javapersistence.chapter04.jdbc20stdext.SetupJNDIDataSource root password
run com.apress.javapersistence.chapter04.jdbc20stdext.PooledConnectionExample

Then for the JDBC 2.0 code run:
run com.apress.javapersistence.chapter04.jdbc20stdext.RowSetExample root password

The JDBC 3 examples need JDK 1.4 installed and a JDBC driver/database that 
supports save points.  As of this writing MySQL does not suport savepoints. 
Oracle however does.