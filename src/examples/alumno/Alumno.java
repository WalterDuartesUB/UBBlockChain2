package examples.alumno;

import java.io.IOException;
import java.util.Arrays;

import interfaces.data.IDataHasheable;
import interfaces.data.IDataRecuperable;

public class Alumno implements IDataHasheable, IDataRecuperable{

	private String nombre;
	private String apellido;
	private String matricula;
	
	public Alumno(String nombre, String apellido, String matricula) {
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setMatricula(matricula);
	}

	@Override
	public byte[] toBytes() throws IOException {
		return this.getMatricula().getBytes();
	}

	public String getMatricula() {
		return matricula;
	}

	private void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getApellido() {
		return apellido;
	}

	private void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		try {
			return "Alumno [toBytes()=" + Arrays.toString(toBytes()) + ", getMatricula()=" + getMatricula()
					+ ", getApellido()=" + getApellido() + ", getNombre()=" + getNombre() + "]";
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}	
}
