import java.util.Collection;
import java.util.LinkedList;

import org.json.simple.parser.ParseException;

import blockchain.BlockChain;
import blockchain.IBlockChain;
import cipher.BidiriectionalCipherAES;
import documentopdf.DocumentoPDF;
import documentopdf.DocumentoPDFFactory;
import hashgenerator.HashGeneratorMD5;
import models.Block;
import tsproviders.TimestampProviderURL;

public class Aplicacion {

	public static void main(String[] args) throws ParseException {
		Aplicacion a = new Aplicacion();

		a.ejecutar();
	}

	public void ejecutar() throws ParseException {
		// Creo una blockChain con los parametros que quiero
		IBlockChain<DocumentoPDF> blockChain = crearBlockChain();

		// Agrego unos bloques a la cadena
		agregarBloques(blockChain);

		// Muestro lo que cargue anteriormente
		mostrarBloques(blockChain);
	}

	private IBlockChain<DocumentoPDF> crearBlockChain() {
		BlockChain<DocumentoPDF> blockChain = new BlockChain<DocumentoPDF>(new DocumentoPDFFactory());

		blockChain.setGeneradorHash(new HashGeneratorMD5<DocumentoPDF>());
		blockChain.setTimestampingProvider(new TimestampProviderURL<DocumentoPDF>());
		blockChain.setDataCipher(new BidiriectionalCipherAES());

		return blockChain;
	}

	private void mostrarBloques(IBlockChain<DocumentoPDF> blockChain) throws ParseException {

		Collection<Block<DocumentoPDF>> bloques = new LinkedList<Block<DocumentoPDF>>();

		// Pido todos los bloques a la blockchain
		blockChain.getAll(bloques);

		// Imprimo los que traje de la blockchain
		for (Block<DocumentoPDF> bloque : bloques)
			mostrarBloque(bloque);
	}

	private void agregarBloques(IBlockChain<DocumentoPDF> blockChain) {
		blockChain.add(new DocumentoPDF("C:/resumen.pdf"));
		blockChain.add(new DocumentoPDF("C:/salida.rtf"));
	}

	private void mostrarBloque(Block<DocumentoPDF> bloque) {
		System.out.print(bloque.previousHash());
		System.out.print(", ");
		System.out.print(bloque.blockHash());
		System.out.print(", ");
		System.out.print(bloque.getStampedData().timestamp());
		System.out.print(", ");
		System.out.print(bloque.getStampedData().hashedData().hashAsString());
		System.out.print(", ");
		System.out.println(bloque.getStampedData().hashedData().data().getPathDocumento());
	}
}
