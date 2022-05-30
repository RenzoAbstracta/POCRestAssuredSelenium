package DB;

import Entities.Pay;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtilsDataBase {

    //singleton
    private static UtilsDataBase instancia = null;

    private UtilsDataBase() {
    }

    public static UtilsDataBase getInstancia() {
        if (instancia == null) {
            instancia = new UtilsDataBase();
        }
        return instancia;
    }


    public Pay getPayingFromDB(int id) throws Exception {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;

        try {
            conexion = Conexion.getConexion();
            consulta = conexion.prepareStatement("SELECT * FROM paying p WHERE p.idpaying = ?");
            consulta.setInt(1, id);

            resultadoConsulta = consulta.executeQuery();

            Pay unPaying = null;
            int idP;
            int amount;

            if (resultadoConsulta.next()) {
                idP = resultadoConsulta.getInt("idpaying");
                amount = resultadoConsulta.getInt("amount");

                unPaying = new Pay(idP, amount);
            }

            return unPaying;
        } catch (Exception ex) {
            throw new Exception("No se pudo encontrar el pago con id " + id + ".");
        } finally {
            Conexion.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
}
