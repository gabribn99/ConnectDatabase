package connectiondb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabri
 */
public class MngDb {

    private String user, pass, databasename;
    private Connection connection;

    public MngDb(String user, String pass, String databasename) {
        this.user = user;
        this.pass = pass;
        this.databasename = databasename;
    }

    public MngDb() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDatabasename() {
        return databasename;
    }

    public void setDatabasename(String databasename) {
        this.databasename = databasename;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection conn) {
        this.connection = conn;
    }

    public void closeDB() {
        try
        {
            this.connection.close();
        } catch (SQLException ex)
        {
        }
    }

    public void openDB() {
        try
        {
            String host = "localhost";
            int port = 3306;
            String url = String.format("jdbc:mysql://%s:%d/%s?useSSL=false", host, port, databasename);
            this.connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex)
        {
        }
    }

    public boolean stDB() {
        try
        {
            if (this.connection != null)
            {
                return !this.connection.isClosed();
            }
        } catch (SQLException ex)
        {
        }
        return false;
    }
//--------------------------------------------------------------------------------//

    public List<Object[]> showAnimals() {
        List<Object[]> lista = new ArrayList<>();
        if (connection != null)
        {
            try
            {
                Statement st = connection.createStatement();
                ResultSet resultset = st.executeQuery("select * from animales");
                while (resultset.next())
                {
                    Object[] fila = new Object[4];
                    fila[0] = resultset.getInt("id");
                    fila[1] = resultset.getInt("propietario");
                    fila[2] = resultset.getString("animal");
                    fila[3] = resultset.getString("raza");

                    lista.add(fila);
                }
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return lista;
    }
}
