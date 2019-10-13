package a516100.pdu.cuahangonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import a516100.pdu.cuahangonline.R;
import a516100.pdu.cuahangonline.main.MainActivity;
import a516100.pdu.cuahangonline.model.Giohang;

public class GiohangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Giohang> arrayGiohang;

    public GiohangAdapter(Context context, ArrayList<Giohang> arrayGiohang) {
        this.context = context;
        this.arrayGiohang = arrayGiohang;
    }

    @Override
    public int getCount() {
        return arrayGiohang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayGiohang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView ==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_listview_giohang,null);
            viewHolder.tvTenhang = (TextView) convertView.findViewById(R.id.tvTenhangGiohang);
            viewHolder.tvGiahang = (TextView) convertView.findViewById(R.id.tvGIahangGiohang);
            viewHolder.imvHinhanh = (ImageView) convertView.findViewById(R.id.imvGiohang);
            viewHolder.btnMinus = (Button) convertView.findViewById(R.id.btnminus);
            viewHolder.btnPlus = (Button) convertView.findViewById(R.id.btnplus);
            viewHolder.btnValue = (Button) convertView.findViewById(R.id.btnValue);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Giohang giohang = (Giohang) getItem(position);
        viewHolder.tvTenhang.setText(giohang.getTensp());
        DecimalFormat format = new DecimalFormat("###,###");
        viewHolder.tvGiahang.setText(format.format(giohang.getGiasp()));
        Picasso.with(context).load(giohang.getHinhanhsp())
                             .placeholder(R.drawable.noimg)
                             .error(R.drawable.erro_img)
                             .into(viewHolder.imvHinhanh);
        viewHolder.btnValue.setText(giohang.getSoluongsp()+"");
        final int soluong = Integer.parseInt(viewHolder.btnValue.getText().toString());

        if(soluong>=10) {
            viewHolder.btnPlus.setVisibility(View.INVISIBLE);
        }else if (soluong<=1){
            viewHolder.btnMinus.setVisibility(View.INVISIBLE);
        }else{
            viewHolder.btnMinus.setVisibility(View.VISIBLE);
            viewHolder.btnPlus.setVisibility(View.VISIBLE);
        }
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slHientai = MainActivity.arrayGiohang.get(position).getSoluongsp();
                int slmoinhat = Integer.parseInt(finalViewHolder.btnValue.getText().toString())+1;
                long Giahientai =  MainActivity.arrayGiohang.get(position).getGiasp();
                MainActivity.arrayGiohang.get(position).setSoluongsp(slmoinhat);
                long GiaMoinhat = (Giahientai * slmoinhat) /slHientai;
                MainActivity.arrayGiohang.get(position).setGiasp(GiaMoinhat);
                DecimalFormat format = new DecimalFormat("###,###");
                finalViewHolder.tvGiahang.setText(format.format(GiaMoinhat));
                finalViewHolder.btnValue.setText(slmoinhat+"");
                a516100.pdu.cuahangonline.main.Giohang.EventUtil();
                if(slmoinhat >9){
                    finalViewHolder.btnMinus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnPlus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnValue.setText(slmoinhat+"");
                }else {
                    finalViewHolder.btnMinus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnPlus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnValue.setText(slmoinhat+"");
                }
            }
        });
        viewHolder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slHientai = MainActivity.arrayGiohang.get(position).getSoluongsp();
                int slmoinhat = Integer.parseInt(finalViewHolder.btnValue.getText().toString())-1;
                long Giahientai =  MainActivity.arrayGiohang.get(position).getGiasp();
                MainActivity.arrayGiohang.get(position).setSoluongsp(slmoinhat);
                long GiaMoinhat = (Giahientai * slmoinhat) /slHientai;
                MainActivity.arrayGiohang.get(position).setGiasp(GiaMoinhat);
                DecimalFormat format = new DecimalFormat("###,###");
                finalViewHolder.tvGiahang.setText(format.format(GiaMoinhat));
                finalViewHolder.btnValue.setText(slmoinhat+"");
                a516100.pdu.cuahangonline.main.Giohang.EventUtil();
                if(slmoinhat <2){
                    finalViewHolder.btnMinus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnPlus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnValue.setText(slmoinhat+"");
                }else {
                    finalViewHolder.btnMinus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnPlus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnValue.setText(slmoinhat+"");
                }
            }
        });
        return convertView;
    }
    public class ViewHolder{
        public TextView tvTenhang;
        public TextView tvGiahang;
        public Button btnMinus;
        public Button btnPlus;
        public Button btnValue;
        public ImageView imvHinhanh;
    }
}
