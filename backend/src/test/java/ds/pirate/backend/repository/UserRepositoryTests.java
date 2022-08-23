package ds.pirate.backend.repository;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.crypto.password.PasswordEncoder;

import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.HashTags;
import ds.pirate.backend.entity.airUser;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    UserRepository urepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ArticleRepository arepo;

    @Autowired
    HashTagRepository hrepo;

    @Test
    public void insertArticles(){
        IntStream.rangeClosed(1, 10).forEach(i->{
            ArticlesList entity = ArticlesList.builder()
                                    .atitle(i+"번글")
                                    .context(i+"번글 내용")
                                    .opend(false)
                                    .shareable(false)
                                    .build();

            arepo.save(entity);
            HashTags hentity = HashTags.builder()
                                .hashTagName("hashTagName"+i)
                                .articles(entity)
                                .build();
            hrepo.save(hentity);
            
        });
    }

    @Test
    public void insertAccounts(){
        IntStream.rangeClosed(1, 50).forEach(i->{
            airUser entity = airUser.builder()
                                    .passwd(encoder.encode("1234"))
                                    .eMail(i+"aaa@aaa.com")
                                    .airName("airName"+i)
                                    .birthDay(LocalDateTime.now())
                                    .gender(1)
                                    .auth(false)
                                    .recentArticles("1, 2, 3, 4, 5")
                                    .q1("q1")
                                    .q2("q2")
                                    .q3("q3")
                                    .userIntro("Helloguys!")
                                    .chName("11111"+i)
                                    .build();
            urepo.save(entity);

        });


    }

    @Test
    public void insertAccounts2(){
        IntStream.rangeClosed(1, 30).forEach(i->{
            airUser entity = airUser.builder()
                                    .airName("airName"+1)
                                    .auth(false)
                                    .birthDay(LocalDateTime.now())
                                    .eMail("aaa"+i+"@aaa.com")
                                    .gender(1)
                                    .passwd(encoder.encode("1234"))
                                    .q1(i+"q1")
                                    .q2(i+"q3")
                                    .q3(i+"q2")
                                    .recentArticles("1, 2, 3, 4, 5")
                                    .userIntro("Helloguys!")
                                    .build();
            urepo.save(entity);
        });
    }

}
