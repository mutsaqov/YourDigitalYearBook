package com.example.myannualbook;

public class dbHelper_guru {

    private String IDguru;
    private String nama_guru;
    // private String username;
    private String email;
    private String password;
    private String whatsapp;
    private String mata_pelajaran;

    private String foto_profile;
    //   private String foto_BTS;

    public dbHelper_guru() {

    }

    public dbHelper_guru(String IDguru, String nama_guru, String email, String password, String whatsapp, String mata_pelajaran, String foto_profile) {
        this.setIDguru(IDguru);
        this.setNama_guru(nama_guru);
        this.setEmail(email);
        this.setPassword(password);
        this.setWhatsapp(whatsapp);
        this.setMata_pelajaran(mata_pelajaran);
        this.setFoto_profile(foto_profile);
    }

    public String getIDguru() {
        return IDguru;
    }

    public void setIDguru(String IDguru) {
        this.IDguru = IDguru;
    }

    public String getNama_guru() {
        return nama_guru;
    }

    public void setNama_guru(String nama_guru) {
        this.nama_guru = nama_guru;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getMata_pelajaran() {
        return mata_pelajaran;
    }

    public void setMata_pelajaran(String mata_pelajaran) {
        this.mata_pelajaran = mata_pelajaran;
    }

    public String getFoto_profile() {
        return foto_profile;
    }

    public void setFoto_profile(String foto_profile) {
        this.foto_profile = foto_profile;
    }
}
