package ds.pirate.backend.admin.service;

import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import ds.pirate.backend.admin.AdminArticleRepository;
import ds.pirate.backend.admin.AdminQuestionRepository;
import ds.pirate.backend.admin.AdminReportRepository;
import ds.pirate.backend.admin.AdminUserRepository;
import ds.pirate.backend.admin.dto.PageRequestDTO;
import ds.pirate.backend.admin.dto.PageResultDTO;
import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.dto.QuestionDTO;
import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.dto.reportDTO;
import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.QuestionsList;
import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.entity.reportList;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImple implements AdminService{
    private final AdminArticleRepository arepo;
    private final AdminQuestionRepository qrepo;
    private final AdminUserRepository urepo;
    private final AdminReportRepository rrepo;

    @Override
    public PageResultDTO<ArticleDTO, ArticlesList> getArticleList(PageRequestDTO requestDTO) {

        
        return null;
    }

    @Override
    public PageResultDTO<QuestionDTO, QuestionsList> getQuestionList(PageRequestDTO requestDTO) {
        
        return null;
    }

    @Override
    public PageResultDTO<reportDTO, reportList> getReportList(PageRequestDTO requestDTO) {
        
        return null;
    }

    @Override
    public PageResultDTO<airUserDTO, airUser> getUserList(PageRequestDTO requestDTO) {
        
        return null;
    }

    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();
        String keyword = requestDTO.getKeyword();
    
        QGuestbook qGuestbook = QGuestbook.guestbook; // 관련된 테이블에 대한 쿼리 객체
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = qGuestbook.gno.gt(0L);
        booleanBuilder.and(expression);
        if(type == null || type.trim().length() == 0) { // 검색 조건 無
          return booleanBuilder;
        }
    
        BooleanBuilder conditBuilder = new BooleanBuilder();
        if(type.contains("t")) conditBuilder.or(qGuestbook.title.contains(keyword));
        if(type.contains("c")) conditBuilder.or(qGuestbook.content.contains(keyword));
        if(type.contains("w")) conditBuilder.or(qGuestbook.writer.contains(keyword));
        booleanBuilder.and(conditBuilder);
    
        return booleanBuilder;
      }
}
