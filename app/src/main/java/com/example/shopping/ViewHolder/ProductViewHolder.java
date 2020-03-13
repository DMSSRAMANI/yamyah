package com.example.shopping.ViewHolder;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.R;
import com.rey.material.widget.Spinner;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtproductName, txtProductDescription, txtProductPrice;
    public ImageView imageView;
    public AdapterView.OnItemClickListener Listner;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);


        imageView =(ImageView) itemView.findViewById(R.id.product_image);
        txtproductName =(TextView) itemView.findViewById(R.id.product_name);
        txtProductDescription =(TextView) itemView.findViewById(R.id.product_descrption);
        txtProductPrice =(TextView) itemView.findViewById(R.id.product_price);
    }
    public void setItemClickListner(AdapterView.OnItemClickListener listner){
        this.Listner =Listner;
    }

    @Override
    public void onClick(View v) {


    }
}
