package examples.documentopdf.action;

import java.util.HashMap;
import java.util.Map;

import blockchain.IBlockChain;
import examples.documentopdf.DocumentoPDF;
import examples.documentopdf.IDocumentoPDF;

public class MainController{
	public enum CommandIDs{UPLOAD_FILE, FIND_FILE_IN_BLOCK_CHAIN};
	
	private MainView view;	
	private Map<CommandIDs, ICommand> commands;
	private IBlockChain<DocumentoPDF, IDocumentoPDF> blockChain;
	
	public MainController(IBlockChain<DocumentoPDF, IDocumentoPDF> blockChain) {
		this.setBlockChain(blockChain);		
		initCommands();
	}

	private void initCommands() {
		this.setCommands( new HashMap<CommandIDs, ICommand>() );
		
		this.getCommands().put( CommandIDs.UPLOAD_FILE , new UploadFileCommand( this.getBlockChain() ) );
		this.getCommands().put( CommandIDs.FIND_FILE_IN_BLOCK_CHAIN , new FindFileInBlockChainCommand( this.getBlockChain() ) );
	}

	public void showView() {
		this.getView().show();
	}

	public MainView getView() {
		return view;
	}

	public void setView(MainView view) {
		this.view = view;
	}

	public void execute(CommandIDs commandId) {		
		this.getCommands().get(commandId).execute();
	}

	private Map<CommandIDs, ICommand> getCommands() {
		return commands;
	}

	private void setCommands(Map<CommandIDs, ICommand> commands) {
		this.commands = commands;
	}

	public IBlockChain<DocumentoPDF, IDocumentoPDF> getBlockChain() {
		return blockChain;
	}

	public void setBlockChain(IBlockChain<DocumentoPDF, IDocumentoPDF> blockChain) {
		this.blockChain = blockChain;
	}

}
