package a516100.pdu.cuahangonline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Giohang {
    @SerializedName("idsp")
    @Expose
    private int idsp;
    @SerializedName("tensp")
    @Expose
    private String tensp;
    @SerializedName("giasp")
    @Expose
    private long giasp;
    @SerializedName("hinhanhsp")
    @Expose
    private String hinhanhsp;
    @SerializedName("soluongsp")
    @Expose
    private int soluongsp;

    public Giohang(int idsp, String tensp, long giasp, String hinhanhsp, int soluongsp) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.hinhanhsp = hinhanhsp;
        this.soluongsp = soluongsp;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public long getGiasp() {
        return giasp;
    }

    public void setGiasp(long giasp) {
        this.giasp = giasp;
    }

    public String getHinhanhsp() {
        return hinhanhsp;
    }

    public void setHinhanhsp(String hinhanhsp) {
        this.hinhanhsp = hinhanhsp;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }
}
