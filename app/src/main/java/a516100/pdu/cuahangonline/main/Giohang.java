package a516100.pdu.cuahangonline.main;

import android.app.AlertDialog;
import android.app.usage.UsageEvents;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import a516100.pdu.cuahangonline.R;
import a516100.pdu.cuahangonline.adapter.GiohangAdapter;
import a516100.pdu.cuahangonline.until.CheckConnection;

public class Giohang extends AppCompatActivity {
    List<Giohang> arrayGiohang;
    ListView lvGiohang;
    TextView tvThongbao;
    static TextView tvTongtien;
    Button btnThanhtoan, btnTieptucmua;
    Toolbar toolbar;
    GiohangAdapter giohangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        anhxa();
        ActionToolbar();
        Checkdata();
        EventUtil();
        OnItemLongCLickListview();
        EventButton();
    }

    private void EventButton() {
        btnTieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        btnThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.arrayGiohang.size()>0){
                    Intent intent = new Intent(getApplicationContext(), ThongtinKhachhang.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }else {
                    CheckConnection.show_Toast_short("Giỏ hàng của bạn hiện đang trống", getApplicationContext());
                }
            }
        });
    }

    private void OnItemLongCLickListview() {
        lvGiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Giohang.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity.arrayGiohang.size()<=0){
                            tvThongbao.setVisibility(View.VISIBLE);
                        }else {
                            MainActivity.arrayGiohang.remove(position);
                            giohangAdapter.notifyDataSetChanged();
                            EventUtil();
                            if(MainActivity.arrayGiohang.size()<=0){
                                tvThongbao.setVisibility(View.VISIBLE);
                            }else {
                                tvThongbao.setVisibility(View.INVISIBLE);
                                EventUtil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        giohangAdapter.notifyDataSetChanged();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void EventUtil() {
        int tongtien = 0;
        for (int i =0;i<MainActivity.arrayGiohang.size();i++){
            tongtien += MainActivity.arrayGiohang.get(i).getGiasp();
        }
        DecimalFormat format = new DecimalFormat("###,###");
        tvTongtien.setText(format.format(tongtien)+"Đ");
    }

    private void Checkdata() {
        if(MainActivity.arrayGiohang.size()<=0){
            giohangAdapter.notifyDataSetChanged();
            tvThongbao.setVisibility(View.VISIBLE);
            lvGiohang.setVisibility(View.INVISIBLE);
        }else {
            giohangAdapter.notifyDataSetChanged();
            tvThongbao.setVisibility(View.INVISIBLE);
            lvGiohang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        lvGiohang = (ListView) findViewById(R.id.lvGiohang);
        tvThongbao = (TextView) findViewById(R.id.tvThongbao);
        tvTongtien = (TextView) findViewById(R.id.tvGiatri);
        btnThanhtoan = (Button) findViewById(R.id.btnThanhtoan);
        btnTieptucmua = (Button) findViewById(R.id.btnTieptucmuahang);
        toolbar = (Toolbar) findViewById(R.id.toolbarGiohang);
        giohangAdapter = new GiohangAdapter(getApplicationContext(),MainActivity.arrayGiohang);
        lvGiohang.setAdapter(giohangAdapter);
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
                Intent intent = new Intent(getApplicationContext(),Giohang.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
