package Project;

import java.util.ArrayList;

// class final
public final class VariasiTopUp extends Game{
    // deklarasi field
    private ArrayList<ArrayList<Integer>> JenisGame = new ArrayList<>();
    
    // constructor 
    public VariasiTopUp (String Game, String mataUang, ArrayList<Integer> jenis){
        // method super dari parentnya di class Game
        super(Game, mataUang);
        this.Game = Game;
        this.mataUang = mataUang;
        for (int i = 0 ; i < jenis.size() ; i+=2){
            this.JenisGame.add(new ArrayList<>());
            this.JenisGame.get(JenisGame.size()-1).add(jenis.get(i));
            this.JenisGame.get(JenisGame.size()-1).add(jenis.get(i+1));
        }
    }
    
    // method setter getter
    public ArrayList<ArrayList<Integer>> getVarian() {
        return this.JenisGame;
    }
    
    public void set_topUp(int jumlah, int harga, int idx){
        this.JenisGame.get(idx).set(0, jumlah);
        this.JenisGame.get(idx).set(1, harga);
    }
    
    // cari index variasinya
    public int idx_variasi(int cari){
        for (int i = 0 ; i < JenisGame.size() ; i++){
            if (JenisGame.get(i).get(0).equals(cari)){
                return i;
            }
        }
        return -1;
    }
    
    public void tambah_varian(int jumlah, int harga){
        this.JenisGame.add(new ArrayList<>());
        this.JenisGame.get(JenisGame.size()-1).add(jumlah);
        this.JenisGame.get(JenisGame.size()-1).add(harga);
    }
    
    @Override
    public void action(){
        this.JenisGame.clear();
    }
    
//    overloading
    public void action(int jumlah, int idx){
        this.JenisGame.remove(idx);
    }
    
}
