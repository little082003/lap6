package com.example.lap32;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActivityAddNote extends AppCompatActivity {

    private Spinner spinnerUser;
    private TextView tvResult;
    private List<BaseUser> userList;
    private EditText etNoteTitle, etTextContent, etChecklistContent;
    private RadioGroup rgNoteType;
    private Button btnSaveNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ... (โค้ด Boilerplate) ...
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // 1. ค้นหา Views
        initializeViews();

        // 2. สร้างข้อมูล User ตัวอย่างและใส่ใน Spinner
        createSampleUsers();
        populateUserSpinner();

        // 3. ตั้งค่า Listener สำหรับปุ่มบันทึก
        btnSaveNote.setOnClickListener(v -> saveNote());

        // ... (Listener อื่นๆ) ...
    }

    private void initializeViews() {
        spinnerUser = findViewById(R.id.spinnerUser);
        tvResult = findViewById(R.id.tvResult);
        btnSaveNote = findViewById(R.id.btnSaveNote);
        etNoteTitle = findViewById(R.id.etNoteTitle);
        etTextContent = findViewById(R.id.etTextContent);
        etChecklistContent = findViewById(R.id.etChecklistContent);
        rgNoteType = findViewById(R.id.rgNoteType);
    }

    // สร้างข้อมูล User เพื่อใช้เป็นตัวอย่าง
    private void createSampleUsers() {
        userList = new ArrayList<>();
        userList.add(new RegularUser("jam", "jam@gmail.com", "pass123"));
        userList.add(new AdminUser("som", "som.admin@gmail.com", "adminpass", "Full Access"));
    }

    // นำรายชื่อ User ไปแสดงใน Spinner
    private void populateUserSpinner() {
        List<String> userNames = new ArrayList<>();
        for (BaseUser user : userList) {
            userNames.add(user.getUsername());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, userNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUser.setAdapter(adapter);
    }

    // เมธอดสำหรับบันทึกข้อมูล
    private void saveNote() {
        String title = etNoteTitle.getText().toString().trim();

        // --- ส่วนสำคัญ: ดึงข้อมูล User ที่ถูกเลือกจาก Spinner ---
        int selectedUserPosition = spinnerUser.getSelectedItemPosition();
        BaseUser selectedUser = userList.get(selectedUserPosition); // ได้ออบเจ็กต์ User ทั้งหมด

        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter a title.", Toast.LENGTH_SHORT).show();
            return;
        }

        Note newNote;
        int selectedNoteTypeId = rgNoteType.getCheckedRadioButtonId();

        if (selectedNoteTypeId == R.id.rbTextNote) {
            String content = etTextContent.getText().toString().trim();
            // สร้าง TextNote พร้อมส่งข้อมูล User ที่เลือกไปด้วย
            newNote = new TextNote(title, content, selectedUser);
        } else { // rbCheckListNote
            String checklistRaw = etChecklistContent.getText().toString().trim();
            List<String> items = new ArrayList<>(Arrays.asList(checklistRaw.split("\\s*\\n\\s*")));
            // สร้าง CheckListNote พร้อมส่งข้อมูล User ที่เลือกไปด้วย
            newNote = new CheckListNote(title, "Checklist items:", items, selectedUser);
        }

        // --- แสดงผลลัพธ์: ซึ่งตอนนี้จะมีข้อมูล User รวมอยู่ด้วย ---
        tvResult.setText(newNote.display());
        Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show();
    }
}