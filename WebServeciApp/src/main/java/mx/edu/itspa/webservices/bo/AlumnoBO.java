package mx.edu.itspa.webservices.bo;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;

import mx.edu.itspa.webservices.dao.*;
import mx.edu.itspa.webservices.dto.Alumno;
import mx.edu.itspa.webservices.general.DAOException;

@ManagedBean(name="alumnoBO")
@SessionScoped
public class AlumnoBO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Alumno alumno;
	private AlumnoDAO alumnoDAO;
	
	public AlumnoBO() {
		alumnoDAO = new AlumnoDAO();
		alumno = new Alumno();
	}
	
	public List<Alumno> getAlumnos(){
		try {
			return alumnoDAO.obtenerTodos();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String edit(String matricula) {
		try {
			alumno = alumnoDAO.obtener(matricula);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "add";
	}
	
	public String save() {
		try {
			if(alumno.getId()==0) {
				alumnoDAO.insertar(alumno);
			}else {
				alumnoDAO.modificar(alumno);
			}
			alumno = new Alumno();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "save";
	}
	
	public String eliminar(String matricula) {
		try {
			alumno = alumnoDAO.obtener(matricula);
			alumnoDAO.eliminar(alumno);
			alumno = new Alumno();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return "delete";
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}	
}