package Project;

import Database.MyDatabase;
import UI.Landingpage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    static public ArrayList<VariasiTopUp> dataGame = new ArrayList<>();
    static public ArrayList<Pesanan> dataPesanan = new ArrayList<>();
    
    // keyword final pada variabel
    static public final String USERNAME = "admin";
    static public final String PASSWORD = "admin";
    
    static public int cari_game(String game){
        for (int i = 0 ; i < dataGame.size() ; i++){
            if (dataGame.get(i).getGame().equalsIgnoreCase(game)){
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) throws IOException {
        
        MyDatabase.read_game();
        MyDatabase.read_varian();
        MyDatabase.read_transaksi();
        
        Landingpage form = new Landingpage();
        form.setVisible(true);
    }
    
    
}
