package main;


import modelo.Envoltorio;
import modelo.Persona;
import modelo.Telefono;
import proxys.PersonaProxy;

public class Main {
	
	//Clase cliente 
	public static void main(String args[]) {
		Envoltorio dao = new PersonaProxy();
		/*Set<Telefono> telefonos = new HashSet<Telefono>();
		telefonos.add(new Telefono("291456123"));
		telefonos.add(new Telefono("2920456123"));
		telefonos.add(new Telefono("289456123"));
		Persona persona= new Persona(1,"Alexis",telefonos);
		dao.agregarPersona(persona);*/
		
		Persona p = dao.personaPorId(1);
		System.out.println(p.nombre());
		for (Telefono telefono : p.telefonos()) {
			System.out.println(telefono);
		}
	}
}
