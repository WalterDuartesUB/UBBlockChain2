package examples.documentopdf.action;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import examples.documentopdf.action.MainController.CommandIDs;

public class MainView{
	private MainController controller;
	private JFrame window;
	
	public MainView() {
		initWindow();
		initComponents();
	}
	
	private void initComponents() {		
		JMenuBar menubar = new JMenuBar();
		
		menubar.add( this.initFileMenu() );
		
		this.getWindow().setJMenuBar(menubar);
	}

	private JMenu initFileMenu() {
		JMenu menu = new JMenu("File");
			
		menu.add( this.initUploadFileMenuItem() );
		menu.add( this.initFindFileMenuItem() );
		
		return menu;
	}

	private JMenuItem initUploadFileMenuItem() {
		JMenuItem menuItem = new JMenuItem("Upload a File");
		
		menuItem.addActionListener( this::onClickUploadFile);
				
		return menuItem;
	}

	public void onClickUploadFile(ActionEvent e) {
		this.getController().execute( CommandIDs.UPLOAD_FILE );		
	}

	private JMenuItem initFindFileMenuItem() {
		JMenuItem menuItem = new JMenuItem("Find a File int the block chain");
		
		menuItem.addActionListener( this::onClickFindFile);
				
		return menuItem;
	}
	
	public void onClickFindFile(ActionEvent e) {
		this.getController().execute( CommandIDs.FIND_FILE_IN_BLOCK_CHAIN );
	}
	
	private void initWindow() {
		this.setWindow( new JFrame() );
		this.getWindow().setSize(600, 600);
		this.getWindow().setResizable(false);
		this.getWindow().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}

	public void show() {
		this.getWindow().setVisible(true);		
	}

	public MainController getController() {
		return controller;
	}

	public void setController(MainController controller) {
		this.controller = controller;
	}

	private JFrame getWindow() {
		return window;
	}

	private void setWindow(JFrame window) {
		this.window = window;
	}

}
