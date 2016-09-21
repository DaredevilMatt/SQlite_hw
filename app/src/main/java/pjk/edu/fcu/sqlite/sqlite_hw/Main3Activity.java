package pjk.edu.fcu.sqlite.sqlite_hw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    foodDBHelper myDb;
    private EditText Nametextview,CaloriestextView;
    private Button Changebtn,Deletebtn;
    ArrayList<DBdata> dataList ;
    int position;

    MyBaseAdapter DataListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        InitView();
        GetIntent();
        setView();
        InitDB();
        InitListener();
    }

    //初始view
    public void InitView(){
        Nametextview = (EditText)findViewById(R.id.NameEditText);
        CaloriestextView = (EditText)findViewById(R.id.CaloriesEditText);
        Changebtn = (Button)findViewById(R.id.Changebtn);
        Deletebtn = (Button)findViewById(R.id.Deletebtn);
    }

    //初始資料庫
    public void InitDB(){
        myDb = new foodDBHelper(this);
    }

    //取得Intent的資料
    public void GetIntent(){
        Intent intent = getIntent();
        dataList = (ArrayList<DBdata>)intent.getSerializableExtra("KEY_CLASS");
        position = intent.getIntExtra("KEY_INDEX",1);
        Nametextview.setText(dataList.get(position).getName());
        CaloriestextView.setText(dataList.get(position).getCalories()+"");
    }

    //設定View的字
    public void setView(){
        Nametextview.setText(dataList.get(position).getName().toString());
        CaloriestextView.setText(dataList.get(position).getCalories()+"");
    }

    //初始監聽器
    public void InitListener(){
        Deletebtn.setOnClickListener(deleteListener);
        Changebtn.setOnClickListener(changeListener);
    }

    //刪除資料庫資料
    private View.OnClickListener deleteListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Integer detetedRows =myDb.deleteData(dataList.get(position).getId()+"");
            if(detetedRows > 0)
                Toast.makeText(Main3Activity.this, "Data Deleted", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(Main3Activity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
        }
    };

    //更改資料庫資料
    private View.OnClickListener changeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean isUpdate = myDb.updateData(dataList.get(position).getId()+"",Nametextview.getText().toString(),CaloriestextView.getText().toString());
            if(isUpdate == true)
                Toast.makeText(Main3Activity.this,"Data Update",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(Main3Activity.this,"Data not Updated",Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main3, menu);
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
