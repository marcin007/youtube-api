package com.marcinwo.youtubeapi.demo.repository;

import com.marcinwo.youtubeapi.demo.ExampleData;
import com.marcinwo.youtubeapi.demo.entity.Category;
import com.marcinwo.youtubeapi.demo.entity.Channel;
import com.marcinwo.youtubeapi.demo.entity.Film;
import com.marcinwo.youtubeapi.demo.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import static java.sql.Types.NULL;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class FilmRepositoryTest {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Before
    public void beforeEach() {
        filmRepository.deleteAll();
    }

    @Test
    public void given_channelHasFilms_when_findAllByChannelId_then_returnFilmList() {//todo ok??
        Channel myChannel = new Channel("aaa1", "ooo1", new User(), new HashSet<>());
        Film film1 = new Film(LocalDateTime.now(), "Sarnie zniwo", "Kino akjci", "www.qwe.pl", 22, myChannel, new HashSet<>(), new Category());
        Film film2 = new Film(LocalDateTime.now(), "Sarnie zniwo2", "Kino akjci2", "www.qwe.pl2", 222, myChannel, new HashSet<>(), new Category());

        testEntityManager.persistAndFlush(film1);
        testEntityManager.persistAndFlush(film2);

        List<Film> films = filmRepository.findAllByChannel_Id(1L);

        assertThat(films).isNotEmpty();
        assertThat(films).size().isEqualTo(2);
    }

    @Test
    public void given_channelHasNoFilms_when_findAllByChannelId_then_returnEmptyFilmList() {

        User myUser = ExampleData.user();
        Channel channel = new Channel(null, null, myUser, null);
        Film film = new Film(null, null, null, null, NULL, channel, null, null);

        testEntityManager.persistAndFlush(film);

        List<Film> films = filmRepository.findAllByChannel_Id(1L);

        assertThat(films).isEmpty();

    }

}
