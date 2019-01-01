package com.example.zyj.lasttest;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity_regist extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_regist);
        Button regist = (Button) findViewById(R.id.regist_button);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            String name = ((EditText) findViewById(R.id.name_edit)).getText().toString();
                            String passwd = ((EditText) findViewById(R.id.pwd_edit)).getText().toString();
                            String email = ((EditText) findViewById(R.id.email_edit)).getText().toString();
                            String phone = ((EditText) findViewById(R.id.phone_edit)).getText().toString();
                            URL url = new URL("http://119.29.60.170/index.aspx?type=regist&username=" + name + "&password=" + passwd + "&email=" + email + "&phone=" + phone);
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.setRequestMethod("GET");
                            InputStream inputStream = httpURLConnection.getInputStream();
                            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,
                                    "utf-8");
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            StringBuffer stringBuffer = new StringBuffer();
                            String temp;
                            while ((temp = bufferedReader.readLine()) != null) {
                                stringBuffer.append(temp);
                            }
                            Looper.prepare();
                            if (stringBuffer.toString().equals("regist success")) {
                                Toast.makeText(getApplicationContext(), "注册成功！", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity_regist.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "请正确填写完全部信息！或者部分信息已被注册！", Toast.LENGTH_SHORT).show();
                            }
                            Looper.loop();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }

    public void back(View view) {
        Intent intent = new Intent(MainActivity_regist.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_regist, menu);
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
