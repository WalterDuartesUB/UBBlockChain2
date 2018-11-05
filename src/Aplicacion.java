import java.util.Collection;
import java.util.LinkedList;

import org.json.simple.parser.ParseException;

import blockchain.BlockChain;
import blockchain.IBlockChain;
import cipher.BidiriectionalCipherAES;
import documentopdf.DocumentoPDFHasheable;
import documentopdf.IDocumentoPDF;
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
		IBlockChain<DocumentoPDFHasheable, IDocumentoPDF> blockChain = crearBlockChain();

		// Agrego unos bloques a la cadena
		agregarBloques(blockChain);

		// Muestro lo que cargue anteriormente
		mostrarBloques(blockChain);
	}

	private IBlockChain<DocumentoPDFHasheable, IDocumentoPDF> crearBlockChain() {
		BlockChain<DocumentoPDFHasheable, IDocumentoPDF> blockChain = new BlockChain<DocumentoPDFHasheable, IDocumentoPDF>(new DocumentoPDFFactory());

		blockChain.setGeneradorHash(new HashGeneratorMD5<DocumentoPDFHasheable>());
		blockChain.setTimestampingProvider(new TimestampProviderURL<DocumentoPDFHasheable>());
		blockChain.setDataCipher(new BidiriectionalCipherAES());

		return blockChain;
	}

	private void mostrarBloques(IBlockChain<DocumentoPDFHasheable, IDocumentoPDF> blockChain) throws ParseException {

		Collection<IBlockData<IDocumentoPDF>> bloques = new LinkedList<IBlockData<IDocumentoPDF>>();

		// Pido todos los bloques a la blockchain
		blockChain.getAll(bloques);

		// Imprimo los que traje de la blockchain
		for (IBlockData<IDocumentoPDF> bloque : bloques)
			mostrarBloque(bloque);
	}

	private void agregarBloques(IBlockChain<DocumentoPDFHasheable, IDocumentoPDF> blockChain) {
		blockChain.add(new DocumentoPDFHasheable("C:/resumen.pdf"));
		blockChain.add(new DocumentoPDFHasheable("C:/salida.rtf"));
	}

	private void mostrarBloque(IBlockData<IDocumentoPDF> bloque) {
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
