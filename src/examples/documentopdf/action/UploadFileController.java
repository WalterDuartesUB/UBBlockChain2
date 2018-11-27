package examples.documentopdf.action;

import blockchain.IBlockChain;
import examples.documentopdf.DocumentoPDF;
import examples.documentopdf.IDocumentoPDF;

public class UploadFileController {
	private IBlockChain<DocumentoPDF, IDocumentoPDF> blockChain;
	private UploadFileView view;
	
	public UploadFileController(IBlockChain<DocumentoPDF, IDocumentoPDF> blockChain) {
		this.setBlockChain(blockChain);
	}
	
	public IBlockChain<DocumentoPDF, IDocumentoPDF> getBlockChain() {
		return blockChain;
	}
	
	public void setBlockChain(IBlockChain<DocumentoPDF, IDocumentoPDF> blockChain) {
		this.blockChain = blockChain;
	}

	public UploadFileView getView() {
		return view;
	}

	public void setView(UploadFileView view) {
		this.view = view;
	}

	public void selectFile() {
		this.getView().selectFile();		
	}

	public void uploadFile(String filePath) {
		this.getBlockChain().add( new DocumentoPDF(filePath));		
	}

}
