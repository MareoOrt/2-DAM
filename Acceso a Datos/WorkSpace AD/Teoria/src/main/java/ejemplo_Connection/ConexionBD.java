package ejemplo_Connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConexionBD {

	public static Connection getConnection() throws Exception {
		
		try {
			DataSource ds = (DataSource) new InitialContext().lookup("java:con/env");
			return ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("ConexionDB.getConnection:Error al conectar con la base de datos.");
			e.printStackTrace();
			throw new Exception("ConexionDB.getConnection:Error al conectar con la base de datos.");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.err.println("ConexionDB.getConnection:No se pudo obtener el DataSource.");
			e.printStackTrace();
			throw new Exception("ConexionDB.getConnection:No se pudo obtener el DataSource.");
		}

	}

}
