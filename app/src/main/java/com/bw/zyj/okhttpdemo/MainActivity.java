package com.bw.zyj.okhttpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        postObj();
        //postJson();
        //postKey();
        //getEnqueue();
        //getExecute();
    }

    private void postObj() {
        RequestParameter req =new RequestParameter();
        req.page = "1";
        req.rows = "20";

        OkHttpUtils.post("http://www.tngou.net/api/cook/list",
                req, new Callback(){

                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String result = response.body().string();
                        tv.post(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText(result);
                            }
                        });
                    }
                });
    }

    private void postJson() {
        String json ="{\"page\":\"1\",\"rows\":\"20\"}";
        OkHttpUtils.post("http://www.tngou.net/api/cook/list",
                json, new Callback(){

                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String result = response.body().string();
                        tv.post(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText(result);
                            }
                        });
                    }
                });
    }

    private void postKey() {
        Map<String,String> map =new HashMap<>();
        map.put("page","1");
        map.put("rows","20");

        OkHttpUtils.post("http://www.tngou.net/api/cook/list",
                map, new Callback(){

                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String result = response.body().string();
                        tv.post(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText(result);
                            }
                        });
                    }
                });
    }

    private void getEnqueue() {
        OkHttpUtils.get("http://www.baidu.com",
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String result = response.body().string();
                        tv.post(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText(result);
                            }
                        });
                    }
                });
    }

    private void getExecute() {
        new Thread(){
            @Override
            public void run() {
                final String result = OkHttpUtils.get("http://www.baidu.com");
                tv.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(result);
                    }
                });
            }
        }.start();
    }

    private void initView() {
        tv = (TextView)findViewById(R.id.tv);
    }
}
