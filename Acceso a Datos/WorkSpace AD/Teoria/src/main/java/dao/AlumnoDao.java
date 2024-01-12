package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Result;

import ejemplo_Connection.ConexionBD;
import entitties.Alumno;

public class AlumnoDao {

	public static int insert(Alumno a) throws SQLException, Exception {

		String sql = "INSERT INTO matriculas.alumnos VALUES (?,?,?,?,?)";

		try (Connection conn = ConexionBD.getConnection()) {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, a.getIdmatricula());
			ps.setString(2, a.getNombre());
			ps.setString(3, a.getApellidos());
			ps.setString(4, a.getCiclo());
			ps.setString(5, a.getCurso());
			return ps.executeUpdate();
		}
	}

	public static int delete(Alumno a) throws SQLException, Exception {

		String sql = "DELETE FROM matriculas.alumnos WHERE nMatricula=?";

		try (Connection conn = ConexionBD.getConnection()) {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, a.getIdmatricula());
			return ps.executeUpdate();
		}
	}
	
	public static int update(Alumno a) throws SQLException, Exception {

		String sql = "UPDATE matriculas.alumnos SET CICLO=?, CURSO=? WHERE nMatricula=?";

		try (Connection conn = ConexionBD.getConnection()) {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, a.getCiclo());
			ps.setString(2, a.getCurso());
			ps.setString(3, a.getIdmatricula());
			return ps.executeUpdate();
		}
	}
	
	public static Alumno select(Alumno a) throws SQLException, Exception {

		String sql = "SELECT * FROM matricula.alumno a WHERE nMatricula=?";
		Alumno alumno = null;
		
		
		try (Connection conn = ConexionBD.getConnection()) {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, a.getIdmatricula());
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				alumno = new Alumno();
				
				alumno.setNombre(rs.getString(2));
				alumno.setApellidos(rs.getString(3));
				alumno.setCurso(rs.getString(4));
				alumno.setCiclo(rs.getString(5));
			}
			return alumno;
		}
	}
	
	
}
