package ds.pirate.backend.repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;



import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.HashTags;
import ds.pirate.backend.entity.airUser;
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


    @Test
    public void getbyaid(){
        ArticlesList result = arepo.getByAid(1L);
        ArticleDTO dto = aservice.EntityToDTO(result);
        List<String> hashString =  hrepo.getList(result.getAid())
                                        .stream()
                                        .map(hentity -> hentity.getHashTagName())
                                        .collect(Collectors.toList());
        dto.setTags(hashString);
        log.info(dto);
    }

    @Test
    public void getUserInfoByuserId(){
        Optional<airUser>result = urepo.findByUserId(1L);
        log.info(result.get().getAirName());
    }


    // @Test
    // public void insertArticles(){
    //     IntStream.rangeClosed(1, 10).forEach(i->{
    //         ArticlesList entity = ArticlesList.builder()
    //                                 .atitle(i+"번글")
    //                                 .context((i+"번글 내용").getBytes())
    //                                 .opend(false)
    //                                 .shareable(false)
    //                                 .build();


    //         HashTags tags = HashTags.builder()
    //                                 .hashTagName(i+"tagname")
    //                                 .articles(entity)
    //                                 .build();
            
    //         ImagesList imgs = ImagesList.builder().fileName(i+"fileName").idx(1).articles(entity).build();

    //         acomments cgroup = acomments.builder().articles(entity)
    //         .airuser(airUser.builder().passwd(encoder.encode("1234"))
    //         .eMail(i+"aaa@aaa.com")
    //         .airName("airName"+i)
    //         .birthDay(LocalDateTime.now())
    //         .gender(1)
    //         .auth(false)
    //         .recentArticles("1, 2, 3, 4, 5")
    //         .q1("q1")
    //         .q2("q2")
    //         .q3("q3")
    //         .userIntro("Helloguys!")
    //         .chName("11111"+i)
    //         .build()).commentGroup(1L).commentSorts(1L).commentContext(1L)
    //         .rate(5).articleRate(5).build();

    //         // entity.updateComments(cgroup);
    //         // entity.updateImages(imgs);
    //         // entity.updateTags(tags);

    //         arepo.save(entity);

            
    //         HashTags hentity = HashTags.builder()
    //                             .hashTagName("hashTagName"+i)
    //                             .articles(entity)
    //                             .build();
    //         hrepo.save(hentity);
            
    //     });
    // }
    

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
        log.info(print);
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

}
