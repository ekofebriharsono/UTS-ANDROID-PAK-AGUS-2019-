package dev.tito.risetpakagus;

public class Data {
    private String kode, nama, unit, hargaJual, hargaBeli, diskon;

    public Data() {
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(String hargaJual) {
        this.hargaJual = hargaJual;
    }

    public String getHargaBeli() {
        return hargaBeli;
    }

    public void setHargaBeli(String hargaBeli) {
        this.hargaBeli = hargaBeli;
    }

    public String getDiskon() {
        return diskon;
    }

    public void setDiskon(String diskon) {
        this.diskon = diskon;
    }

    public Data(String kode, String nama, String unit, String hargaJual, String hargaBeli, String diskon) {
        this.kode = kode;
        this.nama = nama;
        this.unit = unit;
        this.hargaJual = hargaJual;
        this.hargaBeli = hargaBeli;
        this.diskon = diskon;
    }
}