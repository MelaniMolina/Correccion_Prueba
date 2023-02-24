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
    private JComboBox comboBoxanio;
    private JComboBox comboBoxprovee;

    public Tienda() throws SQLException {

        //Cargo de datos a sus resepectivos combo box

        try {
            conectar();
            //Cargar datos años
            st = con.createStatement();;
            rs=st.executeQuery("SELECT*FROM FECHA_CADUCA");
            while (rs.next()){
                comboBoxanio.addItem(rs.getString("ANIO"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        //Cargar nombre proveedores
        try {
            conectar();
            st=con.createStatement();
            rs=st.executeQuery("SELECT*FROM NOMBRES_PROVEEDORES");
            while (rs.next()){
                comboBoxprovee.addItem(rs.getString("NOMBRES_PORVEE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




        // Boton para buscar
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conectar();
                try {
                    ps=con.prepareStatement("SELECT * FROM PRODUCTOS WHERE COD_PRO = ?");
                    ps.setString(1,codigo.getText());
                    //Me va a retornar los valores de la consulta
                    rs=ps.executeQuery();
                    if (rs.next()){
                    nom_pro.setText(rs.getString(2));
                    unidades.setText(rs.getString(3));
                    comboBoxanio.setSelectedItem(rs.getString(4));
                    comboBoxprovee.setSelectedItem(rs.getString(5));
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
                String anio = comboBoxanio.getSelectedItem().toString();
                String nombre = comboBoxprovee.getSelectedItem().toString();
                try {
                    ps=con.prepareStatement("INSERT INTO PRODUCTOS VALUES(?,?,?,?,?,?)");
                    ps.setString(1,codigo.getText());
                    ps.setString(2,nom_pro.getText());
                    ps.setInt(3, Integer.parseInt(unidades.getText()));//Conversiones por que es un tipo numerico
                    ps.setInt(4, Integer.parseInt(anio));
                    ps.setString(5,nombre);
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
                conectar();
                String anio = comboBoxanio.getSelectedItem().toString();
                String nombre = comboBoxprovee.getSelectedItem().toString();
                String id = codigo.getText();
                int result=0;
                try {
                    ps = con.prepareStatement("UPDATE PRODUCTOS SET  NOM_PROD=?,UNI_VENDIDA=?,FECHA_CADUCE=?,NOMBRE_PROVEEDOR=?,PRECIO=? WHERE COD_PRO ="+id);
                    /*ps.setString(1,nom_pro.getText());
                    ps.setInt(2, Integer.parseInt(unidades.getText()));//Conversiones por que es un tipo numerico
                    ps.setInt(3, Integer.parseInt(anio));
                    ps.setString(4,nombre);
                    ps.setFloat(5, Float.parseFloat(precio.getText()));
                    result =ps.executeUpdate();*/
                    if ( result > 0){
                        JOptionPane.showMessageDialog(null,"¡ Se ah Actualizado de manera Existosa !");
                    }else{
                        JOptionPane.showMessageDialog(null,"ERROR en la Actualizacion");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //Boton para Eliminar

        borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conectar();
                try {
                    String id = codigo.getText();
                    int result = 0;
                    ps = con.prepareStatement("DELETE FROM PRODUCTOS WHERE COD_PRO = ?");
                    ps.setString(1, id);
                    result = ps.executeUpdate();
                    if(result > 0) {
                        JOptionPane.showMessageDialog(null,"¡Producto Eliminado!");
                    } else {
                        JOptionPane.showMessageDialog(null,"Producto no Eliminado");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
    }


    public static void main(String[] args) throws SQLException {
        Tienda t = new Tienda();
        t.setContentPane(new Tienda().panel);
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t.setVisible(true);
        t.pack();
    }

    public void conectar(){
        try{
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/TIENDAS_ABARROTES","root","12345");
            System.out.println("Conectado");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
