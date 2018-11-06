package examples.alumno;

import org.json.simple.JSONObject;

import interfaces.data.IDataFactory;

public class AlumnoFactory implements  IDataFactory<Alumno, Alumno> {

	@Override
	public Alumno createData(JSONObject json) {
		return new Alumno(json.get("nombre").toString(), json.get("apellido").toString(), json.get("matricula").toString() );
	}

	@SuppressWarnings("unchecked")
	@Override
	public void toJSON(Alumno alumno, JSONObject json) {
		json.put("nombre", alumno.getNombre());
		json.put("apellido", alumno.getApellido());
		json.put("matricula", alumno.getMatricula());		
		
	}

}
