package pjk.edu.fcu.sqlite.sqlite_hw;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kiam on 09/05/2016.
 */
class MyBaseAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private int count = 0;
    private ArrayList<DBdata> dataList = new ArrayList<DBdata>();

    public ArrayList<DBdata> getDataList() {
        return dataList;
    }

    private class View_TalkLayout {  //爲什麽要這個?   爲什麽不直接設定TextVIew 而去使用一個Class存著
        TextView name,calories;
    }

    public MyBaseAdapter(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE); //不知道爲什麽要這樣設，爲什麽不放LinearLayout
    }
    /*自己寫的副函數*/
    public void addItem(DBdata data) {
        dataList.add(data);
        count++;
        this.notifyDataSetChanged();    //更新目前的Item
    }

    public void removeALL(){
        dataList.clear();
        this.notifyDataSetChanged();
    }

    public void addAll(){

    }
    /*自己寫的副函數*/
    public int getCount() {
        return count;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }



    public View getView(final int position, View convertView,ViewGroup parent) {
        View_TalkLayout view_TalkLayout;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.show_listview,null);
            view_TalkLayout = new View_TalkLayout();
            view_TalkLayout.name = (TextView)convertView.findViewById(R.id.name);
            view_TalkLayout.calories = (TextView)convertView.findViewById(R.id.calories);
            convertView.setTag(view_TalkLayout);
        } else {
            view_TalkLayout = (View_TalkLayout) convertView.getTag();   //getTag()的用處？
        }
        Log.v("tag",position+"");
        view_TalkLayout.name.setText(dataList.get(position).getName());           //顯示名字
        view_TalkLayout.calories.setText(dataList.get(position).getCalories()+"");   //顯示卡路里


        return convertView;
    }

}