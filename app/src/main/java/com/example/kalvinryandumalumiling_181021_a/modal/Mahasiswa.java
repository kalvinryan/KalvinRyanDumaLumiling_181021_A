package com.example.kalvinryandumalumiling_181021_a.modal;

public class Mahasiswa {
    private int id;
    private String stb;
    private String nama;
    private String alamat;
    private String jkl;
    private String tlp;
    private String tmplahir;
    private String tgllahir;
    private String hoby;

    public Mahasiswa(){

    }

    public Mahasiswa(String stb, String nama, String alamat, String jkl, String tlp, String tmplahir, String tgllahir, String hoby) {
        this.stb = stb;
        this.nama = nama;
        this.alamat = alamat;
        this.jkl = jkl;
        this.tlp = tlp;
        this.tmplahir = tmplahir;
        this.tgllahir = tgllahir;
        this.hoby = hoby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStb() {
        return stb;
    }

    public void setStb(String stb) {
        this.stb = stb;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJkl() {
        return jkl;
    }

    public void setJkl(String jkl) {
        this.jkl = jkl;
    }

    public String getTlp() {
        return tlp;
    }

    public void setTlp(String tlp) {
        this.tlp = tlp;
    }

    public String getTmplahir() {
        return tmplahir;
    }

    public String getTgllahir() {
        return tgllahir;
    }

    public void setTgllahir(String tgllahir) {
        this.tgllahir = tgllahir;
    }

    public void setTmplahir(String tmplahir) {
        this.tmplahir = tmplahir;
    }

    public String getHoby() {
        return hoby;
    }

    public void setHoby(String hoby) {
        this.hoby = hoby;
    }
}
