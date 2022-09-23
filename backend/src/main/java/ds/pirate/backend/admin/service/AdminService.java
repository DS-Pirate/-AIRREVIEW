package ds.pirate.backend.admin.service;

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

public interface AdminService {
    PageResultDTO<airUserDTO, airUser> getUserList(PageRequestDTO requestDTO);
    PageResultDTO<ArticleDTO, ArticlesList> getArticleList(PageRequestDTO requestDTO);
    PageResultDTO<reportDTO, reportList> getReportList(PageRequestDTO requestDTO);
    PageResultDTO<QuestionDTO, QuestionsList> getQuestionList(PageRequestDTO requestDTO);
    Boolean adminChecker(Long userid);
}
