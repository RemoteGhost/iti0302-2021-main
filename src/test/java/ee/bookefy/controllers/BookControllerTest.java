package ee.bookefy.controllers;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ee.bookefy.models.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getBookByIdReturnsBook() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/books/1"))
                .andExpect(status().isOk()).andReturn();
        String bookAsString = mvcResult.getResponse().getContentAsString();
        Book book = objectMapper.readValue(bookAsString, new TypeReference<>() {
        });
        assertEquals("Moby Dick", book.getTitle());
        assertThat(book.getAuthor()).isEqualTo("Herman Melville");
    }

    @Test
    void getAllBooksNoContentReturnsAllBooks() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/books"))
                .andExpect(status().isOk())
                .andReturn();
        String bookAsString = mvcResult.getResponse().getContentAsString();
        List<Book> books = objectMapper.readValue(bookAsString, new TypeReference<>() {
        });
        assertEquals(4, books.size());
        assertEquals("Moby Dick", books.get(0).getTitle());
        assertEquals("Dracula", books.get(1).getTitle());
        assertTrue(books.stream().anyMatch(book -> book.getTitle().equals("The Picture of Dorian Gray")));

    }
}