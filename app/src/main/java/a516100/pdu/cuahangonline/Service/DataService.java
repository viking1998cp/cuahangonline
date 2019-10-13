package a516100.pdu.cuahangonline.Service;

import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.List;

import a516100.pdu.cuahangonline.model.Loaisp;
import a516100.pdu.cuahangonline.model.Quangcao;
import a516100.pdu.cuahangonline.model.Sanpham;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("getLoaisanpham.php")
    Call<List<Loaisp>> GetdataMenu();
    @GET("getSanphamNew.php")
    Call<List<Sanpham>> GetdataSanPhamNew();
    @GET("getQuangcao.php")
    Call<List<Quangcao>> GetdataQuangcao();
    @FormUrlEncoded
    @POST("getSanpham.php")
    Call<List<Sanpham>> GetdataDienthoai(@Field("page") int page, @Field("idsp") int idsp);
    @FormUrlEncoded
    @POST("themkhachhang.php")
    Call<String> Themkhachhang(@Field("tenkhachhang") String tenkhachhang,
                               @Field("sodienthoai") String sodienthoai,
                               @Field("email") String email);
    @FormUrlEncoded
    @POST("themchitietdonhang.php")
    Call<String> Themchitietdonhang (@Field("json")JSONArray jsonArray);
}
