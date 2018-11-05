import java.util.Collection;
import java.util.LinkedList;

import org.json.simple.parser.ParseException;

import blockchain.BlockChain;
import blockchain.IBlockChain;
import cipher.BidiriectionalCipherAES;
import documentopdf.DocumentoPDF;
import documentopdf.DocumentoPDFFactory;
import hashgenerator.HashGeneratorMD5;
import interfaces.IBlockData;
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

		Collection<IBlockData<DocumentoPDF>> bloques = new LinkedList<IBlockData<DocumentoPDF>>();

		// Pido todos los bloques a la blockchain
		blockChain.getAll(bloques);

		// Imprimo los que traje de la blockchain
		for (IBlockData<DocumentoPDF> bloque : bloques)
			mostrarBloque(bloque);
	}

	private void agregarBloques(IBlockChain<DocumentoPDF> blockChain) {
		blockChain.add(new DocumentoPDF("C:/resumen.pdf"));
		blockChain.add(new DocumentoPDF("C:/salida.rtf"));
	}

	private void mostrarBloque(IBlockData<DocumentoPDF> bloque) {
		System.out.print(bloque.previousHash());
		System.out.print(", ");
		System.out.print(bloque.blockHash());
		System.out.print(", ");
		System.out.print(bloque.timestamp());
		System.out.print(", ");
		System.out.print(bloque.dataHash());
		System.out.print(", ");
		System.out.println(bloque.data().getPathDocumento());
	}
}
