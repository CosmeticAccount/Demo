package com.isolsgroup.demo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import net.steamcrafted.loadtoast.LoadToast;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private TextView txtProductName, txtProductPrice, txtProductDetails;
    String productImage, productName, productPrice, productDetails, productID;
    ImageView imgBack, imgCart;
    private Context context;
    SQLiteHandler sqLiteHandler;
    int showsixe = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_product_detail);

        context = this;
        sqLiteHandler = new SQLiteHandler (ProductDetailActivity.this);

        getDataFromIntent();
        initViews();
        setData();
    }

    private void initViews(){
        imageView = findViewById (R.id.img_product);
        txtProductName = findViewById (R.id.txt_product_name);
        txtProductPrice = findViewById (R.id.txt_product_price);
        txtProductDetails = findViewById (R.id.txt_product_details);
        imgBack = findViewById (R.id.img_back);
        imgCart = findViewById (R.id.img_cart);

        imgBack.setOnClickListener (this);
        imgCart.setOnClickListener (this);
    }

    private void getDataFromIntent(){
        productImage = getIntent ().getStringExtra ("productImage");
        productName = getIntent ().getStringExtra ("productName");
        productPrice = getIntent ().getStringExtra ("productPrice");
        productDetails = getIntent ().getStringExtra ("productDetails");
        productID = getIntent ().getStringExtra ("productID");
    }

    private void setData(){
        txtProductName.setText (productName);
        txtProductPrice.setText (productPrice);
        txtProductDetails.setText (productDetails);
        Glide.with (ProductDetailActivity.this).load (productImage).into (imageView);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId ()){
           case R.id.img_back:
               ProductDetailActivity.this.finish();
               break;

           case R.id.img_cart:
               onDishesClick();
               break;
       }
    }

    public void onDishesClick() {
        final LoadToast lt = new LoadToast(context)
                .setTranslationY(1500)
                .setBackgroundColor(Color.LTGRAY)
                .setProgressColor(Color.GREEN)
                .show();
        /*dishList = menuCategoryList.get(position);
        try{
            dishList.setmax(PrefernceSettings.getMax());
        }catch (Exception e){
            e.printStackTrace();
        }*/

        showsixe = 0;
        /*try {
            showsixe = sqlMenuHelper.countRecordbynid(Long.parseLong(dishList.getDishID()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/

        String strMax = "100";

        sqLiteHandler.inserRecord(Long.parseLong(productID),productName,
                productImage, Integer.valueOf(PrefernceSettings.getQuantity()),
                productPrice, strMax);
    //    MenuActivity.itemCounter.setText(sqlMenuHelper.countRecord() + "");

        if (Integer.parseInt(strMax) > showsixe) {
            new Handler ().postDelayed(new Runnable() {
                @Override
                public void run() {
                    lt.success();
                }
            },  (long) 200);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    lt.error();
                }
            },  (long) 200);
        }
    }
}
