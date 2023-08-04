import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class pruebamysql {
    private JTextField codigo;
    private JTextField cedula;
    private JTextField nombre;
    private JTextField fechaN;
    private JComboBox signo;
    private JButton buscarPorCodigoButton;
    private JButton buscarPorNombreButton;
    private JButton buscarPorSignoButton;
    private JButton borrarElPresenteRegistroButton;
    private JButton actualizarElPresenteRegistroButton;
    private JButton ingresarElPresenteRegistroButton;
    private JButton limpiarElFORMULARIOButton;
    private JPanel marco;
    static final String DB_URL = "jdbc:mysql://localhost/POO"; // esta es la conexion con la base de datos
    static final String USER = "root"; //usuario
    static final String PASS = "root_bas3"; // contraseña
    static final String QUERY = "select * from personas";


    public pruebamysql(){
        buscarPorCodigoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sig=codigo.getText();
                try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);){
                    String SQL_Query_select = "SELECT * FROM personas WHERE codigo like ?";
                    try(PreparedStatement pstm = conn.prepareStatement(SQL_Query_select)){
                        pstm.setString(1,sig);
                        try (ResultSet rs = pstm.executeQuery()){
                            if (rs.next()){

                                int cedulaa = rs.getInt("cedula");
                                String nombree = rs.getString("nombre");
                                String fechaaN= rs.getString("fecha");

                                cedula.setText(String.valueOf(cedula));
                                nombre.setText(nombree);
                                fechaN.setText(fechaaN);
                                String sign=signo.getSelectedItem().toString();

                                JOptionPane.showMessageDialog(marco,"Estudiante encontrado");
                            } else {
                                JOptionPane.showMessageDialog(marco,"No existe o no se encontro");
                                codigo.setText(" ");
                                cedula.setText(" ");
                                nombre.setText(" ");
                                fechaN.setText(" ");
                                signo.setSelectedItem("...");
                            }
                        }
                    }
                } catch (SQLException ex){
                    ex.printStackTrace();
                }

            }
        });

        buscarPorNombreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sig=nombre.getText();
                try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);){
                    String SQL_Query_select = "SELECT * FROM personas WHERE nombre like ?";
                    try(PreparedStatement pstm = conn.prepareStatement(SQL_Query_select)){
                        pstm.setString(1,sig);
                        try (ResultSet rs = pstm.executeQuery()){
                            if (rs.next()){
                                int codigoo= rs.getInt("codigo");
                                int cedulaa = rs.getInt("cedula");
                                String fechaaN= rs.getString("fecha");
                                codigo.setText(String.valueOf(codigo));
                                cedula.setText(String.valueOf(cedula));
                                fechaN.setText(fechaaN);

                                JOptionPane.showMessageDialog(marco,"Estudiante encontrado");
                            } else {
                                JOptionPane.showMessageDialog(marco,"No existe o no se encontro");
                                codigo.setText(" ");
                                cedula.setText(" ");
                                nombre.setText(" ");
                                fechaN.setText(" ");
                                signo.setSelectedItem("...");
                            }
                        }
                    }
                } catch (SQLException ex){
                    ex.printStackTrace();
                }

            }
        });

        buscarPorSignoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sig=signo.getSelectedItem().toString();
                try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);){
                    String SQL_Query_select = "SELECT * FROM personas WHERE signo like ?";
                    try(PreparedStatement pstm = conn.prepareStatement(SQL_Query_select)){
                        pstm.setString(1,sig);
                        try (ResultSet rs = pstm.executeQuery()){
                            if (rs.next()){
                                int codigoo= rs.getInt("codigo");
                                int cedulaa = rs.getInt("cedula");
                                String nombree = rs.getString("nombre");
                                String fechaaN= rs.getString("fecha");
                                codigo.setText(String.valueOf(codigo));
                                cedula.setText(String.valueOf(cedula));
                                nombre.setText(nombree);
                                fechaN.setText(fechaaN);

                                JOptionPane.showMessageDialog(marco,"Estudiante encontrado");
                            } else {
                                JOptionPane.showMessageDialog(marco,"No existe o no se encontro");
                                codigo.setText(" ");
                                cedula.setText(" ");
                                nombre.setText(" ");
                                fechaN.setText(" ");
                                signo.setSelectedItem("...");
                            }
                        }
                    }
                } catch (SQLException ex){
                    ex.printStackTrace();
                }

            }
        });

        borrarElPresenteRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);){
                    String query_delete = "delete from personas where codigo =?";

                    try(PreparedStatement pstmt = conn.prepareStatement(query_delete)){
                        pstmt.setInt(1, Integer.parseInt(codigo.getText()));
                        int filasE=pstmt.executeUpdate();
                        if (filasE>0){
                            JOptionPane.showMessageDialog(marco,"Registro eliminado");
                            //mostrar.setText("Estudiante eliminado");
                        }else{
                            //mostrar.setText("No se encontro ninguno estudiante con ese ID!!! ");
                            JOptionPane.showMessageDialog(marco,"No se encontro el Id!!!!!");
                        }
                    }

                }catch (SQLException k){
                    k.printStackTrace();
                }
                codigo.setText("");
                cedula.setText("");
                nombre.setText("");
                fechaN.setText("");
                signo.getSelectedItem().toString();
            }
        });


        actualizarElPresenteRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);){
                    String query_UPDATE = "update personas set cedula=?,nombre=?,fecha=?,signo=? where codigo=?";

                    try(PreparedStatement pstmt = conn.prepareStatement(query_UPDATE)){

                        pstmt.setInt(1, Integer.parseInt(String.valueOf(cedula.getText())));
                        pstmt.setString(2, nombre.getText());
                        pstmt.setString(3,fechaN.getText());
                        pstmt.setString(4,signo.getSelectedItem().toString());
                        pstmt.setInt(5, Integer.parseInt(codigo.getText()));
                        int filasE=pstmt.executeUpdate();
                        if (filasE>0){

                            JOptionPane.showMessageDialog(marco,"Informacion actualizada");
                        }else{
                            JOptionPane.showMessageDialog(marco,"No se pudo actualizar la informacion ID no encontrada");
                        }
                    }

                }catch (SQLException k){
                    k.printStackTrace();
                }




            }
        });
        ingresarElPresenteRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);){
                    String query_insert = "insert into personas (codigo,cedula,nombre,fecha,signo) values (?,?,?,?,?)";

                    try(PreparedStatement pstmt = conn.prepareStatement(query_insert)){
                        pstmt.setInt(1, Integer.parseInt(codigo.getText()));
                        pstmt.setInt(2,Integer.parseInt(cedula.getText()));
                        pstmt.setString(3,nombre.getText());
                        pstmt.setString(4, fechaN.getText());
                        pstmt.setString(5,signo.getSelectedItem().toString());
                        int filasI=pstmt.executeUpdate();
                        if (filasI>0){
                            JOptionPane.showMessageDialog(marco,"Persona ingresada correctamente");
                            //mostrar.setText("Estudiante insertado");
                        }else{
                            //mostrar.setText("No se pudo insertart el usuario!!! ");
                            JOptionPane.showMessageDialog(marco,"Verifique la id que no se repita!!!");
                        }
                    }

                }catch (SQLException k){
                    k.printStackTrace();
                }


            }
        });

        limpiarElFORMULARIOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codigo.setText("");
                cedula.setText("");
                nombre.setText("");
                fechaN.setText("");
            }
        });
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("pruebamysql");
        frame.setContentPane(new pruebamysql().marco);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
