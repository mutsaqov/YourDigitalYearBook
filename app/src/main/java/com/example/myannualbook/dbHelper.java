package com.example.myannualbook;

public class dbHelper {

    private String IDsiswa;
    private String nama_full;
   // private String username;
    private String email;
    private String password;
   // private String alamat;
    private String insta;
    private String whatsapp;
    private String line;

    private String foto_profile;
 //   private String foto_BTS;

    public dbHelper() {

    }

    public dbHelper(String IDsiswa, String nama_full,  String email, String password, String insta, String whatsapp, String line, String foto_profile) {

        this.setIDsiswa(IDsiswa);
        this.setNama_full(nama_full);
   //     this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
     //   this.setAlamat(alamat);
        this.setInsta(insta);
        this.setWhatsapp(whatsapp);
        this.setLine(line);
        this.setFoto_profile(foto_profile);
     //   this.setFoto_BTS(foto_BTS);
    }

    public String getIDsiswa() {
        return IDsiswa;
    }

    public void setIDsiswa(String IDsiswa) {
        this.IDsiswa = IDsiswa;
    }

    public String getNama_full() {
        return nama_full;
    }

    public void setNama_full(String nama_full) {
        this.nama_full = nama_full;
    }

  //  public String getUsername() {
    //    return username;
  //  }

  //  public void setUsername(String username) {
  //      this.username = username;
  //  }

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

   // public String getAlamat() {
   //     return alamat;
  //  }

  //  public void setAlamat(String alamat) {
 //       this.alamat = alamat;
  //  }

    public String getInsta() {
        return insta;
    }

    public void setInsta(String insta) {
        this.insta = insta;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getFoto_profile() {
        return foto_profile;
    }

    public void setFoto_profile(String foto_profile) {
        this.foto_profile = foto_profile;
    }

  /*  public String getFoto_BTS() {
        return foto_BTS;
    }

    public void setFoto_BTS(String foto_BTS) {
        this.foto_BTS = foto_BTS;
    } */
}
