package com.marcinwo.youtubeapi.demo.controller;

import com.marcinwo.youtubeapi.demo.JsonUtils;
import com.marcinwo.youtubeapi.demo.dto.FilmDTO;
import com.marcinwo.youtubeapi.demo.entity.Category;
import com.marcinwo.youtubeapi.demo.entity.Channel;
import com.marcinwo.youtubeapi.demo.entity.Comment;
import com.marcinwo.youtubeapi.demo.entity.Film;
import com.marcinwo.youtubeapi.demo.mapper.FilmMapper;
import com.marcinwo.youtubeapi.demo.service.FilmService;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FilmController.class)
public class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmService filmService;

    @MockBean
    private FilmMapper filmMapper;

    @Test
    public void getFilmsTest() throws Exception {//ok
        List<Film> films = List.of(
                new Film(LocalDateTime.now(), "Wsciekle piesci weza1", "Film akcji", "www.omg.pl",
                        233, new Channel(), Set.of(new Comment()), new Category()),
                new Film(LocalDateTime.now(), "Wsciekle piesci weza2", "Film akcji", "www.omg.pl",
                        233, new Channel(), Set.of(new Comment()), new Category())
        );

        List<FilmDTO> filmDTOS = List.of(
                new FilmDTO("Sarnie zniwo1", "Horror", "www.dej.com", 333, "Testowiron"),
                new FilmDTO("Sarnie zniwo2", "Horror", "www.dej2.com", 34, "Testowiron")
        );

        when(filmService.getFilms()).thenReturn(films);
        when(filmMapper.toFilmDTO(anyCollection())).thenReturn(filmDTOS);

        mockMvc.perform(get("/films"))
                .andDo(print())
                .andExpect(content().string(CoreMatchers.containsString("Horror")))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtils.toJsonString(filmDTOS)));
    }


}
