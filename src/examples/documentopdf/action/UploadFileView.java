package examples.documentopdf.action;

import java.io.File;

import javax.swing.JFileChooser;

public class UploadFileView {

	private UploadFileController controller;
	
	public void selectFile() {
		// Presento un dialogo para que el usuario elija el archivo a subir a la block
		// chain
		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

		int result = fileChooser.showOpenDialog(null);

		if (result != JFileChooser.APPROVE_OPTION)
			throw new RuntimeException("Operación cancelada por el usuario");

		this.getController().uploadFile( fileChooser.getSelectedFile().getAbsolutePath() );		
	}


	public UploadFileController getController() {
		return controller;
	}


	public void setController(UploadFileController controller) {
		this.controller = controller;
	}
}
