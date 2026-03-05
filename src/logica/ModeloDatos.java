package logica;

import java.util.Collection;
import java.util.HashMap;
import entidades.Estudiantes;

public class ModeloDatos {

	HashMap<String, Estudiantes> estudiantesMap;

	public ModeloDatos() {
		estudiantesMap = new HashMap<>();
	}

	public String registrarEstudiante(Estudiantes est) {

		if (!estudiantesMap.containsKey(est.getDocumento())) {
			estudiantesMap.put(est.getDocumento(), est);
			return "ok";
		}else {
			return "Estudiante ya existe con ese documento";
		}
	}

	public Estudiantes consultaEstudiante(String documento) {

		if (estudiantesMap.containsKey(documento)) {
			return estudiantesMap.get(documento);
		}
		return null;
	}

	public Collection<Estudiantes> obtenerLista(){
		return estudiantesMap.values();
	}

	public String eliminarEstudiante(String documento){

		if(estudiantesMap.containsKey(documento)){
			estudiantesMap.remove(documento);
			return "Estudiante eliminado correctamente";
		}else{
			return "El estudiante no existe";
		}
	}

	public String actualizarEstudiante(Estudiantes est){

		if(estudiantesMap.containsKey(est.getDocumento())){
			estudiantesMap.put(est.getDocumento(), est);
			return "Estudiante actualizado correctamente";
		}else{
			return "El estudiante no existe";
		}
	}
}