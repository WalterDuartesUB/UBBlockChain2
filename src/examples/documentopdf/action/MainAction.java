package examples.documentopdf.action;

import blockchain.BlockChain;
import blockchain.IBlockChain;
import cipher.BidiriectionalCipherAES;
import examples.documentopdf.DocumentoPDF;
import examples.documentopdf.DocumentoPDFFactory;
import examples.documentopdf.IDocumentoPDF;
import hashgenerator.HashGeneratorMD5;
import tsproviders.TimestampProviderURL;

public class MainAction {
	public static void actionPerformed() {
		
		//Creo la block chain
		IBlockChain<DocumentoPDF, IDocumentoPDF> blockChain = crearBlockChain();

		//Creo los componentes
		MainView mv = new MainView();
		MainController mc = new MainController( blockChain );
		
		//Ato los componentes
		mc.setView( mv );
		mv.setController(mc);
		
		//Muestro la ventana principal
		
		mc.showView();
	}
	
	private static IBlockChain<DocumentoPDF, IDocumentoPDF> crearBlockChain() {
		BlockChain<DocumentoPDF, IDocumentoPDF> blockChain = new BlockChain<DocumentoPDF, IDocumentoPDF>(new DocumentoPDFFactory());

		blockChain.setGeneradorHash(new HashGeneratorMD5());
		blockChain.setTimestampingProvider(new TimestampProviderURL());
		blockChain.setDataCipher(new BidiriectionalCipherAES());

		return blockChain;
	}	
}
