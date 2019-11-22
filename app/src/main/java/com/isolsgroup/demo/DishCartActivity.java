package com.isolsgroup.demo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.isolsgroup.demo.adapter.CartItemAdapter;
import com.isolsgroup.demo.model.CartItem;
import com.isolsgroup.demo.model.HomeResponse;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DishCartActivity extends AppCompatActivity implements View.OnClickListener {
    @SuppressWarnings("unused")
    private static final String TAG = "DishCartActivity";
    ListView lvCartItems;
    public static TextView tvFooterTotalPrice;
    private LinearLayout proceedButton;
    Context context;
    public static CartItemAdapter cartItemAdapter;
    SQLiteHandler handler;
    Toolbar toolbar;
    double total;
  //  Typeface face;
    private static DecimalFormat df2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_cart);

        context = this;
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.toolbar.setNavigationIcon((int) R.drawable.ic_keyboard_arrow_left_white_24dp);
        this.toolbar.setTitle((CharSequence) "Cart");
        this.toolbar.setTitleTextColor(-1);
        setSupportActionBar(this.toolbar);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DishCartActivity.this.finish();
            }
        });
        initViews();
        cartItemAdapter = new CartItemAdapter(context, R.layout.item_cart);
        cartItemAdapter.updateCartItems(getCartItems());
        lvCartItems.setAdapter(cartItemAdapter);
        try{
            total = Double.parseDouble(String.valueOf(handler.gettotalprice()));
            tvFooterTotalPrice.setText(String.valueOf(df2.format(total)));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        proceedButton.setOnClickListener(this);
    }

    private void initViews(){
        PrefernceSettings.openDataBase(context);
        handler = new SQLiteHandler(this);
   //     face = Typeface.createFromAsset(getAssets(), "Rupee_Foradian.ttf");
        df2 = new DecimalFormat("0.00");
        df2.setMaximumFractionDigits(2);
        lvCartItems = (ListView) findViewById(R.id.ListViewCatalog);
        tvFooterTotalPrice = findViewById(R.id.total);
        proceedButton = findViewById(R.id.button_layout);
   //     tvFooterTotalPrice.setTypeface(face);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_layout:
                if (handler.countRecord() < 1){
                    Toast toast = Toast.makeText(context, "Your cart is Empty", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 90);
                    toast.show();
                }else {

                    total = Double.parseDouble(String.valueOf(handler.gettotalprice()));
                    try {
                        String dealsStatus = "0";
                        //    Intent intent = new Intent(getApplicationContext(), ConfirmAddressActivity.class);
                        //    intent.putExtra("amount", String.valueOf(total));
                        //    intent.putExtra("dealsStatus", dealsStatus);
                        //    startActivity(intent);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
    private List<CartItem> getCartItems() {
        List<CartItem> cartItems = new ArrayList<CartItem> ();
        Cursor allitem = handler.selectRecords();
        if (allitem.moveToFirst()) {
            do {
                CartItem cartItem = new CartItem();
                HomeResponse k = new HomeResponse();
                k.setProductID (allitem.getString(1));
                k.setProductName (allitem.getString(2));
                k.setImages (allitem.getString(3));
                k.setQty(allitem.getInt(4));
                k.setProductPrice (allitem.getString(5));
                k.setMax (allitem.getString(6));
                cartItem.setProduct(k);
                cartItem.setQuantity(allitem.getInt(4));
                cartItems.add(cartItem);
            } while (allitem.moveToNext());
        }
        allitem.close();
        return cartItems;
    }
    @Override
    public void onResume(){
        super.onResume();
    }
}
