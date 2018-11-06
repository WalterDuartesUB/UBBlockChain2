package documentopdf;

public class DocumentoPDFRecuperable implements IDocumentoPDF{
	private String pathDocumento;

	public DocumentoPDFRecuperable(String pathDocumento) {
		this.setPathDocumento(pathDocumento);
	}

	@Override
	public String getPathDocumento() {
		return pathDocumento;
	}

	public void setPathDocumento(String pathDocumento) {
		this.pathDocumento = pathDocumento;
	}
}
