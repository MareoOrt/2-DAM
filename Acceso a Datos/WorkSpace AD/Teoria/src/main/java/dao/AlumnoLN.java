package dao;

import java.sql.SQLException;

import entitties.Alumno;

public class AlumnoLN {

	public static String alta(Alumno a) throws SQLException, Exception {
		if (AlumnoDao.select(a) == null) {
			AlumnoDao.insert(a);
			return "El alumno se ha guardado correctamente";
		}else{
			return "El alumno ya existe.";
		}
	}
	
	public static String baja(Alumno a) throws SQLException, Exception {
		if (AlumnoDao.select(a) != null) {
			AlumnoDao.insert(a);
			return "Baja del alumno realizada correctamente.";
		}else{
			return "El alumno no existe.";
		}
	}
	
	public static String actualiza(Alumno a) throws SQLException, Exception {
		String msj = (AlumnoDao.update(a)>0)? "Alumno actualizado correctamente.": "Fallo en la actualizacion del alumno.";
		return msj;
	}

	public static String seleccionar(Alumno a) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
