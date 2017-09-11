package com.mostafaabdel_fatah.deleteitemlist;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.IdentityHashMap;


class DeleteItemListAdapter extends BaseAdapter {

    ArrayList<Item> items;
    Context context;

    public DeleteItemListAdapter(Context context , ArrayList<Item> items){
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.row,null);
        final RelativeLayout linearLayout = (RelativeLayout) view.findViewById(R.id.linearLayout);
        TextView text= (TextView) view.findViewById(R.id.textView);
        text.setText(items.get(position).getText());
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        if (MainActivity.show_checkbox)
            checkBox.setVisibility(View.VISIBLE);
        else
            checkBox.setVisibility(View.INVISIBLE);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    MainActivity.deleteItem.add(items.get(position).getId());
                    linearLayout.setBackgroundColor(Color.LTGRAY);
                }else
                    if (MainActivity.deleteItem.size() > 0 )
                        for (int i = 0 ; i < MainActivity.deleteItem.size() ; i++){
                            int m = MainActivity.deleteItem.get(i);
                            if (m == items.get(position).getId()){
                                MainActivity.deleteItem.remove(i);
                                linearLayout.setBackgroundColor(Color.WHITE);
                            }
                        }
                MainActivity.actionMode.setTitle(MainActivity.deleteItem.size()+" items selected");
            }
        });


        return view ;
    }


}

