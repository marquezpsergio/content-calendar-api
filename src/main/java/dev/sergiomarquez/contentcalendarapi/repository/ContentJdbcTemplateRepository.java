package dev.sergiomarquez.contentcalendarapi.repository;

import dev.sergiomarquez.contentcalendarapi.model.Content;
import dev.sergiomarquez.contentcalendarapi.model.Status;
import dev.sergiomarquez.contentcalendarapi.model.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentJdbcTemplateRepository {

    private final JdbcTemplate jdbcTemplate;

    public ContentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Content mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Content(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getObject("status", Status.class),
                rs.getObject("contentType", Type.class),
                rs.getTimestamp("dateCreated").toLocalDateTime(),
                rs.getTimestamp("dateUpdated").toLocalDateTime(),
                rs.getString("url")
        );
    }

    public List<Content> findAll() {
        String sql = "SELECT * FROM Content";

        return jdbcTemplate.query(sql, ContentJdbcTemplateRepository::mapRow);
    }

    public Optional<Content> findById(Integer id) {
        String sql = "SELECT * FROM Content WHERE id = ?";

        return jdbcTemplate.query(sql, new Object[]{id}, ContentJdbcTemplateRepository::mapRow).stream().findFirst();
    }

    public void save(Content content) {
        String sql = "INSERT INTO Content (title, desc, status, contentType, dateCreated, dateUpdated, url) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, content.title(), content.description(), content.status(), content.contentType(), content.dateCreated(), content.dateUpdated(), content.url());
    }

    public void update(Content content) {
        String sql = "UPDATE Content SET title = ?, desc = ?, status = ?, contentType = ?, dateCreated = ?, dateUpdated = ?, url = ? " +
                "WHERE id = ?";

        if (!findById(content.id()).isPresent()) {
            throw new IllegalArgumentException("Content not found");
        }
        jdbcTemplate.update(sql, content.title(), content.description(), content.status(), content.contentType(), content.dateCreated(), content.dateUpdated(), content.url(), content.id());
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM Content WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }


}
