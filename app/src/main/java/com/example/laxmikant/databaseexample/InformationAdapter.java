package com.example.laxmikant.databaseexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.List;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.ItemHolder> {
    Context context;
    List<EmployeeInfoModel> list;

    public InformationAdapter(Context context, List<EmployeeInfoModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.info_item, parent,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        EmployeeInfoModel model = list.get(position);
        holder.name.setText(model.getName());
        holder.age.setText(model.getAge());
        holder.company.setText(model.getCompany());
        holder.role.setText(model.getRole());
        holder.image.setImageBitmap(chageByteArrayToBitmap(model.getImageArray()));
    }

    private Bitmap chageByteArrayToBitmap(byte[] imageArray) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageArray);
        Bitmap bitmapImage = BitmapFactory.decodeStream(inputStream);
        return bitmapImage;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        CardView card;
        ImageView image;
        TextView name, company, age, role;

        public ItemHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            company = itemView.findViewById(R.id.company);
            age = itemView.findViewById(R.id.age);
            role = itemView.findViewById(R.id.role);
        }
    }
}
