package proxys;

import java.util.Set;

import modelo.Envoltorio;
import modelo.Persona;
import modelo.PersonaDao;
import modelo.Telefono;

public class PersonaProxy implements Envoltorio{
	private Envoltorio persona;
	
	//Clase proxy
	
	@Override
	public Persona personaPorId(int id) {
		
		if(persona==null) {
			persona= new PersonaDao();
			return persona.personaPorId(id);
		}
		else {
			return persona.personaPorId(id);
		}
	}

	@Override
	public void agregarPersona(Persona persona) {
		if(this.persona==null) {
			this.persona= new PersonaDao();
			this.persona.agregarPersona(persona);
		}
		else {
			this.persona.agregarPersona(persona);
		}
	}

	@Override
	public Set<Telefono> telefonosPorId(int id) {
		if(this.persona==null) {
			this.persona= new PersonaDao();
			return this.persona.telefonosPorId(id);
		}
		else {
			return this.persona.telefonosPorId(id);
		}
	}
	
	
}
