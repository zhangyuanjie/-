package com.example.zyj.lasttest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.network.zhouwei.http_network.Server;


public class Publish extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        final String name;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        name = bundle.getString("name");
        Button back = (Button) findViewById(R.id.cancel_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Publish.this.finish();
            }
        });
        Button publish = (Button) findViewById(R.id.publish_button);
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Server server = new Server();
                server.setServer("119.29.60.170");
                Bitmap[] bitmaps = {

                };
                String words = ((EditText) findViewById(R.id.edit_text)).getText().toString();
                server.commnetResourcesUpload(name, words, bitmaps);
                Toast.makeText(getApplicationContext(), "发表成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Publish.this, Index.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void otherButton(View view) {
        Toast.makeText(getApplicationContext(), "该功能尚未实现，请继续关注以后版本！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_publish, menu);
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
