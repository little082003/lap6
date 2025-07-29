package com.example.lap32;

import java.util.List;

public class CheckListNote extends Note {

    private List<String> items;

    // ปรับปรุง: คอนสตรัคเตอร์รับ BaseUser
    public CheckListNote(String title, String context, List<String> items, BaseUser owner) {
        super(title, context, owner);
        this.items = items;
    }

    @Override
    public String display() {
        // ปรับปรุง: ส่งคืน String ที่จัดรูปแบบและสร้างรายการตรวจสอบ
        StringBuilder itemsStr = new StringBuilder();
        for (String item : items) {
            itemsStr.append("- ").append(item).append("\n");
        }

        return "--- Checklist Note ---\n" +
                "Owner: " + owner.getUsername() + "\n" +
                "Title: " + title + "\n" +
                "Context: " + context + "\n" +
                itemsStr.toString() +
                "Created: " + getFormattedDate();
    }
}