package a516100.pdu.cuahangonline.main;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import a516100.pdu.cuahangonline.R;
import a516100.pdu.cuahangonline.Service.APIService;
import a516100.pdu.cuahangonline.Service.DataService;
import a516100.pdu.cuahangonline.adapter.LapTopAdapter;
import a516100.pdu.cuahangonline.model.Sanpham;
import a516100.pdu.cuahangonline.until.CheckConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LapTopActivity extends AppCompatActivity {
    Toolbar toolbarLaptop;
    ListView lvLaptop;
    LapTopAdapter lapTopAdapter;
    ArrayList<Sanpham> arrayLaptop;
    DataService dataService;
    int idLaptop = 0;
    int page =1;
    View footerView;
    Boolean Isloading = false;
    Boolean limitData = false;
    myHanler myHanler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_top);
        anhxa();
        if(CheckConnection.checkConnect(getApplicationContext())){
            GetIDLoaisp();
            ActionToolbar();
            Getdata(page);
            LoadMoreData();
        }else {
            CheckConnection.show_Toast_short("Kiểm tra lại kết nối internet",getApplicationContext());
            finish();
        }
    }

    private void anhxa() {
        toolbarLaptop= (Toolbar) findViewById(R.id.toolbarlapTop);
        lvLaptop = (ListView) findViewById(R.id.lvlaptop);
        dataService = APIService.getService();
        arrayLaptop = new ArrayList<>();
        lapTopAdapter = new LapTopAdapter(getApplicationContext(), arrayLaptop);
        lvLaptop.setAdapter(lapTopAdapter);
        LayoutInflater inflate = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerView = inflate.inflate(R.layout.progresbar,null);
        myHanler = new myHanler();
    }
    private void GetIDLoaisp() {
        idLaptop = getIntent().getIntExtra("idLoaisanpham",-1);
        Log.d("BBB", "GetIDLoaisp: "+idLaptop);
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbarLaptop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLaptop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void LoadMoreData() {
        lvLaptop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), chitietsanpham.class);
                intent.putExtra("thongtinsanpham", arrayLaptop.get(position));
                startActivity(intent);
            }
        });
        lvLaptop.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if((firstVisibleItem+visibleItemCount)==totalItemCount && totalItemCount !=0 && Isloading==false && limitData ==false ){
                    Isloading =true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();
                }
            }
        });
    }
    private void Getdata(int Page) {
        retrofit2.Call<List<Sanpham>> callSanpham = dataService.GetdataDienthoai(Page,idLaptop);
        callSanpham.enqueue(new Callback<List<Sanpham>>() {
            @Override
            public void onResponse(Call<List<Sanpham>> call, Response<List<Sanpham>> response) {
                if(response.body() != null && response.body().size() > 0 ){
                    lvLaptop.removeFooterView(footerView);
                    ArrayList<Sanpham> arrayListlaptop = (ArrayList) response.body();
                    for(int i=0;i<arrayListlaptop.size();i++){
                        arrayLaptop.add(arrayListlaptop.get(i));
                    }
                    lapTopAdapter.notifyDataSetChanged();
                }else {
                    limitData = true;
                    lvLaptop.removeFooterView(footerView);
                    CheckConnection.show_Toast_short("Đã hết dữ liệu", getApplicationContext());
                }
            }

            @Override
            public void onFailure(Call<List<Sanpham>> call, Throwable t) {

            }
        });

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
    public class myHanler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    lvLaptop.addFooterView(footerView);
                    break;
                case 1:
                    Getdata(++page);
                    Isloading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends Thread{
        @Override
        public void run() {
            myHanler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message =myHanler.obtainMessage(1);
            myHanler.sendMessage(message);
            super.run();
        }
    }

}
