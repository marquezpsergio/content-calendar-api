package dev.sergiomarquez.contentcalendarapi.repository;

import dev.sergiomarquez.contentcalendarapi.model.Content;
import dev.sergiomarquez.contentcalendarapi.model.Status;
import dev.sergiomarquez.contentcalendarapi.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {

    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository() {

    }

    @PostConstruct
    private void init() {
        Content content = new Content(1, "My First Blog", "My First Blog", Status.IDEA, Type.ARTICLE, LocalDateTime.now(), null, "https://www.google.com/my-first-blog");
        Content content2 = new Content(2, "My Second Blog", "My Second Blog", Status.IDEA, Type.ARTICLE, LocalDateTime.now(), null, "https://www.google.com/my-second-blog");
        Content content3 = new Content(3, "My Third Blog", "My Third Blog", Status.IDEA, Type.ARTICLE, LocalDateTime.now(), null, "https://www.google.com/my-third-blog");
        contentList.addAll(List.of(content, content2, content3));
    }

    public List<Content> findAll() {
        return contentList;
    }

    public Optional<Content> findById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    public boolean existsById(Integer id) {
        return contentList.stream().anyMatch(c -> c.id().equals(id));
    }

    public void save(Content content) {
        contentList.removeIf(c -> c.id().equals(content.id()));
        contentList.add(content);
    }


    public void delete(Integer id) {
        contentList.removeIf(c -> c.id().equals(id));
    }
}
