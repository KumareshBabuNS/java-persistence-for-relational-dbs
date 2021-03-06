/*
 * DemoSavepoint.java
 *
 * Created on June 24, 2002, 9:41 PM
 */

package com.apress.javapersistence.chapter04.jdbc3;

import java.sql.*;
import java.io.*;

/**
 *
 * @author  rsperko
 */
public class DemoSavepoint {
    private String username;
    private String password;

    /** Creates a new instance of DemoSavepoint */
    public DemoSavepoint(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if( args.length < 2 ) {
            System.out.println( "usage: DemoSavepoint username password" );
            return;
        }

        DemoSavepoint printDb
            = new DemoSavepoint( args[ 0 ], args[ 1 ] );
        printDb.execute();
    }

    public void execute() {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName( "org.hsqldb.jdbcDriver" ).newInstance();
            String url = "jdbc:hsqldb:hsqldb\\chapter04";
            connection = DriverManager.getConnection( url, username, password );
            connection.setAutoCommit(false);

            statement = connection.createStatement();

            String update1 = "UPDATE employees "
                + "SET email = 'dglyzewski@centare.com' "
                + "WHERE email = 'dave.glyzewski@centare.com'";
            statement.executeUpdate( update1 );
            Savepoint savepoint1 = connection.setSavepoint("savepoint1");

            String update2 = "UPDATE employees "
                + "SET email = 'echaltry@centare.com' "
                + "WHERE email = 'ed.chaltry@centare.com'";
            statement.executeUpdate( update2 );
            Savepoint savepoint2 = connection.setSavepoint("savepoint2");

            String update3 = "UPDATE employees "
                + "SET email = 'dsmith@centare.com' "
                + "WHERE email = 'dustin.smith@centare.com'";
            statement.executeUpdate( update3 );
            Savepoint savepoint3 = connection.setSavepoint("savepoint3");

            String update4 = "UPDATE employees "
                + "SET email = 'stomczak@centare.com' "
                + "WHERE email = 'susan.tomczak@centare.com'";
            statement.executeUpdate( update4 );
            Savepoint savepoint4 = connection.setSavepoint("savepoint4");

            String update5 = "UPDATE employees "
                + "SET email = 'jcarnell@centare.com' "
                + "WHERE email = 'john.carnell@centare.com'";
            statement.executeUpdate( update5 );
            Savepoint savepoint5 = connection.setSavepoint("savepoint5");

            connection.rollback(savepoint3);
            connection.commit();
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
        finally {
            if( statement != null ) {
                try {
                    statement.close();
                }
                catch( SQLException e ){} // nothing we can do
            }
            if( connection != null ) {
                try {
                    connection.close();
                }
                catch( SQLException e ){} // nothing we can do
            }
        }
    }
}
