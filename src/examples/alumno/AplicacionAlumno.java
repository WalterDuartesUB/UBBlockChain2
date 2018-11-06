package examples.alumno;

import java.util.Collection;
import java.util.LinkedList;

import blockchain.BlockChain;
import blockchain.IBlockChain;
import cipher.BidiriectionalCipherAES;
import hashgenerator.HashGeneratorMD5;
import interfaces.block.IBlockData;
import tsproviders.TimestampProviderURL;

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
		
		System.out.println("Alumnos(BlockChain):");
		
		//Muestro los alumnos de la block chain
		for( IBlockData<Alumno> alumno : alumnosBC )
			System.out.println( alumno.data() );
		
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
