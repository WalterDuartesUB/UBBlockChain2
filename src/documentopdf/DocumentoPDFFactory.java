package documentopdf;
import org.json.simple.JSONObject;

import interfaces.IDataFactory;

public class DocumentoPDFFactory implements IDataFactory<DocumentoPDFHasheable>{	
	public DocumentoPDFHasheable createData(JSONObject jsonObject) {
		return new DocumentoPDFHasheable( jsonObject.get("path_documento").toString() );
	}

	@SuppressWarnings("unchecked")
	public void toJSON(DocumentoPDFHasheable data, JSONObject jsonObject) {
		jsonObject.put("path_documento", data.getPathDocumento() );		
	}

}
