import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Tienda extends JFrame {
    Connection con;
    PreparedStatement ps;

    Statement st;

    ResultSet rs;
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
    private JPanel panel;

    public Tienda() {

        // Boton para buscar
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conectar();
                try {
                    ps=con.prepareStatement("SELECT * FROM PRODUCTO WHERE COD_PRO = ?");
                    ps.setString(1,codigo.getText());
                    //Me va a retornar los valores de la consulta
                    rs=ps.executeQuery();
                    if (rs.next()){
                    nom_pro.setText(rs.getString(2));
                    unidades.setText(rs.getString(3));
                    fecha.setText(rs.getString(4));
                    nom_provee.setText(rs.getString(5));
                    precio.setText(rs.getString(6));
                        JOptionPane.showMessageDialog(null,"¡ Prodcuto encontrado !");
                    }else {
                        JOptionPane.showMessageDialog(null,"No se Encuentra Dentro de la Base");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        // Boton para crear
        crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             conectar();
                try {
                    ps=con.prepareStatement("INSERT INTO PRODUCTO VALUES(?,?,?,?,?,?)");
                    ps.setString(1,codigo.getText());
                    ps.setString(2,nom_pro.getText());
                    ps.setInt(3, Integer.parseInt(unidades.getText()));//Conversiones por que es un tipo numerico
                    ps.setInt(4, Integer.parseInt(fecha.getText()));
                    ps.setString(5,nom_provee.getText());
                    ps.setFloat(6, Float.parseFloat(precio.getText()));
                    if (ps.executeUpdate()>0){
                        JOptionPane.showMessageDialog(null,"¡ Se ah Creado Correctamente !");
                    }else{
                        JOptionPane.showMessageDialog(null,"ERROR al momento de Crearla");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //Boton para Actualizar
        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tienda t =  new Tienda();
                t.conectar();
                try {
                    ps = t.con.prepareStatement("UPDATE PRODUCTO SET  NOM_PROD=?,UNI_VENDIDA=?,FECHA_CADUCE=?,NOMBRE_PROVEEDOR=?,PRECIO=? WHERE COD_PRO =?");
                    ps.setString(1,nom_pro.getText());
                    ps.setInt(2, Integer.parseInt(unidades.getText()));//Conversiones por que es un tipo numerico
                    ps.setInt(3, Integer.parseInt(fecha.getText()));
                    ps.setString(4,nom_provee.getText());
                    ps.setFloat(5, Float.parseFloat(precio.getText()));
                    ps.setString(6,codigo.getText());
                    if ( ps.executeUpdate() > 0){
                        JOptionPane.showMessageDialog(null,"¡ Se ah Actualizado de manera Existosa !");
                    }else{
                        JOptionPane.showMessageDialog(null,"ERROR en la Actualizacion");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) {
        Tienda t = new Tienda();
        t.setContentPane(new Tienda().panel);
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t.setVisible(true);
        t.pack();
    }

    public void conectar(){
        try{
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/TIENDAS","root","12345");
            System.out.println("Conectado");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
