package a516100.pdu.cuahangonline.main;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a516100.pdu.cuahangonline.R;
import a516100.pdu.cuahangonline.Service.APIService;
import a516100.pdu.cuahangonline.Service.DataService;
import a516100.pdu.cuahangonline.until.CheckConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongtinKhachhang extends AppCompatActivity {
    EditText editTenkhachhang, editSodienthoai, editEmail;
    Button btnXacnhan, btnQuaylai;
    ImageView indicator;
    LinearLayout lnThongtin_khachhang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_khachhang);
        anhxa();
        btnQuaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(CheckConnection.checkConnect(getApplicationContext())){
            EventButton();
        }else {
            CheckConnection.show_Toast_short("Kiểm tra kết nối",getApplicationContext());
        }
    }

    private void EventButton() {
        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = editTenkhachhang.getText().toString().trim();
                String sodienthoai = editSodienthoai.getText().toString().trim();
                String email = editEmail.getText().toString().trim();
                if(ten.length()>0 && sodienthoai.length()>0 && email.length()>0){
                    String emailPattern = "\\w+@\\w+[.]\\w+";
                    Pattern pattern = Pattern.compile(emailPattern);
                    Matcher matcher = pattern.matcher(email);
                    if(matcher.matches()){
                        final DataService dataService = APIService.getService();
                        retrofit2.Call<String> themkhachhang = dataService.Themkhachhang(ten,sodienthoai,email);
                        Log.d("BBB", "onClick: "+ten+" "+sodienthoai+" "+email );
                        themkhachhang.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String id = (String) response.body();
                                JSONArray jsonArray = new JSONArray();
                                for(int i=0; i<MainActivity.arrayGiohang.size();i++) {
                                    JSONObject jsonObject = new JSONObject();
                                    try {
                                        jsonObject.put("madonhang", id);
                                        jsonObject.put("masanpham", MainActivity.arrayGiohang.get(i).getIdsp());
                                        jsonObject.put("tensanpham", MainActivity.arrayGiohang.get(i).getTensp());
                                        jsonObject.put("giasanpham", MainActivity.arrayGiohang.get(i).getGiasp());
                                        jsonObject.put("soluongsanpham", MainActivity.arrayGiohang.get(i).getSoluongsp());
                                        jsonArray.put(jsonObject);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                    AnimationDrawable animationDrawable = new AnimationDrawable();
                                    animationDrawable = (AnimationDrawable) indicator.getBackground();
                                    indicator.setVisibility(View.VISIBLE);
                                    lnThongtin_khachhang.setVisibility(View.INVISIBLE);
                                    animationDrawable.start();
                                    retrofit2.Call<String> callchitietdonhang =  dataService.Themchitietdonhang(jsonArray);
                                final AnimationDrawable finalAnimationDrawable = animationDrawable;
                                callchitietdonhang.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            String ketqua = response.body();
                                            if(ketqua.equalsIgnoreCase("1")){
                                                MainActivity.arrayGiohang.clear();
                                                CheckConnection.show_Toast_short("Bạn đã thêm đơn hàng thành công",getApplicationContext());
                                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);
                                                CheckConnection.show_Toast_short("Mời bạn tiếp tục mua hàng",getApplicationContext());

                                            }else if(ketqua.equalsIgnoreCase("0")) {
                                                CheckConnection.show_Toast_short("Đơn hàng của bạn đã bị lỗi", getApplicationContext());
                                                lnThongtin_khachhang.setVisibility(View.VISIBLE);
                                                indicator.setVisibility(View.INVISIBLE);
                                                finalAnimationDrawable.stop();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {

                                        }
                                    });
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    }else {
                        CheckConnection.show_Toast_short("Địa chỉ email không hợp lệ",getApplicationContext());
                    }
                }else {
                    CheckConnection.show_Toast_short("Hãy kiểm tra lại dữ liệu", getApplicationContext());
                }
            }
        });
    }

    private void anhxa() {
        editTenkhachhang = (EditText) findViewById(R.id.editTenkhachhang);
        editSodienthoai = (EditText) findViewById(R.id.editSdtKhachhang);
        editEmail = (EditText) findViewById(R.id.editEmailKhachhang);
        btnXacnhan = (Button) findViewById(R.id.btnXacnhan);
        btnQuaylai = (Button) findViewById(R.id.btnQuaylai);
        indicator = (ImageView) findViewById(R.id.loadingIcon);
        lnThongtin_khachhang = (LinearLayout) findViewById(R.id.lnThongtin);
    }
}
