package com.example.lap32;

public class TextNote extends Note {

    // คอนสตรัคเตอร์รับ BaseUser จากคลาสแม่
    public TextNote(String title, String context, BaseUser owner) {
        super(title, context, owner);
    }

    @Override
    public String display() {
        // ปรับปรุง: ส่งคืน String ที่มีข้อมูลของเจ้าของ
        return "--- Text Note ---\n" +
                "Owner: " + owner.getUsername() + "\n" + // แสดงชื่อผู้ใช้ของเจ้าของ
                "Title: " + title + "\n" +
                "Content: " + context + "\n" +
                "Created: " + getFormattedDate();
    }
}