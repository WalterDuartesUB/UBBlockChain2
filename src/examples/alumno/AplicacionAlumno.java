package examples.alumno;

import java.util.Collection;
import java.util.LinkedList;

import org.json.simple.JSONObject;

import blockchain.BlockChain;
import blockchain.IBlockChain;
import cipher.BidiriectionalCipherAES;
import hashgenerator.HashGeneratorMD5;
import interfaces.block.IBlockData;
import interfaces.data.IDataFactory;
import tsproviders.TimestampProviderURL;

class Persona{
	private String nombre;
	private String apellido;
	
	public Persona(String nombre, String apellido) {
		this.setNombre(nombre);
		this.setApellido(apellido);
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
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + "]";
	}
	
}

public class AplicacionAlumno {
	

	public static void main(String[] args) {
		IBlockChain<Alumno, Alumno> blockChain = createBlockChain();
		Collection<Alumno>			alumnos = new LinkedList<Alumno>();

		//Agrego a la coleccion de datos
		alumnos.add( new Alumno("Walter", "Duartes", "560-3166") );
		alumnos.add( new Alumno("Otro", "Duartes", "560-3167") );
		alumnos.add( new Alumno("Walter", "Otro", "560-3168") );
		
		// Agrego alumnos a la blockchain
		for( Alumno alumno : alumnos )
			blockChain.add(alumno);		
		
		// Recupero los alumnos agregados de la blockchain
		Collection<IBlockData<Alumno>> alumnosBC = new LinkedList<IBlockData<Alumno>>();
		
		blockChain.getAll( alumnosBC );
		
		//Recupero en una clase diferente con una factory propia
		Collection<IBlockData<Persona>> personasBC = new LinkedList<IBlockData<Persona>>();		
		blockChain.getAll( personasBC, new IDataFactory<Alumno, Persona>() {
			@Override
			public Persona createData(JSONObject jsonObject) {
				return new Persona( jsonObject.get("nombre").toString(), jsonObject.get("apellido").toString());
			}

			@Override
			public void toJSON(Alumno data, JSONObject jsonObject) {	
			}
		} );
		
		System.out.println("Alumnos(BlockChain):");
		
		//Muestro los alumnos de la block chain
		for( IBlockData<Alumno> alumno : alumnosBC )
			System.out.println( alumno.data() );
		
		for( IBlockData<Persona> persona : personasBC )
			System.out.println( persona.data() );
		
		System.out.println("Alumnos(Original):");
		//Muestro los alumnos de la coleccion
		for( Alumno alumno : alumnos )
			System.out.println(alumno);
	}

	private static BlockChain<Alumno, Alumno> createBlockChain() {
		BlockChain<Alumno, Alumno> blockChain = new BlockChain<Alumno, Alumno>(new AlumnoFactory());
		
		blockChain.setGeneradorHash(new HashGeneratorMD5());
		blockChain.setTimestampingProvider(new TimestampProviderURL());
		blockChain.setDataCipher( new BidiriectionalCipherAES() );
		return blockChain;
	}

}
