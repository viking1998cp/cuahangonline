package a516100.pdu.cuahangonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import a516100.pdu.cuahangonline.R;
import a516100.pdu.cuahangonline.model.Loaisp;

public class LoaispAdapter extends BaseAdapter {
    ArrayList<Loaisp> arrayLoaisp;
    Context context;

    public LoaispAdapter(ArrayList<Loaisp> arrayLoaisp, Context context) {
        this.arrayLoaisp = arrayLoaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayLoaisp.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayLoaisp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView tvTenLoaisp;
        ImageView imgLoaisp;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_listview_loaisp,null);
            viewHolder.imgLoaisp = (ImageView) convertView.findViewById(R.id.imvLoaisp);
            viewHolder.tvTenLoaisp = (TextView) convertView.findViewById(R.id.tvLoaisp);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Loaisp loaisp = (Loaisp) getItem(position);
        viewHolder.tvTenLoaisp.setText(loaisp.getTenloaisp());
        Picasso.with(context).load(loaisp.getHinhanhloaisp())
                .placeholder(R.drawable.noimg)
                .error(R.drawable.erro_img)
                .into(viewHolder.imgLoaisp);
        return convertView;
    }
}
