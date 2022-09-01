package ds.pirate.backend.test;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;



import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.HashTags;
import ds.pirate.backend.entity.acomments;
import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.entity.uImagesList;
import ds.pirate.backend.repository.ArticleRepository;
import ds.pirate.backend.repository.CommentRepository;
import ds.pirate.backend.repository.HashTagRepository;
import ds.pirate.backend.repository.UserImageListRepository;
import ds.pirate.backend.repository.UserRepository;
import ds.pirate.backend.service.ArticleService.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
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

    @Autowired
    ArticleService aservice;

    @Autowired
    CommentRepository crepo;

    @Autowired
    UserImageListRepository uimgrepo;

    @Test
    public void getbyaid(){
        ArticlesList result = arepo.getByAid(1L);
        ArticleDTO dto = aservice.EntityToDTO(result);
        List<String> hashString =  hrepo.getList(result.getAid())
                                        .stream()
                                        .map(hentity -> hentity.getHashTagName())
                                        .collect(Collectors.toList());
        dto.setTags(hashString);
    }

    @Test
    public void getUserInfoByuserId(){
        Optional<airUser>result = urepo.findByUserId(1L);
    }


    @Test
    public void getHList(){
        List<HashTags> result = hrepo.getList(1L);
        result.forEach(i->{
            
        });

    }

    @Test
    public void getAllList(){
        List<ArticlesList>result = arepo.getList();
        result.forEach(i -> {
        ArticleDTO print = ArticleDTO.builder()
                        .aid(i.getAid())
                        .atitle(i.getAtitle())
                        .context(i.updateContextToString(i.getContext()))
                        .opened(i.isOpend())
                        .shareable(i.isShareable())
                        .build();
        });
        
    }



    @Test
    public void insertAccounts(){
        IntStream.rangeClosed(1, 10).forEach(i->{
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

    @Test
    public void insertcomment(){
        ArticlesList aid = ArticlesList.builder().aid(1L).build();
        airUser userid = airUser.builder().userid(1L).build();
        acomments entity = acomments.builder()
        .articles(aid)
        .airuser(userid)
        .commentGroup(1L)
        .commnetDepth(0L)
        .commentSorts(0L)
        .commentContext("가나다라마바사아자차카타파하")
        .rate(5)
        .articleRate(3)
        .build();
        crepo.save(entity);
    }

    
    @Test
    public void insertcommentTwo(){
        LongStream.rangeClosed(1L, 5L).forEach(i->{
            ArticlesList aid = ArticlesList.builder().aid(1L).build();
            airUser userid = airUser.builder().userid(1L).build();
            acomments entity = acomments.builder()
            .articles(aid)
            .airuser(userid)
            .commentGroup(i)
            .commnetDepth(0L)
            .commentSorts(0L)
            .commentContext("가나다라마바사아자차카타파하")
            .rate(3)
            .articleRate(4)
            .build();
            crepo.save(entity);
        });

    }


    @Test
    public void insertulImage(){
        airUser userid = airUser.builder().userid(1L).build();
        uImagesList ulid = uImagesList.builder()
        .airuser(userid)
        .fileName("29a0f4f1-3882-4b3d-9641-0e71751842d7.png")
        .build();
        uimgrepo.save(ulid);
    }
}
