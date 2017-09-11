package com.mostafaabdel_fatah.deleteitemlist;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    static ActionMode actionMode;
    static boolean show_checkbox = false;
    ArrayList<Item> allList = new ArrayList<>();
    static ArrayList<Integer> deleteItem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] ar = {"Mostafa","Abd El_Fatah","Mohammed","Ali","Ismael","Saad","Khaled","Walid","Omer"
                ,"Ahemad","Mostafa Abd El_Fatah","Khaled AbdEl_Fatah","Omer AbdEl_Fatah","Mohamed AbdEl_Fatah"
                ,"Osam","Ahemad Saad" , "Ali Saad" , "Hesssin Saad" , "Hessuin" ,"Mohoumd"};
        for (int i = 0 ; i < ar.length ; i++ ) {
            allList.add(new Item(i,ar[i]));
        }
        listView = (ListView) findViewById(R.id.listview);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        final DeleteItemListAdapter adapter = new DeleteItemListAdapter(this,allList);
        listView.setAdapter(adapter);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                show_checkbox = true ;
                adapter.notifyDataSetChanged();
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                actionMode.getMenuInflater().inflate(R.menu.menuitems,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                actionMode.setTitle("0 items Selected");
                MainActivity.actionMode = actionMode;
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {

                int id = menuItem.getItemId();
                if (id == R.id.delete){
                    for(int i = 0 ;i < deleteItem.size() ; i++ )
                        for(int j = 0 ;j < allList.size() ; j++ )
                            if (deleteItem.get(i) == allList.get(j).getId())
                                allList.remove(j);
                    deleteItem.clear();
                    show_checkbox = false;
                    adapter.notifyDataSetChanged();
                    actionMode.finish();
                    return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                deleteItem.clear();
                show_checkbox = false ;
                adapter.notifyDataSetChanged();
            }
        });
    }



}
