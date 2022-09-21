package ds.pirate.backend.admin.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

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
import ds.pirate.backend.entity.QQuestionsList;
import ds.pirate.backend.entity.QairUser;
import ds.pirate.backend.entity.QreportList;
import ds.pirate.backend.entity.QuestionsList;
import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.entity.reportList;
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
        Pageable pageable = requestDTO.getPageable(Sort.by("qid").descending());
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
        Pageable pageable = requestDTO.getPageable(Sort.by("reid").descending());
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
