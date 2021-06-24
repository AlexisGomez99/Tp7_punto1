package main;

import modelo.Persona;
import modelo.Telefono;
import persistencia.PersonaDao;

public class Main {
	public static void main(String args[]) {
		
		PersonaDao dao = new PersonaDao();
		Persona p = dao.personaPorId(1);
		System.out.println(p.nombre());
		
		//La persona puede tener mas de un telefono.
		for (Telefono telefono : p.telefonos()) {
			System.out.println(telefono);
		}
	}
}