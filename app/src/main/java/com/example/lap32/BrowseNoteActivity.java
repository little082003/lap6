package com.example.lap32;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BrowseNoteActivity extends AppCompatActivity {

    private ProgressBar progress;
    private TextView tvResult;
    private Button btnSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_browse_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ค้นหา View components จาก layout
        progress = findViewById(R.id.progressBar1);
        tvResult = findViewById(R.id.tvResult);
        btnSearch = findViewById(R.id.btnSearch);

        // ซ่อน ProgressBar และ TextView ผลลัพธ์ในตอนเริ่มต้น
        progress.setVisibility(View.GONE);
        tvResult.setVisibility(View.GONE);


        // ตั้งค่า Event Listener ให้กับปุ่มค้นหา
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // --- เริ่มการทำงานตามโจทย์ ---

                // 1. แสดง ProgressBar และซ่อนปุ่มกับผลลัพธ์เก่า
                progress.setVisibility(View.VISIBLE);
                tvResult.setVisibility(View.GONE);
                btnSearch.setEnabled(false); // ปิดการใช้งานปุ่มชั่วคราว

                // 2. สร้างและเริ่ม Thread ใหม่เพื่อจำลองการโหลดข้อมูล
                new Thread(() -> {
                    try {
                        // 3. ดีเลย์การทำงานเป็นเวลา 2 วินาที (2000 มิลลิวินาที)
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 4. กลับมาทำงานที่ UI Thread เพื่ออัปเดตหน้าจอ
                    runOnUiThread(() -> {
                        // 5. ซ่อน ProgressBar
                        progress.setVisibility(View.GONE);

                        // 6. แสดงข้อความ "ไม่พบข้อมูล" ใน TextView
                        tvResult.setText("ไม่พบข้อมูล");
                        tvResult.setVisibility(View.VISIBLE);

                        // เปิดใช้งานปุ่มอีกครั้ง
                        btnSearch.setEnabled(true);
                    });
                }).start(); // สั่งให้ Thread เริ่มทำงาน
            }
        });
    }
}