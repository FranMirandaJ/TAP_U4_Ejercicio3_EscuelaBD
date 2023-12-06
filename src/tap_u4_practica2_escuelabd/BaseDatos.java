package tap_u4_practica2_escuelabd;


import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDatos {

    Connection conexion;
    Statement transaccion;
    ResultSet cursor;

    String cadenaConexion = "jdbc:mysql://btcv1qobpz98ikw44tz4-mysql.services.clever-cloud.com:3306/btcv1qobpz98ikw44tz4?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String usuario = "uuo7m853sumtxraa";
    String pass = "6zcUWfXda8RowuVNgFos";

    public BaseDatos() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(cadenaConexion, usuario, pass);
            transaccion = conexion.createStatement();

        } catch (SQLException e) {

        } catch (ClassNotFoundException e) {

        }
    }

    public boolean insertar(Credencial c) {
        String SQL_Insertar = "INSERT INTO `Credencial`(`NUMCONTROL`, `NOMBREALUMNO`, `CARRERA`, `FECHAEXPEDICION`, `SEMESTRE`)"
                + " VALUES ('%NCTRL%','%NOMBRE%','%CARRERA%','%EXP%','%SEM%');";
        SQL_Insertar = SQL_Insertar.replace("%NCTRL%", c.numControl);
        SQL_Insertar = SQL_Insertar.replace("%NOMBRE%", c.nombreAlumno);
        SQL_Insertar = SQL_Insertar.replace("%CARRERA%", c.carrera);
        SQL_Insertar = SQL_Insertar.replace("%EXP%", c.fechaExpedicion);
        SQL_Insertar = SQL_Insertar.replace("%SEM%", c.semestre.toString());
        

        try {
            transaccion.execute(SQL_Insertar);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public ArrayList<Credencial> mostrarTodos() {
        ArrayList< Credencial> datos = new ArrayList<>();
        String SQL_consulta = "SELECT * FROM `Credencial`";

        try {
            //RESULTSET = variable que maneja las tuplas resultantes
            cursor = transaccion.executeQuery(SQL_consulta);

            if (cursor.next()) {
                do {
                    Credencial c = new Credencial(
                            cursor.getString(1),
                            cursor.getString(2), 
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getInt(5)
                    );
                    datos.add(c);
                } while (cursor.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }

    public Credencial obtenerPorID(String NCTRLaBuscar) {
        String SQL_consulta = "SELECT * FROM `Credencial` WHERE `NUMCONTROL`=" + NCTRLaBuscar;

        try {
            //RESULTSET = variable que maneja las tuplas resultantes
            cursor = transaccion.executeQuery(SQL_consulta);

            if (cursor.next()) {
                Credencial p = new Credencial(
                        cursor.getString(1),
                            cursor.getString(2), 
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getInt(5)
                );
                return p;
            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Credencial("", "", "", "", -1);
    }

    public boolean eliminar(String NCTRLaBuscar) {
        String SQL_eliminar = "DELETE FROM `Credencial` WHERE `NUMCONTROL`=" + NCTRLaBuscar;

        try {
            transaccion.execute(SQL_eliminar);
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }
    
    public boolean actualizar(Credencial p){
        String SQL_Actualizar = "UPDATE `Credencial` SET `NOMBREALUMNO`='%NOMBRE%',`CARRERA`='%CARRERA%',"
                + "`FECHAEXPEDICION`='%EXP%',`SEMESTRE`='%SEM%' WHERE `NUMCONTROL`="+p.numControl;

        SQL_Actualizar = SQL_Actualizar.replace("%NOMBRE%", p.nombreAlumno);
        SQL_Actualizar = SQL_Actualizar.replace("%CARRERA%", p.carrera);
        SQL_Actualizar = SQL_Actualizar.replace("%EXP%", p.fechaExpedicion);
        SQL_Actualizar = SQL_Actualizar.replace("%SEM%", p.semestre.toString());

        try {
            transaccion.executeUpdate(SQL_Actualizar);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

}
