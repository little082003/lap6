package com.example.lap32;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class Note {
    protected String title;
    protected String context;
    protected Date createdDate;
    protected BaseUser owner; // เพิ่ม: field สำหรับเก็บข้อมูลเจ้าของ

    // ปรับปรุง: เพิ่ม BaseUser ในคอนสตรัคเตอร์
    public Note(String title, String context, BaseUser owner) {
        this.title = title;
        this.context = context;
        this.owner = owner;
        this.createdDate = new Date();
    }

    // ... (getters/setters) ...

    public BaseUser getOwner() {
        return owner;
    }

    protected String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        return sdf.format(createdDate);
    }

    // ปรับปรุง: เปลี่ยนเป็น abstract String เพื่อให้ส่งคืนข้อความสำหรับ UI
    public abstract String display();
}