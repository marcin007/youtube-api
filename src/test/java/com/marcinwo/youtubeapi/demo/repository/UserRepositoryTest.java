package com.marcinwo.youtubeapi.demo.repository;

import com.marcinwo.youtubeapi.demo.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void beforeEach() {
        userRepository.deleteAll();
    }

    @Test
    public void whenFindByUserName_thenReturnUser() {
        // given
        User user = new User("Jacek", "Adamowski",
                "jadamowski", "1234",
                new HashSet<>(), new HashSet<>(), new HashSet<>());
        entityManager.persist(user);
        entityManager.flush();

        // when
        String username = "jadamowski";
        Optional<User> optionalUser = userRepository.findByUserName(username);

        // then
        assertThat(optionalUser.isPresent()).isTrue();
        assertThat(optionalUser.get().getLastName()).isEqualTo(user.getLastName());
    }

    @Test
    public void whenFindAllByLastName_thenReturnUsers() {
        User user1 = new User("Jacek1", "Adamowski1", "jadamowski1", "1234",
                new HashSet<>(), new HashSet<>(), new HashSet<>());
        User user2 = new User("Jacek2", "Adamowski1", "jadamowski2", "1234",
                new HashSet<>(), new HashSet<>(), new HashSet<>());

        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.flush();

        String lastname = "Adamowski1";
        List<User> users = userRepository.findAllByLastName(lastname);

        assertThat(users.size() == 2).isTrue();
        assertThat(users.get(0).getFirstName()).isEqualTo(user1.getFirstName());
        assertThat(users.get(1).getFirstName()).isEqualTo(user2.getFirstName());
    }

    @Test//ok
    public void given_userNotExist_when_findByUserName_then_returnEmptyOptional(){
        Optional<User> user = userRepository.findByUserName("username");

        assertThat(user).isEmpty();
    }

    @Test//ok
    public void given_usersNotExist_when_findAllByLastName_then_returnEmptyUserList(){
        List<User> user = userRepository.findAllByLastName("LastName");

        assertThat(user).isEmpty();
    }
}
