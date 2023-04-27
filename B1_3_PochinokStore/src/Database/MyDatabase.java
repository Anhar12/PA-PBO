/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Project.Main;
import Project.Pesanan;
import Project.VariasiTopUp;
import UI.Admin;
import UI.Customer;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author ACER
 */
public class MyDatabase {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB = "jdbc:mysql://localhost:3306/dbtopup";
    
    private static Connection CONN;
    private static Statement statement;
    private static ResultSet result;
    private static PreparedStatement Pstatement;
    
    public static void connection(){
        try{
            Class.forName(JDBC_DRIVER);
            CONN = DriverManager.getConnection(DB, "root", "");
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Gagal Konek Ke Database Bossku");
        }
    }
    
    public static void read_game(){
        connection();
        
        try{            
            statement = (Statement) CONN.createStatement();
            
            result = statement.executeQuery("select * from game order by nama");
            Main.dataGame.clear();
            
            while (result.next()){
                String game = result.getString("nama");
                String mataUang = result.getString("mataUang");
                ArrayList<Integer> varian = new ArrayList<>();
                VariasiTopUp newData = new VariasiTopUp(game, mataUang, varian);
                Main.dataGame.add(newData);
            }
            
            statement.close();
            CONN.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void read_varian(){
        connection();
        
        try{            
            statement = (Statement) CONN.createStatement();
            
            result = statement.executeQuery("select * from varian order by jumlah");
            
            for (int i = 0 ; i < Main.dataGame.size() ;i++){
                Main.dataGame.get(i).action();
            }
            
            while (result.next()){
                int jumlah = result.getInt("jumlah");
                int harga = result.getInt("harga");
                String game = result.getString("game");
                
                Main.dataGame.get(Main.cari_game(game)).tambah_varian(jumlah, harga);
            }
            
            statement.close();
            CONN.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void read_transaksi(){
        connection();
        
        try{            
            statement = (Statement) CONN.createStatement();
            
            result = statement.executeQuery("select * from pesanan order by waktu");
            Main.dataPesanan.clear();
            while (result.next()){
                int jumlah = result.getInt("jumlah");
                int harga = result.getInt("harga");
                String game = result.getString("game");
                String ID = result.getString("id_akun");
                String nickname = result.getString("nickname");
                String waktu = result.getString("waktu");
                String mataUang = Main.dataGame.get(Main.cari_game(game)).getMataUang();
                
                Pesanan newData = new Pesanan(jumlah, harga, game, ID, nickname, waktu, mataUang);
                Main.dataPesanan.add(newData);
            }
            
            statement.close();
            CONN.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void insert_game(String game, String mataUang){
        connection();
        
        try{            
            String query = "insert into game values('"+ game +"', '"+ mataUang +"')";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.executeUpdate();
            Pstatement.close();
            CONN.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void insert_varian(String game, int jumlah, int harga){
        connection();
        
        try{            
            String query = "insert into varian (game, harga, jumlah) values('"+ game +"', '"+ harga +"', '"+ jumlah +"')";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.executeUpdate();
            Pstatement.close();
            CONN.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void insert_transaksi(String game, int jumlah, int harga, String id_akun, String nickname, String waktu){
        connection();
        
        try{            
            String query = "insert into pesanan (jumlah, harga, id_akun, nickname, game, waktu) values"
                            + "('"+ jumlah +"', '"+ harga +"', '"+ id_akun +"', '"+ nickname +"', '"+ game +"', '"+ waktu +"')";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.executeUpdate();
            Pstatement.close();
            CONN.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void delete_game(String game){
        connection();
        
        try{            
            String query = "delete from game where nama = '"+ game +"'";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.executeUpdate();
            Pstatement.close();
            CONN.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void delete_varian(String game, int jumlah){
        connection();
        
        try{            
            String query = "delete from varian where jumlah = '"+ jumlah +"' and game = '"+ game +"'";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.executeUpdate();
            Pstatement.close();
            CONN.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void update_game(String update, String game, String mataUang){
        connection();
        
        try{            
            String query = "update game set nama = '"+ game +"', mataUang = '"+ mataUang +"' where nama = '"+ update +"'";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.executeUpdate();
            Pstatement.close();
            CONN.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void update_varian(int update, String game, int jumlah, int harga){
        connection();
        
        try{            
            String query = "update varian set jumlah = '"+ jumlah +"', harga = '"+ harga +"' where jumlah = '"+ update +"' and game = '"+ game +"'";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.executeUpdate();
            Pstatement.close();
            CONN.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void search(String cari, int form){
        connection();
        
        try{            
            statement = (Statement) CONN.createStatement();
            result = statement.executeQuery("Select * from varian join game on varian.game = game.nama where varian.game like '%"+ cari +"%' order by game");
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("No.");
            model.addColumn("Game");
            model.addColumn("Jumlah");
            model.addColumn("Harga");
            
            int i = 1;
            while (result.next()){
                String game = result.getString("game");
                String mataUang = result.getString("mataUang");
                String jumlah = result.getString("jumlah") + " " + mataUang;
                String harga = "Rp" + result.getString("harga");
                
                model.addRow(new Object[] {(i), game, jumlah, harga});
                i++;
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            
            if (form == 1){
                Admin.TableSearch.setModel(model);
                Admin.TableSearch.getColumnModel().getColumn(0).setPreferredWidth(1);
                Admin.TableSearch.getColumnModel().getColumn(1).setPreferredWidth(150);
                Admin.TableSearch.getColumnModel().getColumn(2).setPreferredWidth(150);
                Admin.TableSearch.getColumnModel().getColumn(3).setPreferredWidth(150);
                TableColumnModel columnModel = Admin.TableSearch.getColumnModel();
                for (int j = 0 ; j < 4 ; j++){
                    columnModel.getColumn(j).setCellRenderer(centerRenderer);
                }
                Admin.TableSearch.setDefaultEditor(Object.class, null);
            } else {
                Customer.TableSearch.setModel(model);
                Customer.TableSearch.getColumnModel().getColumn(0).setPreferredWidth(1);
                Customer.TableSearch.getColumnModel().getColumn(1).setPreferredWidth(150);
                Customer.TableSearch.getColumnModel().getColumn(2).setPreferredWidth(150);
                Customer.TableSearch.getColumnModel().getColumn(3).setPreferredWidth(150);
                TableColumnModel columnModel = Customer.TableSearch.getColumnModel();
                for (int j = 0 ; j < 4 ; j++){
                    columnModel.getColumn(j).setCellRenderer(centerRenderer);
                }
                Customer.TableSearch.setDefaultEditor(Object.class, null);
            }
            
//            Admin.TableSearch.setVisible(true);
            statement.close();
            CONN.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
