package a516100.pdu.cuahangonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import a516100.pdu.cuahangonline.R;
import a516100.pdu.cuahangonline.main.chitietsanpham;
import a516100.pdu.cuahangonline.model.Sanpham;
import a516100.pdu.cuahangonline.until.ItemclickListener;

public class SanphamAdapter  extends RecyclerView.Adapter<SanphamAdapter.viewHolder>  {
    Context context;
    ArrayList<Sanpham> arraySanpham;

    public SanphamAdapter(Context context, ArrayList<Sanpham> arraySanpham) {
        this.context = context;
        this.arraySanpham = arraySanpham;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dong_recyclerview_sanphammoi,null);
        viewHolder viewHolder = new viewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, final int i) {
        Sanpham sanpham = arraySanpham.get(i);
        viewHolder.tvTenSanpham.setText(sanpham.getTensp());
        DecimalFormat format = new DecimalFormat("###,###,###");
        viewHolder.tvGiaSanpham.setText("Giá : "+format.format(sanpham.getGiasp())+" Đ");
        Picasso.with(context).load(sanpham.getHinhanhsp())
                                .placeholder(R.drawable.noimg)
                                .error(R.drawable.erro_img)
                                .into(viewHolder.Hinhsanpham);
        viewHolder.setItemclickListener(new ItemclickListener() {
            @Override
            public void Onclick(View view, int position, boolean isLongclick) {
                Intent intent = new Intent(context, chitietsanpham.class);
                intent.putExtra("thongtinsanpham", arraySanpham.get(i));
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return arraySanpham.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener , View.OnClickListener{
        public ImageView Hinhsanpham;
        public TextView tvTenSanpham, tvGiaSanpham;
        private ItemclickListener itemclickListener;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            Hinhsanpham = (ImageView) itemView.findViewById(R.id.imvSanpham);
            tvTenSanpham = (TextView) itemView.findViewById(R.id.tvTensampham);
            tvGiaSanpham = (TextView) itemView.findViewById(R.id.tvGiasanpham);
            itemView.setOnClickListener(this);
        }
        public void setItemclickListener(ItemclickListener itemclickListener){
            this.itemclickListener = itemclickListener;
        }
        @Override
        public void onClick(View v) {
            itemclickListener.Onclick(v,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
