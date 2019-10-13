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
import java.text.Format;
import java.util.ArrayList;

import a516100.pdu.cuahangonline.R;
import a516100.pdu.cuahangonline.model.Sanpham;

public class DienthoaiAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraySanpham;

    public DienthoaiAdapter(Context context, ArrayList<Sanpham> arraySanpham) {
        this.context = context;
        this.arraySanpham = arraySanpham;
    }

    @Override
    public int getCount() {
        return arraySanpham.size();
    }

    @Override
    public Object getItem(int position) {
        return arraySanpham.get(position);
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
            convertView = layoutInflater.inflate(R.layout.dongdienthoai,null);
            viewHolder.tvTenDienthoai = (TextView) convertView.findViewById(R.id.tvTenDienthoai);
            viewHolder.tvGiaDienthoai = (TextView) convertView.findViewById(R.id.tvGiaDienthoai);
            viewHolder.tvMotaDienthoai = (TextView) convertView.findViewById(R.id.tvMotaDienthoai);
            viewHolder.imvDienthoai = (ImageView)  convertView.findViewById(R.id.imvDienthoai);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(position);
        viewHolder.tvTenDienthoai.setText(sanpham.getTensp());
        DecimalFormat format = new DecimalFormat("###,###");
        viewHolder.tvGiaDienthoai.setText("Giá : "+format.format(sanpham.getGiasp())+" Đ");
        viewHolder.tvMotaDienthoai.setMaxLines(2);
        viewHolder.tvMotaDienthoai.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.tvMotaDienthoai.setText(sanpham.getMotasp());
        Picasso.with(context).load(sanpham.getHinhanhsp())
                             .placeholder(R.drawable.noimg)
                             .error(R.drawable.erro_img)
                             .into(viewHolder.imvDienthoai);
        return convertView;
    }
    public class ViewHolder{
        TextView tvTenDienthoai;
        TextView tvGiaDienthoai;
        TextView tvMotaDienthoai;
        ImageView imvDienthoai;
    }

}
