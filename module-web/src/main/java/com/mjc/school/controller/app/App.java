package com.mjc.school.controller.app;

import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.controller.impl.TagController;
import com.mjc.school.controller.menu.MenuOption;
import com.mjc.school.controller.menu.MenuText;
import com.mjc.school.service.dto.AuthorDTO;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.dto.TagDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class App {
    private final AuthorController authorController;
    private final NewsController newsController;
    private final TagController tagController;
    Scanner scanner = new Scanner(System.in);

    public void startApp() {
        while (true) {
            for (MenuOption options: MenuOption.values()) {
                System.out.println(options.getOptionCode() + " - " + options.getOptionName());
            }

            System.out.print(MenuText.ENTER_NUMBER_OF_OPERATION.getText());
            switch (scanner.nextLine()) {
                case "1" -> viewAllNews();
                case "2" -> viewsNewsById();
                case "3" -> viewsNewsByParameters();
                case "4" -> addNews();
                case "5" -> editNews();
                case "6" -> deleteNews();
                case "7" -> viewAllAuthor();
                case "8" -> viewsAuthorById();
                case "9" -> viewsAuthorByNewsId();
                case "10" -> addAuthor();
                case "11" -> editAuthor();
                case "12" -> deleteAuthor();
                case "13" -> viewAllTag();
                case "14" -> viewsTagById();
                case "15" -> viewsTagByNewsId();
                case "16" -> addTag();
                case "17" -> editTag();
                case "18" -> deleteTag();
                case "0" -> System.exit(0);
                default -> System.out.println("Invalid Operation");
            }
        }
    }

    private void addNews() {
        NewsDTO newsDTO = new NewsDTO();

        System.out.print(MenuText.ENTER_NEWS_TITLE.getText());
        newsDTO.setTitle(scanner.nextLine());
        System.out.print(MenuText.ENTER_NEWS_CONTENT.getText());
        newsDTO.setContent(scanner.nextLine());
        System.out.print(MenuText.ENTER_NEWS_AUTHOR_ID.getText());
        newsDTO.setAuthorId(scanner.nextLong());
        System.out.print(MenuText.ENTER_TAGS_IDS.getText());

        scanner.nextLine();
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            newsDTO.setTagsId(new HashSet<>());
        } else {
            Set<Long> setLong = Arrays.stream(input.split(" "))
                    .map(Long::parseLong).collect(Collectors.toSet());
            newsDTO.setTagsId(setLong);
        }

        newsController.create(newsDTO);
    }

    private void addAuthor() {
        AuthorDTO authorDTO = new AuthorDTO();

        System.out.print(MenuText.ENTER_AUTHOR_NAME.getText());
        authorDTO.setName(scanner.next());

        scanner.nextLine();
        authorController.create(authorDTO);
    }

    private void addTag() {
        TagDTO tagDTO = new TagDTO();

        System.out.print(MenuText.ENTER_TAG_NAME.getText());
        tagDTO.setName(scanner.next());
        System.out.print(MenuText.ENTER_NEWS_IDS.getText());

        scanner.nextLine();
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            tagDTO.setNewsId(new HashSet<>());
        } else {
            Set<Long> setLong = Arrays.stream(input.split(" "))
                    .map(Long::parseLong).collect(Collectors.toSet());
            tagDTO.setNewsId(setLong);
        }

        tagController.create(tagDTO);
    }

    private void editNews() {
        NewsDTO newsDTO = new NewsDTO();

        System.out.print(MenuText.ENTER_NEWS_TITLE.getText());
        newsDTO.setTitle(scanner.nextLine());
        System.out.print(MenuText.ENTER_NEWS_CONTENT.getText());
        newsDTO.setContent(scanner.nextLine());
        System.out.print(MenuText.ENTER_NEWS_AUTHOR_ID.getText());
        newsDTO.setAuthorId(scanner.nextLong());
        System.out.print(MenuText.ENTER_TAGS_IDS.getText());

        String input = scanner.nextLine();
        Set<Long> setLong = Arrays.stream(input.split(" "))
                .map(Long::parseLong).collect(Collectors.toSet());
        newsDTO.setTagsId(setLong);

        System.out.print(MenuText.ENTER_NEWS_ID.getText());
        Long id = scanner.nextLong();

        scanner.nextLine();
        newsController.update(newsDTO, id);
    }

    private void editAuthor() {
        AuthorDTO authorDTO = new AuthorDTO();

        System.out.print(MenuText.ENTER_AUTHOR_NAME.getText());
        authorDTO.setName(scanner.next());
        System.out.print(MenuText.ENTER_NEWS_AUTHOR_ID.getText());
        Long id = scanner.nextLong();

        scanner.nextLine();
        authorController.update(authorDTO, id);
    }

    private void editTag() {
        TagDTO tagDTO = new TagDTO();

        System.out.print(MenuText.ENTER_TAG_NAME.getText());
        tagDTO.setName(scanner.next());
        System.out.print(MenuText.ENTER_NEWS_IDS.getText());

        String input = scanner.nextLine();
        Set<Long> setLong = Arrays.stream(input.split(" "))
                .map(Long::parseLong).collect(Collectors.toSet());
        tagDTO.setNewsId(setLong);

        System.out.print(MenuText.ENTER_TAG_ID.getText());
        Long id = scanner.nextLong();

        scanner.nextLine();
        tagController.update(tagDTO, id);
    }

    private void deleteNews() {
        System.out.print(MenuText.ENTER_NEWS_ID.getText());
        long id = scanner.nextLong();
        scanner.nextLine();
        newsController.deleteById(id);
    }

    private void deleteAuthor() {
        System.out.print(MenuText.ENTER_NEWS_AUTHOR_ID.getText());
        long id = scanner.nextLong();
        scanner.nextLine();
        authorController.deleteById(id);
    }

    private void deleteTag() {
        System.out.print(MenuText.ENTER_TAG_ID.getText());
        long id = scanner.nextLong();
        scanner.nextLine();
        tagController.deleteById(id);
    }


    private void viewAllNews() {
        newsController.readAll();
    }

    private void viewsNewsById() {
        System.out.print(MenuText.ENTER_NEWS_ID.getText());
        long id = scanner.nextLong();
        scanner.nextLine();
        newsController.readById(id);
    }

    private void viewAllAuthor() {
        authorController.readAll();
    }

    private void viewsAuthorById() {
        System.out.print(MenuText.ENTER_NEWS_AUTHOR_ID.getText());
        long id = scanner.nextLong();
        scanner.nextLine();
        authorController.readById(id);
    }

    private void viewAllTag() {
        tagController.readAll();
    }

    private void viewsTagById() {
        System.out.print(MenuText.ENTER_TAG_ID.getText());
        long id = scanner.nextLong();
        scanner.nextLine();
        tagController.readById(id);
    }

    private void viewsTagByNewsId() {
        System.out.print(MenuText.ENTER_NEWS_ID.getText());
        long newsId = scanner.nextLong();
        scanner.nextLine();
        tagController.getTagsByNewId(newsId);
    }

    private void viewsAuthorByNewsId() {
        System.out.print(MenuText.ENTER_NEWS_ID.getText());
        long newsId = scanner.nextLong();
        scanner.nextLine();
        authorController.getAuthorByNewsId(newsId);
    }

    private void viewsNewsByParameters() {
        System.out.print(MenuText.ENTER_TAG_NAME.getText());
        String tagName = scanner.next();
        System.out.print(MenuText.ENTER_TAG_ID.getText());
        String tagId = scanner.next();
        System.out.print(MenuText.ENTER_AUTHOR_NAME.getText());
        String authorName = scanner.next();
        System.out.print(MenuText.ENTER_NEWS_TITLE.getText());
        String title = scanner.nextLine();
        System.out.print(MenuText.ENTER_NEWS_CONTENT.getText());
        String content = scanner.nextLine();

        scanner.nextLine();
        newsController.getNewsByParameters(tagName.isEmpty() ? null : tagName,
                tagId.isEmpty() ? null : Long.parseLong(tagId),
                authorName.isEmpty() ? null : authorName,
                title.isEmpty() ? null : title,
                content.isEmpty() ? null : content);
    }
}
