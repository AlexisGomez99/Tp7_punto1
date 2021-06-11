package modelo;

import java.util.Set;

//Sujeto
public interface Envoltorio {
	
	Persona personaPorId(int id);
	
	void agregarPersona(Persona persona);
	
	Set<Telefono> telefonosPorId(int id);

}
