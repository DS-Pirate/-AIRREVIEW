package ds.pirate.backend.service.SettingService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPADeleteClause;

import ds.pirate.backend.dto.QuestionDTO;
import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.dto.reportDTO;
import ds.pirate.backend.entity.QArticlesList;
import ds.pirate.backend.entity.QHashTags;
import ds.pirate.backend.entity.QImagesList;
import ds.pirate.backend.entity.QSaveList;
import ds.pirate.backend.entity.Qacomments;
import ds.pirate.backend.entity.Qalarm;
import ds.pirate.backend.entity.QlikeUnlikeList;
import ds.pirate.backend.entity.QreportList;
import ds.pirate.backend.entity.QuestionsList;
import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.entity.reportList;
import ds.pirate.backend.entity.uImagesList;
import ds.pirate.backend.repository.ArticleReportRepository;
import ds.pirate.backend.repository.ArticleRepository;
import ds.pirate.backend.repository.QuestionRepository;
import ds.pirate.backend.repository.UserImageListRepository;
import ds.pirate.backend.repository.UserRepository;
import ds.pirate.backend.repository.ArticleRepository.getMyChannelArticleList;
import ds.pirate.backend.service.ArticleService.ArticleService;
import ds.pirate.backend.service.UserService.UserService;
import ds.pirate.backend.vo.channelArticleList;
import ds.pirate.backend.vo.settingArticleList;
import ds.pirate.backend.vo.userid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class SettingServiceImpl implements SettingService {
  private final UserRepository urepo;
  private final UserImageListRepository uirepo;
  private final UserService uService;
  private final PasswordEncoder encoder;
  private final ArticleService aService;
  private final ArticleReportRepository rrepo;
  private final QuestionRepository qrepo;
  private final ArticleRepository arepo;
  @PersistenceContext
  EntityManager em;

  @Override
  public Page<getMyChannelArticleList> articleListByUserid(channelArticleList vo) {
      Pageable pageable = PageRequest.of(vo.getPageNum(), 10, Sort.by(Sort.Direction.DESC, "aid"));
      if (vo.getAtitle() != null){
          return arepo.getArticleListByUserIdAndSearchWithPageable(vo.getUserid(),vo.getAtitle(),pageable);
      }
      return arepo.getArticleListByUserIdWithPageable(vo.getUserid(), pageable);
  }

  @Override
  public airUserDTO getByUserId(Long userid) {
    airUser entity = urepo.findByUserIdWithUserImage(userid).get();
    airUserDTO result = uService.entityToDTO(urepo.findByUserIdWithUserImage(userid).get());
    List<uImagesList> filteredImg = entity.getUserImg().stream().filter(i-> i.getIdx()==0).toList();
    result.setUserImgName(filteredImg.get(0).getFileName());
    return result;
  }

  @Override
  @Transactional
  public void uploadProfileImg(Long userid, String fileName) {
      Optional<uImagesList> isimage = uirepo.getByAiruserEntity(airUser.builder().userid(userid).build());
      if(isimage.isPresent()){
        log.info(isimage.get().getIid());
          urepo.findByUserId(isimage.get().getAiruser().getUserid()).get().getUserImg().removeIf(isimg->{return isimg.getAiruser().getUserid().equals(isimage.get().getAiruser().getUserid());});
          uirepo.delete(isimage.get());
          Optional<uImagesList> img = uirepo.getByAiruserEntity(airUser.builder().userid(userid).build());
          img.ifPresent(v->{log.info(v);});
      }
      uirepo.save(uImagesList.builder().airuser(airUser.builder().userid(userid).build()).fileName(fileName).idx(0).build());
  }

  @Transactional
  @Override
  public Boolean articleRemove(Long aid) {
    
    QArticlesList articlesList = new QArticlesList("article");
    QHashTags hashTags = new QHashTags("hashtags");
    Qacomments comment = new Qacomments("comment");
    Qalarm qalarm = new Qalarm("alarm");
    QreportList qreportList = new QreportList("report");
    QImagesList qImagesList = new QImagesList("img");
    QlikeUnlikeList qlikeUnlikeList = new QlikeUnlikeList("lik");
    QSaveList qSaveList = new QSaveList("sav");

    JPADeleteClause deltesave = new JPADeleteClause(em, qSaveList);
    JPADeleteClause deletelikClause = new JPADeleteClause(em, qlikeUnlikeList);
    JPADeleteClause deleteImage = new JPADeleteClause(em, qImagesList);
    JPADeleteClause deleteReport = new JPADeleteClause(em, qreportList);
    JPADeleteClause deletehash = new JPADeleteClause(em, hashTags);
    JPADeleteClause deleteClause = new JPADeleteClause(em, articlesList);
    JPADeleteClause deleteComment = new JPADeleteClause(em, comment);
    JPADeleteClause deleteAlarm = new JPADeleteClause(em, qalarm);

    deltesave.where(qSaveList.aid.eq(aid)).execute();
    deletelikClause.where(qlikeUnlikeList.aid.eq(aid)).execute();
    deleteImage.where(qImagesList.articles.aid.eq(aid)).execute();
    deleteReport.where(qreportList.articles.aid.eq(aid)).execute();
    deleteAlarm.where(qalarm.articleId.aid.eq(aid)).execute();
    deleteComment.where(comment.articles.aid.eq(aid)).execute();
    deletehash.where(hashTags.articles.aid.eq(aid)).execute();
    deleteClause.where(articlesList.aid.eq(aid)).execute();
    return true;
  }


  @Override
  public String changePasswd(userid vo) {
    Optional<airUser> result = urepo.findByUserId(vo.getUserid());
    if (encoder.matches(vo.getCpasswd(), result.get().getPasswd())) {
      LocalDateTime birthday = LocalDateTime.of(vo.getYear(), vo.getMonth(), vo.getDate(), 0, 0, 0);
      urepo.changePasswdbyIdAndcpass(vo.getUserid(),
          encoder.encode(vo.getUpasswd()),
          vo.getName(), vo.getEmail(), vo.getUserintro(), birthday);
      return "설정을 변경했습니다";
    } else {
      return "다시 설정해주세요";
    }
  }

  @Override
  public HashMap<String, Object> getReportList(settingArticleList vo) {
    Pageable pageable = PageRequest.of(vo.getPageNum(), 5);
    Page<reportList> getList = rrepo.findByUseridWithPageable(vo.getUserid(), pageable);
      List<reportDTO> dto = getList.getContent().stream().map(new Function<reportList, reportDTO> () {
        @Override
        public reportDTO apply(reportList t) {
            return aService.reportEntitytoDTO(t);
        }
      }).toList();
      HashMap<String, Object> hash = new HashMap<>();
      hash.put("page",  getList.getNumber());
      hash.put("dto", dto);
      hash.put("totalElement", getList.getTotalElements());
      hash.put("totalPage", getList.getTotalPages());


    return hash;
  }

  @Override
  public Boolean remove(Long reid) {
    try {
      rrepo.deleteById(reid);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public HashMap<String, Object> getQuestionList(settingArticleList vo) {
    Pageable pageable = PageRequest.of(vo.getPageNum(), 5);
    Page<QuestionsList> getList = qrepo.getQuestionListWithPageable(vo.getUserid(), pageable);
    List<QuestionDTO> dto = getList.getContent().stream().map(new Function<QuestionsList, QuestionDTO> () {
      @Override
      public QuestionDTO apply(QuestionsList t) {
          return QuestionDTO.builder().qid(t.getQid()).answerContext(t.getAnswerContext()).answered(t.isAnswered()).context(t.getContext()).title(t.getTitle()).userid(t.getUserid().getUserid()).build();
      }
    }).toList();
    HashMap<String, Object> hash = new HashMap<>();
    hash.put("page",  getList.getNumber());
    hash.put("dto", dto);
    hash.put("totalElement", getList.getTotalElements());
    hash.put("totalPage", getList.getTotalPages());
    return hash;
  }

}
