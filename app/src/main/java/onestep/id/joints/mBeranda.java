package onestep.id.joints;

/**
 * Created by Brian R on 27/02/2018.
 */

public class mBeranda {
    private int id;
    private String destination, id_agenda, nama_agenda, tanggal_berangkat, tanggal_kedatangan, kategori_traveler, kategori_activity;

    public mBeranda() {

    }

    public mBeranda(int id, String destination) {
        this.id = id;
        this.destination = destination;
    }

    public String getId_agenda() {
        return id_agenda;
    }

    public void setId_agenda(String id_agenda) {
        this.id_agenda = id_agenda;
    }

    public String getNama_agenda() {
        return nama_agenda;
    }

    public void setNama_agenda(String nama_agenda) {
        this.nama_agenda = nama_agenda;
    }

    public String getTanggal_berangkat() {
        return tanggal_berangkat;
    }

    public void setTanggal_berangkat(String tanggal_berangkat) {
        this.tanggal_berangkat = tanggal_berangkat;
    }

    public String getTanggal_kedatangan() {
        return tanggal_kedatangan;
    }

    public void setTanggal_kedatangan(String tanggal_kedatangan) {
        this.tanggal_kedatangan = tanggal_kedatangan;
    }

    public String getKategori_traveler() {
        return kategori_traveler;
    }

    public void setKategori_traveler(String kategori_traveler) {
        this.kategori_traveler = kategori_traveler;
    }

    public String getKategori_activity() {
        return kategori_activity;
    }

    public void setKategori_activity(String kategori_activity) {
        this.kategori_activity = kategori_activity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
