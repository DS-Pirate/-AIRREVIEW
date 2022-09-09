package ds.pirate.backend.service.ArticleService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.dto.SaveDTO;
import ds.pirate.backend.dto.acommentDTO;
import ds.pirate.backend.dto.acommentRateDTO;
import ds.pirate.backend.dto.likeUnlikeDTO;
import ds.pirate.backend.dto.reportDTO;
import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.HashTags;
import ds.pirate.backend.entity.ImagesList;
import ds.pirate.backend.entity.SaveList;
import ds.pirate.backend.entity.acommentRate;
import ds.pirate.backend.entity.acomments;
import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.entity.likeUnlikeList;
import ds.pirate.backend.entity.reportList;
import ds.pirate.backend.entity.subscribList;
import ds.pirate.backend.repository.ArticleReportRepository;
import ds.pirate.backend.repository.ArticleRepository;
import ds.pirate.backend.repository.CommentRateRepository;
import ds.pirate.backend.repository.CommentRepository;
import ds.pirate.backend.repository.HashTagRepository;
import ds.pirate.backend.repository.ImageRepository;
import ds.pirate.backend.repository.LikeUnlikeRepository;
import ds.pirate.backend.repository.SavedRepository;
import ds.pirate.backend.repository.SubscribeRepository;
import ds.pirate.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository repo;
    private final HashTagRepository hrepo;
    private final ImageRepository irepo;
    private final CommentRepository crepo;
    private final CommentRateRepository ctrepo;
    private final UserRepository urepo;
    private final ArticleReportRepository arepo;
    private final LikeUnlikeRepository lurepo;
    private final SavedRepository sarepo;
    private final SubscribeRepository surepo;

    @Override
    public List<ArticleDTO> getSearchCardInfo(Long aid) {
        String hashname = hrepo.findByArticles(ArticlesList.builder().aid(aid).build()).get(0).getHashTagName();
        List<ArticleDTO> articleResult = hrepo.getAidListByHashTagName(hashname).get().stream().map((Function<? super HashTags, ArticleDTO>) m->{
            ArticleDTO dto = EntityToDTO( repo.getByAid(m.getArticles().getAid()));
            if (dto.isShareable()) {
                return dto;    
            }else{
                return null;
            }
        }).collect(Collectors.toList());
        return articleResult;
    }

    @Override
    public HashMap<String, Boolean> getFunctionBtnStatusByUserid(Long userid, Long aid) {
        HashMap<String, Boolean> result = new HashMap<>();
        Optional<likeUnlikeList> favoCheck = lurepo.checkFavoLogByUserIdAndArticleId(userid, aid);
        Optional<reportList> reportCheck = arepo.checkReportLogByUserIdAndArticleId(userid, aid);
        Optional<SaveList> saveCheck = sarepo.checkSaveLogByUserIdAndArticleId(userid, aid);
        result.put("favo", favoCheck.isPresent()==true?true:false);
        result.put("report", reportCheck.isPresent()==true?true:false);
        result.put("save", saveCheck.isPresent()==true?true:false);
        return result;
    }


    @Override
    public String removeComment(acommentDTO dto) {
        Optional<acomments> checkingComment = crepo.getCommentByCidAndUserid(dto.getCid(), dto.getUserid());

        if (checkingComment.isPresent()) {
            crepo.delete(checkingComment.get());
            return "삭제되었습니다";
        } else {
            return "잘못된 접근입니다";
        }
    }

    @Override
    public String addSave(SaveDTO dto) {
        Optional<SaveList> checking = sarepo.checkSaveLogByUserIdAndArticleId(dto.getUserid(), dto.getAid());
        SaveList entity = saveDTOtoEntity(dto);
        if (checking.isPresent()) {
            sarepo.delete(checking.get());
            return "저장을 취소하였습니다";
        } else {
            sarepo.save(entity);
            return "저장 리스트에 추가되었습니다";
        }
    }

    @Override
    public String addLikeUnlike(likeUnlikeDTO dto) {
        Optional<likeUnlikeList> checking = lurepo.checkFavoLogByUserIdAndArticleId(dto.getUserid(), dto.getAid());
        likeUnlikeList entity = favoDTOtoEntity(dto);
        if (checking.isPresent()) {
            lurepo.delete(checking.get());
            return "좋아요를 취소하였습니다";
        } else {
            lurepo.save(entity);
            return "좋아요 리스트에 추가되었습니다";
        }
    }

    @Override
    public String addArticleReport(reportDTO dto) {
        Optional<reportList> chekcing = arepo.checkReportLogByUserIdAndArticleId(dto.getUserid(), dto.getArticleid());
        if (chekcing.isPresent()) {
            return "이미 신고한 글입니다";
        } else {
            arepo.save(reportDTOtoEntity(dto));
            return "신고가 완료되었습니다";
        }
    }

    @Override
    public Double getArticleAvgRating(Long aid) {
        return crepo.getAvgByAid(aid);
    }

    @Override
    public String rateupComment(acommentRateDTO dto) {
        acomments result = crepo.getCommentByCidAndUserid(dto.getCid(), dto.getUserid()).get();
        acommentDTO cdto = commentEntityToDTO(result);

        Optional<acommentRate> isRated = ctrepo.getIsRatedByCidAndUserid(dto.getCid(), dto.getUserid());
        if (isRated.isPresent()) {
            cdto.setRate(cdto.getRate() - isRated.get().getUpdown());
            ctrepo.delete(isRated.get());
            crepo.save(commentDTOtoEntity(cdto));
            return "취소되었습니다";
        } else {
            cdto.setRate(cdto.getRate() + dto.getUpdown());
            crepo.save(commentDTOtoEntity(cdto));
            ctrepo.save(acommentRate.builder().commentid(cdto.getCid()).userid(cdto.getUserid()).updown(dto.getUpdown())
                    .build());
            log.info("없음 등록 ㄲ");
            return "등록되었습니다";
        }
    }

    @Override
    public Long addNewComment(acommentDTO dto) {
        Optional<airUser> result = urepo.findByEmail(dto.getEmail());
        Optional<acomments>  checkingAirUser = 
        crepo.getCommentByAidAndUserid(ArticlesList.builder().aid(dto.getAid()).build(), airUser.builder().userid(dto.getUserid()).build());

        if (!checkingAirUser.isPresent()) {
            dto.setCommentGroup(dto.getCommentGroup() + 1);
            dto.setCommnetDepth(0L);
            dto.setCommentSorts(0L);
            dto.setUserid(result.get().getUserid());
            dto.setRate(0);
            acomments entity = commentDTOtoEntity(dto);
            crepo.save(entity);
            return -1L;
        } else {
            return checkingAirUser.get().getCid();
        }

    }

    @Override
    public Long addNewCommentReply(acommentDTO dto) {
        Optional<airUser> result = urepo.findByEmail(dto.getEmail());
        dto.setCommentGroup(dto.getCommentGroup());
        dto.setCommnetDepth(dto.getCommnetDepth());
        dto.setCommentSorts(dto.getCommentSorts());
        dto.setUserid(result.get().getUserid());
        dto.setRate(0);

        acomments entity = commentDTOtoEntity(dto);

        crepo.save(entity);
        return entity.getCid();
    }

    @Override 
    public List<acommentDTO> getCommentListByAid(Long aid) {
        Optional<List<acomments>> entity = crepo.getListByAid(aid);

        if (entity.isPresent()) {
            List<acommentDTO> dto = entity.get()
                    .stream()
                    .map(cmt -> commentEntityToDTO(cmt))
                    .collect(Collectors.toList());
            return dto;
        }
        return null;
    }

   @Override
   public HashMap<String, Object> getCommentListByAidTwo(Long aid, Long userid) {
    HashMap<String, Object> result = new HashMap<>();

    Optional<List<acomments>> entity = crepo.getListByAid(aid);
        if (entity.isPresent()) {
            List<acommentDTO> dto = entity.get()
                    .stream()
                    .map(cmt -> commentEntityToDTO(cmt)).collect(Collectors.toList());
            
            dto.forEach((t) -> {
                Optional<acommentRate> israted = ctrepo.getIsRatedByCidAndUserid(t.getCid(), userid);
                if(israted.isPresent()){
                    t.setIsrated(israted.get().getUpdown());
                }
            });
            result.put("commentList", dto);
            return result;
        }
       return null;
   }

    @Override
    public ArticleDTO getArticleInfoByAid(Long aid) {
        ArticlesList result = repo.getByAid(aid);
        ArticleDTO dto = EntityToDTO(result);
        List<String> hashString = hrepo.getList(result.getAid())
                .stream()
                .map(hentity -> hentity.getHashTagName())
                .collect(Collectors.toList());
        dto.setTags(hashString);
        return dto;
    }


    @Override
    public String addArticle(ArticleDTO dto, List<String> tags) {
        ArticlesList result = dtoToEntity(dto);
        repo.save(result);

        List<ImagesList> lists = dto.getImages();
        lists.forEach(i -> {
            irepo.save(ImagesList.builder().fileName(i.getFileName()).articles(result).build());
        });

        for (String i : tags) {
            HashTags hresult = listToHSEntity(i);
            hresult.updateArticles(result);
            hrepo.save(hresult);
        }

        return result.getAid().toString();
    }

    @Override
    public HashMap<String, String> getSubscardInfo(Long aid, Long userid) {
        HashMap<String, String> result = new HashMap<>();
        Long articleUserId = repo.getArticleAuthorIdByAid(aid);
        airUser articleUserEntity = urepo.findByUserId(articleUserId).get();
        Optional<subscribList> subchecking = surepo.getIsSubcedByTargetIdAndUserid(articleUserId, userid);
        Long subcount = surepo.getSumByTargetId(articleUserId);
        result.put("articleUserName", articleUserEntity.getAirName());
        result.put("articleUserImg", "./images/read/userid/"+(articleUserId.toString()));
        result.put("isgudoked", subchecking.isPresent()?"true":"false");
        result.put("subCount", subcount.toString());
        return result;
    }

    @Override
    public String subsFunction(Long aid, Long userid) {
        Long articleUserId = repo.getArticleAuthorIdByAid(aid);
        Optional<subscribList> subchecking = surepo.getIsSubcedByTargetIdAndUserid(articleUserId, userid);
        airUser articleUserEntity = urepo.findByUserId(articleUserId).get();
        if(subchecking.isPresent()){
            surepo.delete(subchecking.get());
            return "구독이 해지되었습니다";
        }else{
            surepo.save(subscribList.builder().targetId(articleUserId).userid(articleUserEntity).build());
            return "구독되었습니다";
        }

        
    }

}
