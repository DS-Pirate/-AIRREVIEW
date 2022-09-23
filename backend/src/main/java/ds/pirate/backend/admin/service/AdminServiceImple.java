package ds.pirate.backend.admin.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPADeleteClause;

import com.querydsl.jpa.impl.JPAUpdateClause;

import ds.pirate.backend.admin.Repository.AdminArticleRepository;
import ds.pirate.backend.admin.Repository.AdminQuestionRepository;
import ds.pirate.backend.admin.Repository.AdminReportRepository;
import ds.pirate.backend.admin.Repository.AdminUserRepository;
import ds.pirate.backend.admin.dto.PageRequestDTO;
import ds.pirate.backend.admin.dto.PageResultDTO;
import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.dto.QuestionDTO;
import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.dto.reportDTO;
import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.QArticlesList;
import ds.pirate.backend.entity.QHashTags;
import ds.pirate.backend.entity.QQuestionsList;
import ds.pirate.backend.entity.Qacomments;
import ds.pirate.backend.entity.QairUser;
import ds.pirate.backend.entity.Qalarm;
import ds.pirate.backend.entity.QreportList;
import ds.pirate.backend.entity.QuestionsList;
import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.entity.reportList;
import ds.pirate.backend.repository.HashTagRepository;
import ds.pirate.backend.service.ArticleService.ArticleService;
import ds.pirate.backend.service.QuestionService.QuestionService;
import ds.pirate.backend.service.UserService.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImple implements AdminService {
    private final AdminArticleRepository arepo;
    private final AdminQuestionRepository qrepo;
    private final AdminUserRepository urepo;
    private final AdminReportRepository rrepo;
    private final ArticleService aser;
    private final UserService user;
    private final QuestionService qser;
    private final HashTagRepository hrepo;
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Boolean qaAnswer(QuestionDTO dto) {
        QQuestionsList qQuestionsList = new QQuestionsList("qa");
        JPAUpdateClause updateClause = new JPAUpdateClause(em, qQuestionsList);
        updateClause.where(qQuestionsList.qid.eq(dto.getQid()))
        .set(qQuestionsList.answerContext, dto.getAnswerContext())
        .set(qQuestionsList.answered, true)
        .execute();
        return true;
    }

    @Transactional
    @Override
    public Boolean reportCancel(Long reid) {
        QreportList report = new QreportList("report");
        JPADeleteClause deleteClause = new JPADeleteClause(em, report);
        deleteClause.where(report.reid.eq(reid)).execute();
        return true;
    }

    @Transactional
    @Override
    public Boolean articleDelete(ArticleDTO dto) {
        QArticlesList articlesList = new QArticlesList("article");
        QHashTags hashTags = new QHashTags("hashtags");
        Qacomments comment = new Qacomments("comment");
        Qalarm qalarm = new Qalarm("alarm");
        QreportList qreportList = new QreportList("report");

        JPADeleteClause deleteReport = new JPADeleteClause(em, qreportList);
        JPADeleteClause deletehash = new JPADeleteClause(em, hashTags);
        JPADeleteClause deleteClause = new JPADeleteClause(em, articlesList);
        JPADeleteClause deleteComment = new JPADeleteClause(em, comment);
        JPADeleteClause deleteAlarm = new JPADeleteClause(em, qalarm);

        deleteReport.where(qreportList.articles.aid.eq(dto.getAid())).execute();
        deleteAlarm.where(qalarm.articleId.aid.eq(dto.getAid())).execute();
        deleteComment.where(comment.articles.aid.eq(dto.getAid())).execute();
        deletehash.where(hashTags.articles.aid.eq(dto.getAid())).execute();
        deleteClause.where(articlesList.aid.eq(dto.getAid())).execute();
        return true;
    }

    @Override
    public List<String> getHashs(Long aid) {
        Optional<List<String>> hashs  = hrepo.OptionalfindByArticles(aid);
        if(hashs.isPresent()){
            List<String> result = hashs.get();
            return result;
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public Boolean UserDeleter(airUserDTO dto) {
        QairUser user = new QairUser("user");
        QQuestionsList qQuestionsList = new QQuestionsList("question");
        JPADeleteClause deleteQuestion = new JPADeleteClause(em, qQuestionsList);
        JPADeleteClause deleteUser = new JPADeleteClause(em, user);
        Optional<List<ArticlesList>> articlelist = arepo.getArticlesListByUserid(dto.getUserid());
        if(articlelist.isPresent()) {
            articlelist.get().forEach(v->{
                articleDelete(aser.EntityToDTO(v));                
            });
        }
        deleteQuestion.where(qQuestionsList.userid.userid.eq(dto.getUserid())).execute();
        deleteUser.where(user.userid.eq(dto.getUserid())).execute();
        return true;
    }
    @Override
    @Transactional
    public Boolean UserModifier(airUserDTO dto) {
        QairUser user = new QairUser("user");
        JPAUpdateClause updateClause = new JPAUpdateClause(em, user);

        updateClause.where(user.userid.eq(dto.getUserid()))
        .set(user.userid, dto.getUserid())
        .set(user.eMail, dto.getEmail())
        .set(user.airName, dto.getAirName())
        .set(user.birthDay, dto.getBirthDay())
        .set(user.gender, dto.getGender())
        .set(user.auth, dto.isAuth())
        .set(user.q1, dto.getQ1())
        .set(user.q2, dto.getQ2())
        .set(user.q3, dto.getQ3())
        .set(user.chName, dto.getChName())
        .set(user.userIntro, dto.getUserIntro())
        .execute();
        return true;
    }

    @Override
    public Boolean adminChecker(Long userid) {
        return urepo.isAdminCheck(userid);
    }



    @Override
    public PageResultDTO<ArticleDTO, ArticlesList> getArticleList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("aid").ascending());
        BooleanBuilder booleanBuilder = getArticleSearch(requestDTO);
        Page<ArticlesList> result = arepo.findAll(booleanBuilder, pageable);
        Function<ArticlesList, ArticleDTO> fn = entity -> aser.EntityToDTO(entity);
        return new PageResultDTO<>(result, fn);

    }

    @Override
    public PageResultDTO<QuestionDTO, QuestionsList> getQuestionList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("answered").ascending());
        QQuestionsList qQuestionsList = QQuestionsList.questionsList;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = qQuestionsList.qid.gt(0L);
        booleanBuilder.and(expression);
        Page<QuestionsList> result = qrepo.findAll(booleanBuilder, pageable);
        Function<QuestionsList, QuestionDTO> fn = entity-> qser.EntitytoDTO(entity);
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<reportDTO, reportList> getReportList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("userid_userid").ascending());
        QreportList qreportList = QreportList.reportList;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = qreportList.reid.gt(0L);
        booleanBuilder.and(expression);
        Page<reportList> result = rrepo.findAll(booleanBuilder, pageable);
        Function<reportList, reportDTO> fn = entity-> aser.reportEntitytoDTO(entity);
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<airUserDTO, airUser> getUserList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("userid").ascending());
        BooleanBuilder booleanBuilder = getUserSearch(requestDTO);
        Page<airUser> result = urepo.findAll(booleanBuilder, pageable);
        Function<airUser, airUserDTO> fn = entity -> user.entityToDTO(entity);
        return new PageResultDTO<>(result, fn);
    }

    private BooleanBuilder getUserSearch(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();
        String keyword = requestDTO.getKeyword();

        QairUser qGuestbook = QairUser.airUser;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = qGuestbook.userid.gt(0L);
        booleanBuilder.and(expression);
        if (type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }

        BooleanBuilder conditBuilder = new BooleanBuilder();
        if (type.contains("name")){
            conditBuilder.or(qGuestbook.airName.contains(keyword));
        };

        if (type.contains("mail")){
            conditBuilder.or(qGuestbook.eMail.contains(keyword));
        };
        booleanBuilder.and(conditBuilder);
        return booleanBuilder;
    }

    private BooleanBuilder getArticleSearch(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();
        String keyword = requestDTO.getKeyword();
        QArticlesList qArticlesList = QArticlesList.articlesList;
        
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = qArticlesList.aid.gt(0L);
        booleanBuilder.and(expression);
        if (type == null || type.trim().length() == 0) { // 검색 조건 無
            return booleanBuilder;
        }

        BooleanBuilder conditBuilder = new BooleanBuilder();
        if (type.contains("title")){
            conditBuilder.or(qArticlesList.atitle.contains(keyword));
        };

        if (type.contains("context")){
            conditBuilder.or(qArticlesList.context.contains(keyword));
        };
        booleanBuilder.and(conditBuilder);
        return booleanBuilder;
    }

}
