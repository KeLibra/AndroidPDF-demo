package cn.kelibra.demo.androidpdf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import cn.kelibra.demo.androidpdf.weight.KWebviewActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KWebviewActivity.class);
                intent.putExtra("h5_url", "https://vs-mall.oss-cn-shanghai.aliyuncs.com/mall-admin/%E5%90%9B%E5%AD%90%E7%AD%BE%E5%90%88%E5%90%8C.pdf");
//                intent.putExtra("h5_url", "http://www.baidu.com");
                startActivity(intent);
            }
        });
    }
}
