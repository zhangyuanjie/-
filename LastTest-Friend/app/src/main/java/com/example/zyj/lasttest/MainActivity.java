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
import android.widget.ImageView;
import android.widget.Toast;

import com.network.zhouwei.http_network.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button register_button = (Button) findViewById(R.id.regist_button);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity_regist.class);
                startActivity(intent);
            }
        });
        Button login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            String name = ((EditText) findViewById(R.id.name_edit)).getText().toString();
                            String passwd = ((EditText) findViewById(R.id.pwd_edit)).getText().toString();
                            URL url = new URL("http://119.29.60.170/index.aspx?type=login&username=" + name + "&password=" + passwd);
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
                            if (stringBuffer.toString().equals("login success")) {
                                Toast.makeText(getApplicationContext(), "登入成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, Index.class);
                                Bundle bundle = new Bundle();
                                EditText userName = (EditText) findViewById(R.id.name_edit);
                                bundle.putString("name", userName.getText().toString());
                                intent.putExtras(bundle);
                                startActivity(intent);
                            } else if (stringBuffer.toString().equals("login failed")) {
                                Toast.makeText(getApplicationContext(), "登入失败，请重新登录！", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "请填写完全信息！", Toast.LENGTH_SHORT).show();
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

    public void otherButton(View view) {
        Toast.makeText(getApplicationContext(), "该功能尚未实现，请继续关注以后版本！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
