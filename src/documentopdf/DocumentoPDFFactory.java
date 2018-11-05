package documentopdf;
import org.json.simple.JSONObject;

import interfaces.IDataFactory;

public class DocumentoPDFFactory implements IDataFactory<DocumentoPDF>{	
	public DocumentoPDF createData(JSONObject jsonObject) {
		return new DocumentoPDF( jsonObject.get("path_documento").toString() );
	}

	@SuppressWarnings("unchecked")
	public void toJSON(DocumentoPDF data, JSONObject jsonObject) {
		jsonObject.put("path_documento", data.getPathDocumento() );		
	}

}
