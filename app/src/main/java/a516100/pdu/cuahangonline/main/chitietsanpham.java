package a516100.pdu.cuahangonline.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import a516100.pdu.cuahangonline.R;
import a516100.pdu.cuahangonline.model.Giohang;
import a516100.pdu.cuahangonline.model.Sanpham;

public class chitietsanpham extends AppCompatActivity {
    Toolbar toolbarChitiet;
    ImageView imvChitiet;
    TextView tvTen, tvGia, tvMota;
    Button btnDatmua;
    Spinner spinner;
    int id = 0;
    String ten = "";
    int gia = 0;
    String mota = "";
    String hinhanh = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham);
        anhxa();
        ActionToolbar();
        GetInForMation();
        CatchEventSpinner();
        EventButonMuahang();
    }

    private void EventButonMuahang() {
        btnDatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.arrayGiohang.size() > 0) {
                    int sl = Integer.parseInt((spinner.getSelectedItem().toString()));
                    Boolean exists = false;
                    for (int i = 0; i < MainActivity.arrayGiohang.size(); i++) {
                        if (MainActivity.arrayGiohang.get(i).getIdsp() == id) {
                            MainActivity.arrayGiohang.get(i).setSoluongsp(MainActivity.arrayGiohang.get(i).getSoluongsp() + sl);
                            if (MainActivity.arrayGiohang.get(i).getSoluongsp() >= 10) {
                                MainActivity.arrayGiohang.get(i).setSoluongsp(10);
                            }
                            exists = true;
                            MainActivity.arrayGiohang.get(i).setGiasp(gia * MainActivity.arrayGiohang.get(i).getSoluongsp());
                        }
                    }
                    if (exists == false) {
                        int soluong = Integer.parseInt((spinner.getSelectedItem().toString()));
                        long giamoi = soluong * gia;
                        MainActivity.arrayGiohang.add(new Giohang(id, ten, giamoi, hinhanh, soluong));
                    }
                } else {
                    int soluong = Integer.parseInt((spinner.getSelectedItem().toString()));
                    long giamoi = soluong * gia;
                    MainActivity.arrayGiohang.add(new Giohang(id, ten, giamoi, hinhanh, soluong));
                }
                Intent intent = new Intent(getApplicationContext(), a516100.pdu.cuahangonline.main.Giohang.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] soluong = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void GetInForMation() {
        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanpham.getId();
        ten = sanpham.getTensp();
        gia = sanpham.getGiasp();
        mota = sanpham.getMotasp();
        hinhanh = sanpham.getHinhanhsp();
        tvTen.setText(ten);
        DecimalFormat format = new DecimalFormat("###,###");
        tvGia.setText("Giá : " + format.format(gia) + " Đ");
        tvMota.setText(mota);
        Picasso.with(getApplicationContext()).load(hinhanh)
                .placeholder(R.drawable.noimg)
                .error(R.drawable.erro_img)
                .into(imvChitiet);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarChitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbarChitiet = (Toolbar) findViewById(R.id.toolbarChitietsp);
        imvChitiet = (ImageView) findViewById(R.id.imvChitietsp);
        tvTen = (TextView) findViewById(R.id.tvTenChitietsp);
        tvGia = (TextView) findViewById(R.id.tvGiaChitietsp);
        tvMota = (TextView) findViewById(R.id.tvMotachitietsp);
        spinner = (Spinner) findViewById(R.id.spiner);
        btnDatmua = (Button) findViewById(R.id.btnDatmua);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_giohang:
                Intent intent = new Intent(getApplicationContext(), a516100.pdu.cuahangonline.main.Giohang.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
