package ds.pirate.backend.test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import ds.pirate.backend.service.ApiMemberService.ApiMemberService;

import ds.pirate.backend.vo.MySubInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.dto.acommentDTO;
import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.HashTags;
import ds.pirate.backend.entity.SaveList;
import ds.pirate.backend.entity.acomments;
import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.entity.alarm;
import ds.pirate.backend.entity.likeUnlikeList;
import ds.pirate.backend.entity.subscribList;
import ds.pirate.backend.entity.uImagesList;
import ds.pirate.backend.repository.AlarmRepository;
import ds.pirate.backend.repository.ArticleRepository;
import ds.pirate.backend.repository.CommentRateRepository;
import ds.pirate.backend.repository.CommentRepository;
import ds.pirate.backend.repository.HashTagRepository;
import ds.pirate.backend.repository.LikeUnlikeRepository;
import ds.pirate.backend.repository.SavedRepository;
import ds.pirate.backend.repository.SubscribeRepository;
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

    @Autowired
    ArticleService aser;

    @Autowired
    SubscribeRepository srepo;

    @Autowired
    CommentRateRepository ctrepo;

    @Autowired
    AlarmRepository alrepo;

    @Autowired
    LikeUnlikeRepository likeunlikerepo;

    @Autowired
    SavedRepository savrepo;

    @Autowired
    SubscribeRepository subrepo;

    @Autowired
    ApiMemberService memberService;

    @Test
    void insertDummyAlarm() {
        LongStream.rangeClosed(1, 5).forEach(v -> {
            alarm entity = alarm
                    .builder()
                    .articleId(ArticlesList.builder().aid(1L).build())
                    .whoUser(airUser.builder().userid(v).build())
                    .toUser(airUser.builder().userid(1L).build())
                    .checked(false)
                    .build();
            alrepo.save(entity);

        });
    }

    @Test
    void getAlarmListDirectly() {
        log.info(alrepo.getAlarmInfoByUserid(1L));
    }

    @Test
    void getListByUserIdAndAuthorId() {
        Optional<List<acomments>> result = crepo.getListByUserIdAndAuthorId(1L, 1L);
        log.info(result.get());
    }

    // @Test
    // void temporaltesttogetalarmlist() {
    // List<ArticlesList> articleListByuserid = arepo.getListbyuserId(1L);
    //
    // List<alarm> alarmList =
    // articleListByuserid.stream().map((Function<ArticlesList, alarm>) v -> {
    // alarm result = alrepo.findByArticleId(v.getAid());
    // return result;
    // }).collect(Collectors.toList());
    //
    // log.info(alarmList);
    //
    //
    // }

    // @Test
    // void temporaltesttogetalarmlist() {
    // List<ArticlesList> articleListByuserid = arepo.getListbyuserId(1L);

    // List<alarm> alarmList =
    // articleListByuserid.stream().map((Function<ArticlesList, alarm>) v -> {
    // alarm result = alrepo.findByArticleId(v.getAid());
    // return result;
    // }).collect(Collectors.toList());

    // log.info(alarmList);

    // }

    @Test
    void getArticleListByUserIdWithPageable(){
        log.info("-----------------------------------------------------------------");
        Pageable pageable = PageRequest.of(0, 10);
        arepo.getArticleListByUserIdWithPageable(1L, pageable).forEach(v->{
            log.info(v);
        });
        log.info("-----------------------------------------------------------------");
    }

    @Test
    void findByArticlesAndHashTagName() {
        log.info(hrepo.findByArticlesAndHashTagName(arepo.getByAid(56L), "코멘트페이징 테스트그으으을"));
    }

    @Test
    void findByAid() {
        log.info(arepo.findByAid(66L));
    }

    @Test
    void getArticleByAidAndUserId() {
        Optional<ArticlesList> isit = arepo.getArticleByAidAndUserId(56L, 1L);
        if (!isit.isPresent()) {
            log.info(-1);
        } else {
            log.info(aser.EntityToDTO(isit.get()));
        }
    }

    @Test
    void getPagedAidListByHashTagName() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<HashTags> result = hrepo.findByHashTagNameContainsIgnoreCaseOrderByHidDesc("테스트", pageable);
        List<ArticleDTO> aresult = result.get().map((Function<HashTags, ArticleDTO>) dto -> {
            ArticleDTO dtoresult = aser.EntityToDTO(dto.getArticles());
            return dtoresult;
        }).collect(Collectors.toList());

        aresult.forEach(dto -> {
            log.info(dto);
        });
    }

    @Test
    void isratedget() {
        log.info(ctrepo.getIsRatedByCidAndUserid(43L, 2L));
    }

    @Test
    void subsTestingAdding() {
        srepo.save(subscribList.builder().userid(airUser.builder().userid(1L).build()).targetId(1L).build());
    }

    @Test
    void getArticleByAid() {
        log.info(arepo.getArticleAuthorIdByAid(2L));
    }

    @Test
    void getHashByAid() {
        log.info("-----------------------------------------------------------------");
        log.info(hrepo.findOneByArticle(1L).get().getHashTagName());
        // log.info(hrepo.findOneByArticle(ArticlesList.builder().aid(27L).build()).get().getHashTagName());
        log.info("-----------------------------------------------------------------");
        // 이 코드는 일단 보류 내생각에는 테스트케이스 만개 이런식으로 넘어가면 얘가 더 좋아야하는데 일단 아래 놈이 더 빠르니 저걸로
    }

    @Test
    void getHashByAid2() {
        log.info("-----------------------------------------------------------------");
        log.info(hrepo.findByArticles(ArticlesList.builder().aid(27L).build()).get(0).getHashTagName());
        // ?? 정렬을 안 해서 그런가?? 아니면 글 수가 적어서 그런건가;;??? 얘가 더 빠르니 이걸로 ㄲ
        log.info("-----------------------------------------------------------------");
    }

    @Test
    void getHashtagSearchResult() {
        log.info("-----------------------------------------------------------------");

        // hrepo.getAidListByHashTagName("추천게시글테스트용 글").get().forEach(result->{
        // log.info(result.getArticles().getAid());

        // });
        List<ArticleDTO> aidresult = hrepo.getAidListByHashTagName("추천게시글테스트용 글").get().stream()
                .map((Function<? super HashTags, ArticleDTO>) m -> {
                    ArticleDTO dto = aser.EntityToDTO(arepo.getByAid(m.getArticles().getAid()));
                    if (dto.isShareable()) {
                        return dto;
                    } else {
                        return null;
                    }
                }).collect(Collectors.toList());

        log.info(aidresult);
        log.info("-----------------------------------------------------------------");
    }

    @Test
    void commentPagingTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<acomments> cmlist = crepo.getPageList(pageable, 53L);

        List<acommentDTO> dto = cmlist
                .stream()
                .map((Function<? super acomments, acommentDTO>) cmt -> {
                    return aser.commentEntityToDTO(cmt);
                })
                .collect(Collectors.toList());

        log.info(dto);

        // cmlist.get().forEach(comment->{
        // log.info(comment);
        // });

    }

    @Test
    public void getCommentListByAid() {
        Optional<List<acomments>> entity = crepo.getListByAid(53L);
        if (entity.isPresent()) {
            List<acommentDTO> dto = entity.get()
                    .stream()
                    .map(cmt -> aser.commentEntityToDTO(cmt))
                    .collect(Collectors.toList());
            log.info(dto);
        }
    }

    @Test
    public void getByUserId() {
        Optional<List<subscribList>> likeList = subrepo.getByUserId(1L);
        likeList.get().forEach(v -> {
            log.info(v.getTargetId());
        });
    }

    @Test
    void getListByaid() {
        Optional<List<likeUnlikeList>> likeList = likeunlikerepo.getList(1L);
        List<ArticlesList> result = likeList.get().stream()
                .map((Function<? super likeUnlikeList, ? extends ArticlesList>) v -> {
                    ArticlesList list = arepo.getByAid(v.getAid());
                    return list;
                }).collect(Collectors.toList());
        result.forEach(v -> {
            log.info(v.getAtitle());
        });
    }

    @Test
    void getSaveListByaid() {
        Optional<List<SaveList>> saveList = savrepo.getList(1L);
        List<ArticlesList> result = saveList.get().stream()
                .map((Function<? super SaveList, ? extends ArticlesList>) v -> {
                    ArticlesList list = arepo.getByAid(v.getAid());
                    return list;
                }).collect(Collectors.toList());
        result.forEach(v -> {
            log.info(v.getAtitle());
        });
    }

    @Test
    void getsubscribeListByaid() {
        Optional<List<subscribList>> subList = subrepo.getByUserId(1L);
        List<ArticlesList> result = subList.get().stream()
                .map((Function<? super subscribList, ? extends ArticlesList>) v -> {
                    ArticlesList list = arepo.getByAid(v.getTargetId());
                    return list;
                }).collect(Collectors.toList());
        result.forEach(v -> {
            log.info(v.getAtitle());
        });
    }

    // @Test
    // public void getbyaid() {
    // List<HashTags> result = hrepo.getList(1L);

    // List<HashTagDTO> dtos = result.stream().map((Function<HashTags, HashTagDTO>)
    // v -> {
    // return aser.hEntityToDTO(v);
    // }).collect(Collectors.toList());
    // }

    // @Test
    // public void getUserInfoByuserId(){
    // Optional<airUser>result = urepo.findByUserId(1L);
    // }

    // @Test
    // public void getHList(){
    // List<HashTags> result = hrepo.getList(1L);
    // result.forEach(i->{

    // });

    // }

    // @Test
    // public void getAllList(){
    // List<ArticlesList>result = arepo.getList();
    // result.forEach(i -> {
    // ArticleDTO print = ArticleDTO.builder()
    // .aid(i.getAid())
    // .atitle(i.getAtitle())
    // .context(i.updateContextToString(i.getContext()))
    // .opened(i.isOpend())
    // .shareable(i.isShareable())
    // .build();
    // });

    // }

    @Test
    public void getLatestCidWhereMatchWithAid() {
        log.info(crepo.getLatestCommentGroupWhereMatchWithAid(56L));
    }

    @Test
    public void getAVGArticleRate() {
        Double result = crepo.getAvgByAid(1L);
        log.info("평균값" + result);
    }

    @Test
    public void insertAccounts() {
        IntStream.rangeClosed(1, 20).forEach(i -> {
            airUser entity = airUser.builder()
                    .passwd(encoder.encode("1234"))
                    .eMail(i + "aaa@aaa.com")
                    .airName("airName" + i)
                    .birthDay(LocalDateTime.now())
                    .gender(1)
                    .auth(false)
                    .recentArticles("1, 2, 3, 4, 5")
                    .q1("q1")
                    .q2("q2")
                    .q3("q3")
                    .userIntro("Helloguys!")
                    .chName("11111" + i)
                    .build();
            urepo.save(entity);

        });

    }

    @Test
    public void insertAccounts2() {
        IntStream.rangeClosed(1, 30).forEach(i -> {
            airUser entity = airUser.builder()
                    .airName("airName" + 1)
                    .auth(false)
                    .birthDay(LocalDateTime.now())
                    .eMail("aaa" + i + "@aaa.com")
                    .gender(1)
                    .passwd(encoder.encode("1234"))
                    .q1(i + "q1")
                    .q2(i + "q3")
                    .q3(i + "q2")
                    .recentArticles("1, 2, 3, 4, 5")
                    .userIntro("Helloguys!")
                    .build();
            urepo.save(entity);
        });
    }

    @Test
    public void insertcomment() {
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
    public void insertcommentTwo() {
        LongStream.rangeClosed(2L, 20L).forEach(i -> {
            ArticlesList aid = ArticlesList.builder().aid(56L).build();
            airUser userid = airUser.builder().userid(i).build();
            acomments entity = acomments.builder()
                    .articles(aid)
                    .airuser(userid)
                    .commentGroup(i)
                    .commnetDepth(0L)
                    .commentSorts(0L)
                    .commentContext("가나다라마바사아자차카타파하")
                    .rate(0)
                    .articleRate(5)
                    .build();
            crepo.save(entity);
        });

    }

    @Test

    public void insertulImage() {
        airUser userid = airUser.builder().userid(47L).build();
        uImagesList ulid = uImagesList.builder()
                .airuser(userid)
                .fileName("29a0f4f1-3882-4b3d-9641-0e71751842d7.png")
                .build();
        uimgrepo.save(ulid);
    }

//    @Test
//    public void searchCards() {
//        String decode = "";
//        try {
//            decode = URLDecoder.decode("aa", "UTF-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        log.info(decode);
//        log.info(arepo.getListAndAuthorByAuthorOrAtitle("a"));
//        log.info(arepo.getListAndAuthor());
//    }

    @Test
    public void findUserIdbyEmailAndQ() {
        log.info(urepo.findUserIdByEmailAndQ("3@3.3", "", "", "11"));
    }

    @Test
    public void changePass() {
        urepo.changePassbyId(43L, "12345");

    }

    @Test
    public void findemail() {
        Optional<airUser> result = urepo.findByEmail("3@3.3");
        log.info(result);
    }

    @Test
    public void cardsListOrderBySub(){
        SubscribeRepository.getMySubInfo result = subrepo.getFollwerFollwingCountByUserid(42L).get();
        log.info("resulttttttttttttttttttttttt");
        log.info(result);
    }

}
