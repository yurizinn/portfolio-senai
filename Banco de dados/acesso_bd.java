package acesso_bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Banco {
    private static Connection connection;
   
    public static Connection getConnection(){
        if(connection == null){
            try{
                Class.forName("org.postgresql.Driver");
                String host = "localhost";
                String port = "3000";
                String database = "postgres";
                String user = "postgres";
                String pass = "123";
                String url = "jdbc:postgresql://"+host+":"+port+"/"+database;
                connection = DriverManager.getConnection(url, user, pass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    public static void close(){
        if (connection == null){
            throw new RuntimeException("Sem conexões!");
        }
        else{
            try{
                connection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
 
    public static void salvarTenis (Tenis tenis){
        try{
            Connection con = Banco.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO tenis (marca_id, nome) values(?, ?)");
            ps.setInt(1, usuario.getMarcaId());
            ps.setString(2, usuario.getNome());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void salvarMarca (Marca marca){
        try{
            Connection con = Banco.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO marca (nome) values(?)");
            ps.setString(1, usuario.getNome());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletaTenis(int id){
        try{
            Connection con = Banco.getConnection();
            PreparedStatement ps = con.prepareStatement("Delete FROM tenis WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletaMarca(int id){
        try{
            Connection con = Banco.getConnection();
            PreparedStatement ps = con.prepareStatement("Delete FROM marca WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void atualizaBanco(Usuario usuario){
        deleta(usuario.getId());
        salvar(usuario);
    }

    public static void visualiza_tabela(String tabela, String... atributo){
        try{
            Connection con = Banco.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM "+ tabela);
            ResultSet rs = ps.executeQuery();
            
            String s = "";
            while(rs.next()){
                for(String i : atributo){
                    s = s + " | " +rs.getString(i);
                }
                s = s + "\n";
            }
            System.out.println(s);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
