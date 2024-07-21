package dev.sergiomarquez.contentcalendarapi;

import dev.sergiomarquez.contentcalendarapi.model.Content;
import dev.sergiomarquez.contentcalendarapi.model.Status;
import dev.sergiomarquez.contentcalendarapi.model.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        ResponseEntity<String> response = restTemplate.getForEntity("/", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testContentCollectionController() {
        // Crear un nuevo contenido
        Content content = new Content(99, "Test Title", "Test Description", Status.COMPLETED, Type.ARTICLE, LocalDateTime.now(), null, "http://example.com");
        ResponseEntity<Content> createResponse = restTemplate.postForEntity("/content/collection/new", content, Content.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // Obtener el contenido recien creado
        Integer contentId = content.id();
        ResponseEntity<Content> getResponse = restTemplate.getForEntity("/content/collection/{id}", Content.class, contentId);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(getResponse.getBody()).title()).isEqualTo("Test Title");

        // Actualizar el contenido
        Content updatedContent = new Content(contentId, "Updated Title", "Updated Description", Status.COMPLETED, Type.ARTICLE, LocalDateTime.now(), LocalDateTime.now(), "http://example.com");
        restTemplate.put("/content/collection/edit/{id}", updatedContent, contentId);

        // Verificar que se haya actualizado correctamente
        ResponseEntity<Content> updatedGetResponse = restTemplate.getForEntity("/content/collection/{id}", Content.class, contentId);
        assertThat(updatedGetResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(updatedGetResponse.getBody()).title()).isEqualTo("Updated Title");

        // Eliminar el contenido
        restTemplate.delete("/content/collection/delete/{id}", contentId);

        // Verificar que se haya eliminado correctamente sin intentar deserializar
        ResponseEntity<String> deletedGetResponse = restTemplate.getForEntity("/content/collection/{id}", String.class, contentId);
        assertThat(deletedGetResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
