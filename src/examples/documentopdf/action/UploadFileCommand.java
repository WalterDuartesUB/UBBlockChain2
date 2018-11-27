package examples.documentopdf.action;

import blockchain.IBlockChain;
import examples.documentopdf.DocumentoPDF;
import examples.documentopdf.IDocumentoPDF;

public class UploadFileCommand implements ICommand {

	private IBlockChain<DocumentoPDF, IDocumentoPDF> blockChain;
	public UploadFileCommand(IBlockChain<DocumentoPDF, IDocumentoPDF> blockChain) {
		this.setBlockChain(blockChain);
	}

	@Override
	public void execute() {
		
		//Creo los componentes
		UploadFileView view = new UploadFileView();
		UploadFileController controller = new UploadFileController( this.getBlockChain() );
		
		//Ato los componentes
		controller.setView( view );
		view.setController(controller);
		
		//Muestro la ventana principal
		
		controller.selectFile();
	}

	public IBlockChain<DocumentoPDF, IDocumentoPDF> getBlockChain() {
		return blockChain;
	}

	public void setBlockChain(IBlockChain<DocumentoPDF, IDocumentoPDF> blockChain) {
		this.blockChain = blockChain;
	}

}
