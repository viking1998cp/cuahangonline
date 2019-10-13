package a516100.pdu.cuahangonline.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import a516100.pdu.cuahangonline.R;
import a516100.pdu.cuahangonline.model.Sanpham;

public class LapTopAdapter  extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arrayLaptop;

    public LapTopAdapter(Context context, ArrayList<Sanpham> arrayLaptop) {
        this.context = context;
        this.arrayLaptop = arrayLaptop;
    }

    @Override
    public int getCount() {
        return arrayLaptop.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayLaptop.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.donglaptop,null);
            viewHolder.tvTenLaptop = (TextView) convertView.findViewById(R.id.tvTenLaptop);
            viewHolder.tvGialaptop = (TextView) convertView.findViewById(R.id.tvGiaLaptop);
            viewHolder.tvMotaLaptop = (TextView) convertView.findViewById(R.id.tvMotaLaptop);
            viewHolder.imvLaptop = (ImageView)  convertView.findViewById(R.id.imvLaptop);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(position);
        viewHolder.tvTenLaptop.setText(sanpham.getTensp());
        DecimalFormat format = new DecimalFormat("###,###");
        viewHolder.tvGialaptop.setText("Giá : "+format.format(sanpham.getGiasp())+" Đ");
        viewHolder.tvMotaLaptop.setMaxLines(2);
        viewHolder.tvMotaLaptop.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.tvMotaLaptop.setText(sanpham.getMotasp());
        Picasso.with(context).load(sanpham.getHinhanhsp())
                .placeholder(R.drawable.noimg)
                .error(R.drawable.erro_img)
                .into(viewHolder.imvLaptop);
        return convertView;
    }

    public class ViewHolder{
        TextView tvTenLaptop;
        TextView tvGialaptop;
        TextView tvMotaLaptop;
        ImageView imvLaptop;
    }
}
