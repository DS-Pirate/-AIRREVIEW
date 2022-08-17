package ds.pirate.backend.repository;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.pirate.backend.entity.airUser;

@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    UserRepository urepo;

    @Test
    public void insertAccounts(){
        IntStream.rangeClosed(1, 50).forEach(i->{
            airUser entity = airUser.builder()
                                    .passwd("1")
                                    .eMail("aaa@aaa.com")
                                    .airName("airName"+i)
                                    .phone(12349876)
                                    .birthDay(LocalDateTime.now())
                                    .gender(1)
                                    .auth("user")
                                    .recentArticles("1, 2, 3, 4, 5")
                                    .q1("q1")
                                    .q2("q2")
                                    .q3("q3")
                                    .userIntro("Helloguys!")
                                    .build();
            urepo.save(entity);

        });


    }

}
