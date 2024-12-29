package srcmodul13;

public class Mahasiswa {
    private String nama;
    private String nim;
    private String nilai;

    public Mahasiswa(String nama, String nim, String nilai) {
        this.nama = nama;
        this.nim = nim;
        this.nilai = nilai;
    }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getNim() { return nim; }
    public void setNim(String nim) { this.nim = nim; }
    public String getNilai() { return nilai; }
    public void setNilai(String nilai) { this.nilai = nilai; }
}