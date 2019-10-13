package a516100.pdu.cuahangonline.main;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import a516100.pdu.cuahangonline.R;
import a516100.pdu.cuahangonline.Service.APIService;
import a516100.pdu.cuahangonline.Service.DataService;
import a516100.pdu.cuahangonline.adapter.LoaispAdapter;
import a516100.pdu.cuahangonline.adapter.SanphamAdapter;
import a516100.pdu.cuahangonline.model.Giohang;
import a516100.pdu.cuahangonline.model.Loaisp;
import a516100.pdu.cuahangonline.model.Quangcao;
import a516100.pdu.cuahangonline.model.Sanpham;
import a516100.pdu.cuahangonline.until.CheckConnection;
import a516100.pdu.cuahangonline.until.Sever;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbarMain;
    ViewFlipper viewFlipperQuangCao;
    RecyclerView recyclerViewSanPham;
    NavigationView navigationViewMenu;
    DrawerLayout myDrawerMain;
    ListView lvMenu;
    ArrayList<Loaisp> arrayLoaisp;
    LoaispAdapter loaispAdapter;
    ArrayList<Quangcao> arrayQuangcao;
    LinearLayout linearLayoutMain;
    SanphamAdapter sanphamAdapter;
    ArrayList<Sanpham> arraySanpham;
    ImageView indicator_loadding;
    TextView tv_error;
    DataService dataService ;
    AnimationDrawable animationDrawable;
    public static ArrayList<Giohang> arrayGiohang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        initPermission();
        animationDrawable = new AnimationDrawable();
        animationDrawable = (AnimationDrawable) indicator_loadding.getBackground();
        indicator_loadding.setVisibility(View.VISIBLE);
        animationDrawable.start();
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(10000);
                } catch (InterruptedException e) {
                    Log.d("BBB", "run: "+e.toString());
                }
            }
        };
        thread.start();
        Getdata();
        if(CheckConnection.checkConnect(getApplicationContext())){


        }else {

        }
    }
    private void Getdata() {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(arrayQuangcao!= null){
                    if(isOnline(context)){
                            ActionBar();
                            GetdulieuQuangcao();
                            ActionViewfliper();
                            Getdulieu();
                            GetSanPhamNew();
                            lvMenuOnclick();
                            tv_error.setVisibility(View.INVISIBLE);
                            animationDrawable.stop();
                            indicator_loadding.setVisibility(View.INVISIBLE);
                            linearLayoutMain.setVisibility(View.VISIBLE);

                    }else {
                        indicator_loadding.setVisibility(View.INVISIBLE);
                        animationDrawable.stop();
                        tv_error.setVisibility(View.VISIBLE);
                        CheckConnection.show_Toast_short("Bạn hãy kiểm tra kết nối",getApplicationContext());
                    }
                }

            }
            public boolean isOnline(Context context) {
                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                //should check null because in airplane mode it will be null
                return (netInfo != null && netInfo.isConnected());
            }
        };
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        getApplicationContext().registerReceiver(broadcastReceiver,intentFilter);
    }
    private void GetdulieuQuangcao() {
        retrofit2.Call <List<Quangcao>> callQuangcao = dataService.GetdataQuangcao();
        callQuangcao.enqueue(new Callback<List<Quangcao>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Quangcao>> call, retrofit2.Response<List<Quangcao>> response) {
                ArrayList<Quangcao> quangcaoArrayList = (ArrayList<Quangcao>) response.body();
                for(int i=0;i<quangcaoArrayList.size();i++){
                    arrayQuangcao.add(quangcaoArrayList.get(i));
                }
                for (int i=0;i<arrayQuangcao.size();i++){
                    ImageView imageView = new ImageView(getApplicationContext());
                    Picasso.with(getApplicationContext()).load(arrayQuangcao.get(i).getHinhanhquangcao())
                            .placeholder(R.drawable.noimg)
                            .error(R.drawable.erro_img)
                            .into(imageView);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    viewFlipperQuangCao.addView(imageView);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Quangcao>> call, Throwable t) {
                Log.d("BBB", "onFailure: "+t.getMessage());
            }
        });
    }

    private void lvMenuOnclick() {
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if(CheckConnection.checkConnect(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }else {
                            CheckConnection.show_Toast_short("Bạn hãy kiểm tra kết nối",getApplicationContext());
                        }
                        break;
                    case 1:
                        if(CheckConnection.checkConnect(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, DienThoaiActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("idLoaisanpham",arrayLoaisp.get(1).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.show_Toast_short("Bạn hãy kiểm tra kết nối",getApplicationContext());
                        }
                        break;
                    case 2:
                        if(CheckConnection.checkConnect(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, LapTopActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("idLoaisanpham",arrayLoaisp.get(2).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.show_Toast_short("Bạn hãy kiểm tra kết nối",getApplicationContext());
                        }
                        break;
                    case 3:
                        if(CheckConnection.checkConnect(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, lienhe.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }else {
                            CheckConnection.show_Toast_short("Bạn hãy kiểm tra kết nối",getApplicationContext());
                        }
                        break;
                    case 4:
                        if(CheckConnection.checkConnect(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, ThongTin.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }else {
                            CheckConnection.show_Toast_short("Bạn hãy kiểm tra kết nối",getApplicationContext());
                        }
                        break;
                }
            }
        });
    }

    private void GetSanPhamNew() {
        retrofit2.Call<List<Sanpham>> callSanpham = dataService.GetdataSanPhamNew();
        callSanpham.enqueue(new Callback<List<Sanpham>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Sanpham>> call, retrofit2.Response<List<Sanpham>> response) {
                ArrayList<Sanpham> arrayListSanpham = (ArrayList<Sanpham>) response.body();
                for (int i=0;i<arrayListSanpham.size();i++){
                    arraySanpham.add(arrayListSanpham.get(i));
                }
                sanphamAdapter = new SanphamAdapter(getApplicationContext(),arraySanpham);
                recyclerViewSanPham.setAdapter(sanphamAdapter);
            }

            @Override
            public void onFailure(retrofit2.Call<List<Sanpham>> call, Throwable t) {
                Log.d("loi", "onFailure: "+t.toString());
            }
        });


    }

    private void Getdulieu() {
        retrofit2.Call<List<Loaisp>> callLoaisp  = dataService.GetdataMenu();
        callLoaisp.enqueue(new Callback<List<Loaisp>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Loaisp>> call, retrofit2.Response<List<Loaisp>> response) {
                ArrayList<Loaisp> loaisps = (ArrayList<Loaisp>) response.body();
                for(int i=0;i<loaisps.size();i++){
                    arrayLoaisp.add(loaisps.get(i));
                }
                arrayLoaisp.add(new Loaisp(0,"Liên hệ","https://mangxahoicuathang.000webhostapp.com/hinh%20anh/hinh%20anh%20menu/ic_lienhe.png"));
                arrayLoaisp.add(new Loaisp(0, "Thông tin","https://mangxahoicuathang.000webhostapp.com/hinh%20anh/hinh%20anh%20menu/ic_thongtin.png"));
                loaispAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(retrofit2.Call<List<Loaisp>> call, Throwable t) {

            }
        });

    }

    private void ActionViewfliper() {
        viewFlipperQuangCao.setFlipInterval(5000);
        viewFlipperQuangCao.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipperQuangCao.setInAnimation(animation_slide_in);
        viewFlipperQuangCao.setOutAnimation(animation_slide_out);

    }

    private void ActionBar() {
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarMain.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDrawerMain.openDrawer(GravityCompat.START);
            }
        });
    }

    private void anhxa(){
        toolbarMain = (Toolbar) findViewById(R.id.toolbarMain);
        viewFlipperQuangCao = (ViewFlipper) findViewById(R.id.viewQuangCao);
        recyclerViewSanPham = (RecyclerView) findViewById(R.id.recyclerSanPham);
        navigationViewMenu = (NavigationView) findViewById(R.id.navigationMain);
        lvMenu = (ListView) findViewById(R.id.lvMenu);
        tv_error = (TextView) findViewById(R.id.tv_error);
        indicator_loadding = (ImageView) findViewById(R.id.imvLoadding);
        linearLayoutMain = (LinearLayout) findViewById(R.id.ln_activityMain);
        myDrawerMain = (DrawerLayout) findViewById(R.id.mydrawer_Main);
        dataService = APIService.getService();
        arrayLoaisp = new ArrayList<>();
        arrayLoaisp.add(new Loaisp(0,"Trang chủ","https://mangxahoicuathang.000webhostapp.com/hinh%20anh/hinh%20anh%20menu/ic_trangchu.png"));
        loaispAdapter = new LoaispAdapter(arrayLoaisp, getApplicationContext());
        lvMenu.setAdapter(loaispAdapter);
        arraySanpham = new ArrayList<>();
        arrayQuangcao = new ArrayList<>();
        recyclerViewSanPham.setHasFixedSize(true);
        recyclerViewSanPham.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        if(arrayGiohang!=null){

        }else {
            arrayGiohang = new ArrayList<>();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_giohang:
                Intent intent = new Intent(getApplicationContext(), a516100.pdu.cuahangonline.main.Giohang.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //Register permission
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            } else {

            }
        }
    }
}
