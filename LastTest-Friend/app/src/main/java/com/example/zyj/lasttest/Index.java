package com.example.zyj.lasttest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.network.zhouwei.http_network.Comment;
import com.network.zhouwei.http_network.Server;

import java.util.List;


public class Index extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        TextView name = (TextView) findViewById(R.id.name_text);
        name.setText(bundle.getString("name"));
        Button back = (Button) findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Index.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Button publish = (Button) findViewById(R.id.photo_button);
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Index.this, Publish.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", ((TextView) findViewById(R.id.name_text)).getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Server server = new Server();
        server.setServer("119.29.60.170");
        ImageView tx_img = ((ImageView) findViewById(R.id.tx_img));
        tx_img.setImageBitmap(server.imageFileDownload(name.getText().toString()));
        List<Comment> comments = server.getComments(0, 100);
        Adapter adapter = new Adapter(this, R.layout.friend_listview, comments);
        ListView listView = ((ListView) findViewById(R.id.listview));
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_index, menu);
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
