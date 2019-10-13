package a516100.pdu.cuahangonline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quangcao {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("hinhanhquangcao")
    @Expose
    private String hinhanhquangcao;
    @SerializedName("idsp")
    @Expose
    private String idsp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHinhanhquangcao() {
        return hinhanhquangcao;
    }

    public void setHinhanhquangcao(String hinhanhquangcao) {
        this.hinhanhquangcao = hinhanhquangcao;
    }

    public String getIdsp() {
        return idsp;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
    }
}
