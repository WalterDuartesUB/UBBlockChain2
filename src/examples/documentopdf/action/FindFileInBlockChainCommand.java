package examples.documentopdf.action;

import java.util.Collection;
import java.util.LinkedList;

import blockchain.IBlockChain;
import examples.documentopdf.DocumentoPDF;
import examples.documentopdf.IDocumentoPDF;
import interfaces.block.IBlockData;

public class FindFileInBlockChainCommand implements ICommand {

	private IBlockChain<DocumentoPDF, IDocumentoPDF> blockChain;
	public FindFileInBlockChainCommand(IBlockChain<DocumentoPDF, IDocumentoPDF> blockChain) {
		this.setBlockChain(blockChain);
	}

	@Override
	public void execute() {
		Collection<IBlockData<IDocumentoPDF>> bloques = new LinkedList<IBlockData<IDocumentoPDF>>();
		this.getBlockChain().getAll(bloques);
		
		for( IBlockData<IDocumentoPDF> bloque : bloques )
			System.out.println( bloque );
	}

	public IBlockChain<DocumentoPDF, IDocumentoPDF> getBlockChain() {
		return blockChain;
	}

	public void setBlockChain(IBlockChain<DocumentoPDF, IDocumentoPDF> blockChain) {
		this.blockChain = blockChain;
	}

}
