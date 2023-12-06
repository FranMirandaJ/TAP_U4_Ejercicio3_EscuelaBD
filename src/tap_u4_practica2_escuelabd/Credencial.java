
package tap_u4_practica2_escuelabd;

public class Credencial {
    String numControl;
    String nombreAlumno;
    String carrera;
    String fechaExpedicion;
    Integer semestre;

    public Credencial(String numControl, String nombreAlumno, String carrera, String fechaExpedicion, int semestre) {
        this.numControl = numControl;
        this.nombreAlumno = nombreAlumno;
        this.carrera = carrera;
        this.fechaExpedicion = fechaExpedicion;
        this.semestre = semestre;
    }
    
    public String[] toRenglon(){
        String[] renglon = new String[5];
        
        renglon[0] = numControl;
        renglon[1] = nombreAlumno;
        renglon[2] = carrera;
        renglon[3] = fechaExpedicion;
        renglon[4] = semestre.toString();
        
        return renglon;
    }
    
    
}
