package documentopdf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import interfaces.IHasheableData;

public class DocumentoPDF implements IHasheableData {

	private String pathDocumento;

	public DocumentoPDF(String pathDocumento) {
		this.setPathDocumento(pathDocumento);
	}

	public String getPathDocumento() {
		return pathDocumento;
	}

	public void setPathDocumento(String pathDocumento) {
		this.pathDocumento = pathDocumento;
	}

	@Override
	public String toString() {
		return "DocumentoPDF [pathDocumento=" + pathDocumento + "]";
	}

	@Override
	public byte[] toBytes() throws IOException {
		return Files.readAllBytes(Paths.get(this.getPathDocumento()));
	}
	
}
