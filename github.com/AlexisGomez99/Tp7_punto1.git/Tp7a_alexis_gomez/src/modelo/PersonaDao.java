package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonaDao implements Envoltorio{
	
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/epp_personas";
    private static final String USUARIO = "root";
    private static final String CLAVE="";
    
    
    //Sujeto real 
    
	private Connection obtenerConexion() throws ClassNotFoundException, SQLException {
		Connection conexion = null;
        Class.forName(CONTROLADOR);
        return conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
	}

	/*public Persona personaPorId(int id) {
		String sql = "select p.nombre,t.numero " + "from personas p, telefonos t "
				+ "where p.id = t.idpersona and p.id = ?";
		try (Connection conn = obtenerConexion(); PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			Set<Telefono> telefonos = new HashSet<Telefono>();
			String nombrePersona = null;
			while (result.next()) {
				nombrePersona = result.getString(1);
				telefonos.add(new Telefono(result.getString(2)));
			}
			return new Persona(id, nombrePersona, telefonos);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e1) {
			throw new RuntimeException(e1);
		}
	
	}*/
	public Persona personaPorId(int id) {
		Connection myConexion;
		try {
			myConexion = obtenerConexion();
			Statement sent;
			sent= myConexion.createStatement();
			ResultSet resul= sent.executeQuery("SELECT * FROM `personas` p");
			String nombrePersona = null;
			while (resul.next()) {
				if(resul.getInt("p.id")==id)
					nombrePersona = resul.getString("p.nombre");
			}
			return new Persona(id, nombrePersona, telefonosPorId(id));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e1) {
			throw new RuntimeException(e1);
		}
	
	}
	
	public void agregarPersona(Persona persona) {
		Connection myConexion;
		try {
			myConexion = obtenerConexion();
			Statement sent;
			sent= myConexion.createStatement();
			sent.executeUpdate("INSERT INTO `personas`(`nombre`, `id`) VALUES ('"+persona.nombre()+"',"+persona.idPersona()+")");
			int idtel=0;
			for (Telefono tel: persona.telefonos()) {
				sent.executeUpdate("INSERT INTO `telefonos`(`idpersona`, `numero`, `id`) VALUES ("+persona.idPersona()+","+tel.numero()+","+idtel+")");
			}
			sent.close();
			myConexion.close();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Set<Telefono> telefonosPorId(int id) {
		Connection myConexion;
		Set<Telefono> telefonos= new HashSet<>();
		try {
			myConexion = obtenerConexion();
			Statement sent;
			sent= myConexion.createStatement();
			ResultSet resul= sent.executeQuery("Select * from `telefonos` u");
			while(resul.next()) {
				if(resul.getInt("u.idpersona")==id)
				telefonos.add(new Telefono(resul.getString("u.numero")));
			}
			resul.close();
			sent.close();
			myConexion.close();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return telefonos;
	}
}