package documentopdf;
import org.json.simple.JSONObject;

import interfaces.data.IDataFactory;

public class DocumentoPDFFactory implements IDataFactory<DocumentoPDFHasheable, IDocumentoPDF>{
	@Override
	public IDocumentoPDF createData(JSONObject jsonObject) {
		return new DocumentoPDFRecuperable( jsonObject.get("path_documento").toString() );
	}

	@SuppressWarnings("unchecked")
	@Override
	public void toJSON(DocumentoPDFHasheable data, JSONObject jsonObject) {
		jsonObject.put("path_documento", data.getPathDocumento() );		
	}

}
