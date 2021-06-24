package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import modelo.Persona;
import modelo.ProxyHashSet;
import modelo.Server;
import modelo.Telefono;


public class PersonaDao implements Server {

	public Persona personaPorId(int id) {
		String sql = "select p.nombre " + "from personas p " + "where p.id = ?";

		try (Connection conn = ConexionBaseDeDatos.getConection();
				PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			Set<Telefono> telefonos = new ProxyHashSet<Telefono>(id);
			String nombrePersona = null;

			while (result.next()) {
				nombrePersona = result.getString(1);
			}

			return new Persona(id, nombrePersona, telefonos);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Set<Telefono> obtenerTelefono(int id) {
		
		String sql = "Select * from `telefonos` u";
		try (Connection conn = ConexionBaseDeDatos.getConection();
				PreparedStatement statement = conn.prepareStatement(sql);) {

			ResultSet result = statement.executeQuery();

			Set<Telefono> telefonos = new HashSet<Telefono>();

			while (result.next()) {
				if(id==result.getInt("u.idpersona"))
					telefonos.add(new Telefono(result.getString("numero")));
			}

			return telefonos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}