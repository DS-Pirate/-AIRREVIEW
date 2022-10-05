package ds.pirate.backend.service.SettingService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.QuestionDTO;
import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.dto.reportDTO;
import ds.pirate.backend.entity.QuestionsList;
import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.entity.reportList;
import ds.pirate.backend.entity.uImagesList;
import ds.pirate.backend.repository.ArticleReportRepository;
import ds.pirate.backend.repository.QuestionRepository;
import ds.pirate.backend.repository.UserImageListRepository;
import ds.pirate.backend.repository.UserRepository;
import ds.pirate.backend.service.ArticleService.ArticleService;
import ds.pirate.backend.service.QuestionService.QuestionService;
import ds.pirate.backend.service.UserService.UserService;
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
  private final QuestionService qService;

  @Override
  public airUserDTO getByUserId(Long userid) {
    airUser entity = urepo.findByUserIdWithUserImage(userid).get();
    airUserDTO result = uService.entityToDTO(urepo.findByUserIdWithUserImage(userid).get());
    List<uImagesList> filteredImg = entity.getUserImg().stream().filter(i-> i.getIdx()==0).toList();
    result.setUserImgName(filteredImg.get(0).getFileName());
    return result;
  }

  @Override
  public void uploadProfileImg(Long userid, String fileName) {
      Optional<uImagesList> isimage = uirepo.getByAiruser(userid);
      if(isimage.isPresent()){
          uirepo.delete(isimage.get());
      }
      log.info("09009090909090909099090909090909099090990909909090909090");
      uirepo.save(uImagesList.builder().airuser(airUser.builder().userid(userid).build()).fileName(fileName).idx(0).build());
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
  public List<reportDTO> getReportList(Long user) {
    List<reportList> getList = rrepo.findByUserid(airUser.builder()
        .userid(user)
        .build());
    return getList.stream().map((Function<reportList, reportDTO>) v -> {
      return aService.reportEntitytoDTO(v);
    }).collect(Collectors.toList());
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
  public List<QuestionDTO> getQuestionList(Long userid) {
    List<QuestionsList> result = qrepo.getQuestionList(airUser.builder()
        .userid(userid)
        .build());
    return result.stream().map((Function<QuestionsList, QuestionDTO>) v -> {
      return qService.EntitytoDTO(v);
    }).collect(Collectors.toList());
  }

}

// @Override
// public Boolean remove2(Long aid) {
// try {
// arepo.deleteById(aid);
// return true;
// } catch (Exception e) {
// e.printStackTrace();
// return false;
// }
// }