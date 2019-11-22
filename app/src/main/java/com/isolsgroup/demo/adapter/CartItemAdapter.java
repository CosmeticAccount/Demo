package com.isolsgroup.demo.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.isolsgroup.demo.DishCartActivity;
import com.isolsgroup.demo.R;
import com.isolsgroup.demo.SQLiteHandler;
import com.isolsgroup.demo.model.CartItem;
import com.isolsgroup.demo.model.HomeResponse;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartItemAdapter extends BaseAdapter {
    private List<CartItem> cartItems = Collections.emptyList();
    private final Context context;
    private SQLiteHandler handler;
    private static DecimalFormat df2;
    public CartItemAdapter(Context context, int carddesign) {
        this.context = context;
    }
    public void updateCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return cartItems.size();
    }
    @Override
    public CartItem getItem(int position) {
        return cartItems.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View itemView = null;
        LayoutInflater inflater;
        final ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.item_cart, null, false);
            handler = new SQLiteHandler(context);
            df2 = new DecimalFormat(".##");
        } else {
            itemView = convertView;
        }
        holder.tvCartItemName = (TextView) itemView.findViewById(R.id.txt_name);
        holder.tvCartItemPrice = (TextView) itemView.findViewById(R.id.txt_rupddcce);
        ImageView bClear = (ImageView) itemView.findViewById(R.id.Img_delete);
        holder._decrease =  itemView.findViewById(R.id.btn_minus);
        holder._increase = itemView.findViewById(R.id.btn_plus);
        holder._value = (TextView) itemView.findViewById(R.id.edit_text);
        final CartItem cartItem = getItem(position);
        holder._value.setText(cartItem.getQuantity() + "");
        holder._decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem cartItem1 = getItem(position);
                if (cartItem1.getProduct().getQty() > 1) {
                    handler.updateRecordgift(Long.parseLong(cartItem1.getProduct().getProductID ()), -1, cartItem1.getProduct().getMax (),
                            cartItem1.getProduct().getQty(),cartItem1.getProduct().getProductName ());
            //        DishCartActivity.tvFooterTotalPrice.setTypeface(typeface);
                    double total = Double.parseDouble(String.valueOf(handler.gettotalprice()));
                    DishCartActivity.tvFooterTotalPrice.setText(String.valueOf(df2.format(total)));
                    CartItemAdapter uradapteobjr = (CartItemAdapter) ((ListView) parent).getAdapter();
                    uradapteobjr.updateCartItems(getCartItems());
                    uradapteobjr.notifyDataSetChanged();
                }
            }
        });
        holder._increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem cartItem1 = getItem(position);
                handler.updateRecordgift(Long.parseLong(cartItem1.getProduct().getProductID ()), 1, cartItem1.getProduct().getMax (),
                        cartItem1.getProduct().getQty(),cartItem1.getProduct().getProductName ());
        //        DishCartActivity.tvFooterTotalPrice.setTypeface(typeface);
                double total = Double.parseDouble(String.valueOf(handler.gettotalprice()));
                DishCartActivity.tvFooterTotalPrice.setText(String.valueOf(df2.format(total)));
                CartItemAdapter uradapteobjr = (CartItemAdapter) ((ListView) parent).getAdapter();
                uradapteobjr.updateCartItems(getCartItems());
                uradapteobjr.notifyDataSetChanged();
            }
        });
        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    handler.DeleteRecordgift(Long.parseLong(cartItem.getProduct().getProductID ()), cartItem.getProduct().getProductName ());
                //    DishCartActivity.tvFooterTotalPrice.setTypeface(typeface);
                    double total = Double.parseDouble(String.valueOf(handler.gettotalprice()));
                    DishCartActivity.tvFooterTotalPrice.setText(String.valueOf(df2.format(total)));
                    if (handler.countRecord() == 0) {
                        DishCartActivity.tvFooterTotalPrice.setText("00.00");
                    }
                    CartItemAdapter uradapteobjr = (CartItemAdapter) ((ListView) parent).getAdapter();
                    uradapteobjr.updateCartItems(getCartItems());
                    uradapteobjr.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        String title = cartItem.getProduct().getProductName ();
        holder.tvCartItemName.setText(title);
        holder.tvCartItemPrice.setText(String.valueOf(cartItem.getProduct().getProductPrice ()));
        return itemView;
    }
    private List<CartItem> getCartItems() {
        List<CartItem> cartItems = new ArrayList<CartItem> ();
        Cursor allitem = handler.selectRecords();
        boolean b = false;
        if (allitem.moveToFirst()) {
            do {
                CartItem cartItem = new CartItem();
                HomeResponse k = new HomeResponse();
                k.setProductID (allitem.getString(1));
                k.setProductName (allitem.getString(2));
                k.setImages (allitem.getString(3));
                k.setQty(allitem.getInt(4));
                if (k.getProductName ().substring(0, 2).contains("GV")) {
                    b = true;
                }
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
    private static class ViewHolder {
        TextView tvCartItemName, tvCartItemPrice, _value;
        ImageView _decrease, _increase;
    }
}