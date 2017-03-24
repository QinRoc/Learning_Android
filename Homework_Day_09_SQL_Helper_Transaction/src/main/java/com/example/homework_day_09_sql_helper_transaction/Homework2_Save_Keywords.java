package com.example.homework_day_09_sql_helper_transaction;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Homework2_Save_Keywords extends AppCompatActivity {

    private EditText searchKey;
    private LinearLayout searchHistory;
    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<Key> keys;
    private TextView searchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework2__save__keywords);

        Log.i("Save Keywords", "oncreate run i");

        searchKey = (EditText) findViewById(R.id.searchKey);
        searchHistory = (LinearLayout) findViewById(R.id.searchHistory);
        searchResult = (TextView) findViewById(R.id.searchResult);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        keys = new ArrayList<>();

        openDB();

        searchKey.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Log.i("onFocusChange", "on focus");
                    //Toast.makeText(Homework2_Save_Keywords.this, "onFocus", Toast.LENGTH_SHORT).show();
                    showSearchHistory();
                }
            }
        });

        /*searchKey.setKeyListener(new KeyListener() {
            @Override
            public int getInputType() {
                return 0;
            }

            @Override
            public boolean onKeyDown(View view, Editable text, int keyCode, KeyEvent event) {
                return false;
            }

            @Override
            public boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
                return false;
            }

            @Override
            public boolean onKeyOther(View view, Editable text, KeyEvent event) {
                return false;
            }

            @Override
            public void clearMetaKeyState(View view, Editable content, int states) {

            }
        });
        searchKey.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return false;
            }
        });

        searchKey.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }
        });*/
        //showSearchHistory();
    }

    public SQLiteDatabase openDB() {
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(getFilesDir() + "/searchKeys.db", null);
        Log.i("openDB", "created db");

        //String createDB = "create table if not exists keys (id int primary key auto_increment, key varchar(200) not null);";
        String createDB = "create table if not exists keys (id integer primary key autoincrement, key varchar(200) not null);";
        sqLiteDatabase.execSQL(createDB);
        Log.i("openDB", "created table");

        return sqLiteDatabase;
    }

    public void search(View view) {
        Log.i("search", "to search");

        searchKey.clearFocus();
        searchHistory.removeAllViews();

        String key = searchKey.getText().toString();
        if (key != null && !"".equals(key)) {
            //String addKey = "insert into keys (?)";
            //String addKey = "insert into keys values (?)";
            //String addKey = "insert into keys values (,?)";
            String addKey = "insert into keys (key) values (?)";
            sqLiteDatabase.execSQL(addKey, new Object[]{key});
        }
        searchResult.setText(key + "搜索结果");
        searchResult.setClickable(true);
        //searchResult.setContextClickable(true);
        searchResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchKey.clearFocus();
                searchHistory.removeAllViews();
            }
        });
    }

    public void showSearchHistory() {
        Log.i("showSearchHistory", "to show history");

        searchHistory.removeAllViews();
        keys.removeAll(keys);

        TextView aView = new TextView(this);
        aView.setText("搜索历史");
        searchHistory.addView(aView);

        String getHistory = "select * from keys";

        if (sqLiteDatabase != null) {
            Cursor cursor = sqLiteDatabase.rawQuery(getHistory, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String keyWord = cursor.getString(1);

                Key key = new Key(id, keyWord);
                keys.add(key);
            }

            for (Key key : keys) {
                TextView textView = new TextView(this);
                textView.setText(key.toString());
                searchHistory.addView(textView);
            }
        }

    }
}

class Key {
    private int id;
    private String key;

    public Key() {
    }

    public Key(int id, String key) {
        this.id = id;
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Key{" +
                "id=" + id +
                ", key='" + key + '\'' +
                '}';
    }
}
