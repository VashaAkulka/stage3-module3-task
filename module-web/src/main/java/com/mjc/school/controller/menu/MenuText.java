package com.mjc.school.controller.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MenuText {
    ENTER_NUMBER_OF_OPERATION("Enter operation: "),
    ENTER_NEWS_ID("Enter id: "),
    ENTER_NEWS_TITLE("Enter title: "),
    ENTER_NEWS_CONTENT("Enter content: "),
    ENTER_TAGS_IDS("Enter tags ids: "),
    ENTER_TAG_ID("Enter tag id: "),
    ENTER_NEWS_AUTHOR_ID("Enter author id: "),
    ENTER_AUTHOR_NAME("Enter author name: "),
    ENTER_TAG_NAME("Enter tag name: "),
    ENTER_NEWS_IDS("Enter news ids: "),
    OPERATION("Operation: ");

    private final String text;
}
