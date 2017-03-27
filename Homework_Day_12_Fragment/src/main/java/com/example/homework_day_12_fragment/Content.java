package com.example.homework_day_12_fragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 */
public class Content implements Serializable {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<ContentItem> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, ContentItem> ITEM_MAP = new HashMap<>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createContentItem(i));
        }
    }

    private static void addItem(ContentItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static ContentItem createContentItem(int position) {
        return new ContentItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {

        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }

        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class ContentItem implements Serializable {

        private String id;
        private String content;
        private String details;

        public ContentItem() {
        }

        public ContentItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        @Override
        public String toString() {
            return "ContentItem{" +
                    "id='" + id + '\'' +
                    ", content='" + content + '\'' +
                    ", details='" + details + '\'' +
                    '}';
        }
    }
}
