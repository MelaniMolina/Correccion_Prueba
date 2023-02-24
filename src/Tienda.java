import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Tienda {
    private JTextField fecha;
    private JTextField precio;
    private JTextField nom_provee;
    private JTextField codigo;
    private JTextField nom_pro;
    private JTextField unidades;
    private JButton crear;
    private JButton buscar;
    private JButton actualizar;
    private JButton borrar;

    Connection con;
    public void conectar(){
        try{
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","12345");
            System.out.println("Conectado");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
