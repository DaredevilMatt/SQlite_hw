package pjk.edu.fcu.sqlite.sqlite_hw;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Main2Activity extends Activity{
    foodDBHelper myDb;
    Button showlist;
    ListView lv;
    MyBaseAdapter DataListAdapter;
    private static final int ACTIVITY_KEY = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }


    //初始化資料庫
    public void InitDB(){
        myDb = new foodDBHelper(this);
    }

    public void InitView(){
        showlist = (Button)findViewById(R.id.showlist);
        lv = (ListView)findViewById(R.id.listView);
    }

    public void InitAdapter(){
        DataListAdapter = new MyBaseAdapter(this);
        lv.setAdapter(DataListAdapter);
    }

    public void InitListener(){
        showlist.setOnClickListener(ListAllItem);
        lv.setOnItemClickListener(itemClick);
    }

    AdapterView.OnItemClickListener itemClick = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent();
            intent.setClass(Main2Activity.this, Main3Activity.class);
            intent.putExtra("KEY_CLASS", DataListAdapter.getDataList());
            intent.putExtra("KEY_INDEX", position);
            startActivity(intent);
        }
    };

    private View.OnClickListener ListAllItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            InitDB();
            InitView();
            InitAdapter();
            InitListener();
            DataListAdapter.removeALL(); // 把listview 內容 刪除
            getAllDatas();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        //
        //
        InitDB();
        InitView();
        InitAdapter();
        InitListener();
        DataListAdapter.removeALL(); // 把listview 內容 刪除
        //getAllDatas();
    }

    private void getAllDatas() {
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            Toast.makeText(Main2Activity.this, "Error\", \"Nothing found", Toast.LENGTH_LONG).show();
            return;
        }
        res.moveToFirst();
        DataListAdapter.addItem(new DBdata(res.getString(0), res.getString(1), res.getString(2)));
        while (res.moveToNext()) {
            DataListAdapter.addItem(new DBdata(res.getString(0),res.getString(1),res.getString(2)));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
