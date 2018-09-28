package com.example.tamirshklaz.spotify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static String URL = "http://192.168.4.1:5000/send";

    OkHttpClient okHttpClient = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    Button aButton;
    Button bButton;
    Button cButton;
    Button dButton;
    Button eButton;
    Button fButton;
    Button gButton;

    TextView volumeTV;
    TextView durationTV;

    SeekBar volumeSB;
    SeekBar durationSB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast toast = Toast.makeText(MainActivity.this, "Light", Toast.LENGTH_SHORT);
//                toast.show();
//                turnOnLed("http://192.168.4.1:5000/send", "{'note':'A'}", new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        e.printStackTrace();
//
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        Log.i("Network", response.body().toString());
//                    }
//                });
//
//
//            }
//        });
    }

    private void bindViews() {
        KeyClickListener keyClickListener = new KeyClickListener();

        aButton = findViewById(R.id.a_btn);
        bButton = findViewById(R.id.b_btn);
        cButton = findViewById(R.id.c_btn);
        dButton = findViewById(R.id.d_btn);
        eButton = findViewById(R.id.e_btn);
        fButton = findViewById(R.id.f_btn);
        gButton = findViewById(R.id.g_btn);

        aButton.setOnClickListener(keyClickListener);
        bButton.setOnClickListener(keyClickListener);
        cButton.setOnClickListener(keyClickListener);
        dButton.setOnClickListener(keyClickListener);
        eButton.setOnClickListener(keyClickListener);
        fButton.setOnClickListener(keyClickListener);
        gButton.setOnClickListener(keyClickListener);


        volumeTV = findViewById(R.id.volume_tv);
        durationTV = findViewById(R.id.duration_tv);

        durationSB = findViewById(R.id.duration_sb);
        volumeSB = findViewById(R.id.vol_sb);

        durationSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                durationTV.setText(i / 10.0 + " seconds");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        volumeSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                volumeTV.setText(i + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private class KeyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.a_btn:
                    sendNote("A", volumeSB.getProgress(), durationSB.getProgress()/10.0, this.callback);
                    break;
                case R.id.b_btn:
                    sendNote("B", volumeSB.getProgress(), durationSB.getProgress()/10.0, this.callback);

                    break;
                case R.id.c_btn:
                    sendNote("C", volumeSB.getProgress(), durationSB.getProgress()/10.0, this.callback);

                    break;
                case R.id.d_btn:
                    sendNote("D", volumeSB.getProgress(), durationSB.getProgress()/10.0, this.callback);

                    break;
                case R.id.e_btn:
                    sendNote("E", volumeSB.getProgress(), durationSB.getProgress()/10.0, this.callback);

                    break;
                case R.id.f_btn:
                    sendNote("F", volumeSB.getProgress(), durationSB.getProgress()/10.0, this.callback);

                    break;
                case R.id.g_btn:
                    sendNote("G", volumeSB.getProgress(), durationSB.getProgress()/10.0, this.callback);

                    break;
            }
        }

        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        };
    }


    private Call sendNote(String note, int volume, double duration, Callback callback) {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{'note':" + note + ", " +
                "'volume':" + volume + ", 'duration:' " + duration + "}");

        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "1ac26b2b-9496-4654-9025-733dc24af156")
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
        return call;

    }
}
