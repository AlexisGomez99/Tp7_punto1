package modelo;

import java.util.Set;

public interface Server {
	Persona personaPorId(int id);

	Set<Telefono> obtenerTelefono(int id);
}
