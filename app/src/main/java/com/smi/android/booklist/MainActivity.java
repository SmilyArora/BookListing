package com.smi.android.booklist;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private BookAdapter mAdapter;
    private ConnectivityManager manager;
    final String BOOKS_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        Button b1 = (Button) findViewById(R.id.search_button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView view = (TextView) findViewById(R.id.emptyList);
                if (manager.getActiveNetworkInfo() == null) {
                    view.setText("No internet Connection");
                    view.setVisibility(View.VISIBLE);
                } else {
                    view.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG).show();
                    EditText edit = (EditText) findViewById(R.id.editText);
                    //sewing url from sub strings
                    String nameOfBook = edit.getText().toString();
                    String url = BOOKS_URL + nameOfBook + "&maxResults=10"; //reduce time by limiting no. of responses
                    //async retrival of data and display
                    BookAsyncTask task = new BookAsyncTask();
                    task.execute(url);
                }

            }
        });

        ListView view = (ListView) findViewById(R.id.list);
        mAdapter = new BookAdapter(this, new ArrayList<Book>());
        view.setAdapter(mAdapter);
    }

    private class BookAsyncTask extends AsyncTask<String, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            List<Book> result = QueryUtils.fetchBookData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<Book> data) {
            mAdapter.clear();
            if (data.size() == 0) {
                TextView view = (TextView) findViewById(R.id.emptyList);
                view.setText("No results found");
                view.setVisibility(View.VISIBLE);
                return;
            }
            mAdapter.addAll(data);


        }

    }
}


