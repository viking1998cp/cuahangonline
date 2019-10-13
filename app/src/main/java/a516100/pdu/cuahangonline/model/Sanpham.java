package a516100.pdu.cuahangonline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sanpham implements Serializable {


    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("tensp")
    @Expose
    private String tensp;
    @SerializedName("giasp")
    @Expose
    private int giasp;
    @SerializedName("hinhanhsp")
    @Expose
    private String hinhanhsp;
    @SerializedName("motasp")
    @Expose
    private String motasp;
    @SerializedName("idsanpham")
    @Expose
    private int idsanpham;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public String getHinhanhsp() {
        return hinhanhsp;
    }

    public void setHinhanhsp(String hinhanhsp) {
        this.hinhanhsp = hinhanhsp;
    }

    public String getMotasp() {
        return motasp;
    }

    public void setMotasp(String motasp) {
        this.motasp = motasp;
    }

    public int getIdsanpham() {
        return idsanpham;
    }

    public void setIdsanpham(int idsanpham) {
        this.idsanpham = idsanpham;
    }
}
