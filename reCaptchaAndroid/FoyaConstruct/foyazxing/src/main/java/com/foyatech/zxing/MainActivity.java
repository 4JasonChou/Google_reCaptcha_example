package com.foyatech.zxing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.foyatech.zxing.ericssonlabs.BarCodeTestActivity;
import com.foyatech.zxing.zxing.activity.CaptureActivity;

public class MainActivity extends AppCompatActivity {

    private Button mB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mB = (Button) findViewById(R.id.buttonScan);

        /* Example : 利用 mbOnClick 使用 startActivityForResult 呼叫 Scan.class , 以 onActivityResult 取回掃描資料 */
        mB.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCameraIntent = new Intent(MainActivity.this, Scan.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            Bundle bundle = data.getExtras();
            /*  決定回傳資料的 key value,可設定於 zxing.activity.CaptureActivity中 */
            String scanResult = bundle.getString("result");

            if(scanResult!=null) {
                Log.d("Goto", scanResult.toString());
            }
            else
                Log.d("Goto", "Empty" );
        }

    }
}
