package documentopdf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import interfaces.IHasheableData;

public class DocumentoPDFHasheable extends DocumentoPDFRecuperable implements IHasheableData {
	
	public DocumentoPDFHasheable(String pathDocumento) {
		super(pathDocumento);
	}
	
	@Override
	public byte[] toBytes() throws IOException {
		return Files.readAllBytes(Paths.get(this.getPathDocumento()));
	}	
}