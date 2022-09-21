package ds.pirate.backend.service.ArticleService;


import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import ds.pirate.backend.vo.search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import ds.pirate.backend.service.AlarmService.AlarmService;
import ds.pirate.backend.service.UserService.UserService;
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
    private final UserService uservice;
    private final AlarmService alser;

    @Override
    public List<acommentDTO> getListByUserIdAndAuthorId(Long userid, Long authorid) {
        List<acommentDTO> dtos = crepo.getListByUserIdAndAuthorId(userid, authorid).get().stream().map((Function<acomments, acommentDTO>) v->{
            acommentDTO result = commentEntityToDTO(v);
            return result;
        }).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public String ArticleModify(ArticleDTO dto, List<String> tags) {
        ArticlesList origEntity = repo.findByAid(dto.getAid());
        ArticleDTO getByAid =  EntityToDTO(origEntity); 

        origEntity.getImages().forEach(image->{
            irepo.deleteById(image.getIid());;
        });
        origEntity.getTags().forEach(tag->{
            hrepo.deleteById(tag.getHid());
        });

        getByAid.setAtitle(dto.getAtitle());
        getByAid.setContext(dto.getContext());
        getByAid.setOpened((dto.isOpened()));
        getByAid.setShareable((dto.isShareable()));
        ArticlesList modifiedArticle = dtoToEntity(getByAid);
        repo.save(modifiedArticle);

        List<ImagesList> lists = dto.getImages();
        lists.forEach(new Consumer<ImagesList>() {
            @Override
            public void accept(ImagesList i) {
                if(!irepo.findById(i.getIid()).isPresent()){
                    ImagesList.builder().fileName(i.getFileName()).articles(modifiedArticle).build();
                }
            }
        });

        for (String i : tags) {
            Optional<HashTags> hashTemp = hrepo.findByArticlesAndHashTagName(modifiedArticle, i);
            if (!hashTemp.isPresent()) {
                HashTags hresult = listToHSEntity(i);
                hresult.updateArticles(modifiedArticle);
                hrepo.save(hresult);
            }
        }
        return modifiedArticle.getAid().toString();
    }

    @Override
    public ArticleDTO CheckBeforeModifyArticle(Long aid, Long userid) {
        Optional<ArticlesList> isit = repo.getArticleByAidAndUserId(aid, userid);
        if(!isit.isPresent()){
            return null;
        }else{
            ArticleDTO dto = EntityToDTO(isit.get());
            List<String> hashString = hrepo.getList(dto.getAid())
                .stream()
                .map(hentity -> hentity.getHashTagName())
                .collect(Collectors.toList());
            dto.setTags(hashString);
            return dto;
        }
    }

    //이부분 카드 내용 참고!
    @Override
    public HashMap<String, Object> getCardInfosByHashTagName(Long aid, Pageable pageable) {
        HashMap<String, Object> cardInfo = new HashMap<>();
        
        Page<HashTags> result = hrepo.findByHashTagNameContainsIgnoreCaseOrderByHidDesc(repo.getByAid(aid).getTags().get(0).getHashTagName(), pageable);
        List<ArticleDTO> aresult = result.get().map((Function<HashTags, ArticleDTO>) dto->{
            ArticleDTO dtoresult = EntityToDTO(dto.getArticles());
            return dtoresult;
        }).collect(Collectors.toList());
        List<String> uresult = result.get().map((Function<HashTags, String>) dto->{
            
            String dtoresult = uservice.entityToDTO(urepo.findByUserId(dto.getArticles().getAUser()).get()).getAirName();
            return dtoresult;
        }).collect(Collectors.toList());


            
        cardInfo.put("articles", aresult);
        cardInfo.put("page", pageable.getPageNumber());
        cardInfo.put("pageTotalCount", result.getTotalPages());
        cardInfo.put("userInfo", uresult);

        return cardInfo;
    }

    @Override
    public List<ArticleDTO> getSearchCardInfo(Long aid) {
        String hashname = hrepo.findByArticles(ArticlesList.builder().aid(aid).build()).get(0).getHashTagName();
        List<ArticleDTO> articleResult = hrepo.getAidListByHashTagName(hashname).get().stream()
                .map((Function<? super HashTags, ArticleDTO>) m -> {
                    ArticleDTO dto = EntityToDTO(repo.getByAid(m.getArticles().getAid()));
                    if (dto.isShareable()) {
                        return dto;
                    } else {
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
        result.put("favo", favoCheck.isPresent() == true ? true : false);
        result.put("report", reportCheck.isPresent() == true ? true : false);
        result.put("save", saveCheck.isPresent() == true ? true : false);
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
        acomments result = crepo.getCommentByCidAndUserid(dto.getCid(), dto.getCommentUserid()).get();
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
            ctrepo.save(acommentRate.builder().commentid(cdto.getCid()).userid(dto.getUserid()).updown(dto.getUpdown())
                    .build());
            return "등록되었습니다";
        }
    }

    @Override
    public Long addNewComment(acommentDTO dto) {
        Optional<airUser> result = urepo.findByEmail(dto.getEmail());
        Optional<acomments> checkingAirUser = crepo.getCommentByAidAndUserid(ArticlesList.builder().aid(dto.getAid()).build(), airUser.builder().userid(dto.getUserid()).build());
        if (!checkingAirUser.isPresent()) {
            Optional<List<Long>> latestg = crepo.getLatestCommentGroupWhereMatchWithAid(dto.getAid());
            Long setcg = 1L;
            if (latestg.get().size()!=0) {
                setcg = latestg.get().get(0)+1;
            }

            
            dto.setCommentGroup(setcg);
            dto.setCommnetDepth(0L);
            dto.setCommentSorts(0L);
            dto.setUserid(result.get().getUserid());
            dto.setRate(0);
            acomments entity = commentDTOtoEntity(dto);
            crepo.save(entity);
            alser.addAlarm(entity);
            return -1L;
        } else {
            return checkingAirUser.get().getCid();
        }

    }

    @Override
    public Long addNewCommentReply(acommentDTO dto) {
        Optional<airUser> result = urepo.findByEmail(dto.getEmail());
        log.info(result+"+아니 뭐");
        dto.setCommentGroup(dto.getCommentGroup());
        dto.setCommnetDepth(dto.getCommnetDepth());
        dto.setCommentSorts(dto.getCommentSorts());
        dto.setUserid(result.get().getUserid());
        dto.setRate(0);

        acomments entity = commentDTOtoEntity(dto);

        crepo.save(entity);
        alser.addAlarm(entity);

        return entity.getCid();
    }

    @Override
    public HashMap<String, Object> getCommentListByAid2(Long aid, Pageable pageable) {
        Page<acomments> cmlist = crepo.getPageList(pageable, aid);
        if (!cmlist.isEmpty()) {
            List<acommentDTO> dto = cmlist
                    .stream()
                    .map((Function<acomments, acommentDTO>) cmt -> {
                        log.info(cmt);
                        return commentEntityToDTO(cmt);
                    })
                    .collect(Collectors.toList());
            HashMap<String, Object> result = new HashMap<>();
            result.put("commentList", dto);
            result.put("page", pageable.getPageNumber());
            result.put("pageTotalCount", cmlist.getTotalPages());
            return result;
        }
        return null;
    }

    public HashMap<String, Object> getCommentListByAidTwo2(Long aid, Pageable pageable, Long userid) {
        Page<acomments> cmlist = crepo.getPageList(pageable, aid);
        if (!cmlist.isEmpty()) {
            List<acommentDTO> dto = cmlist
                    .stream()
                    .map((Function<acomments, acommentDTO>) cmt -> {
                        return commentEntityToDTO(cmt);
                    })
                    .collect(Collectors.toList());
            dto.forEach((t) -> {
                Optional<acommentRate> israted = ctrepo.getIsRatedByCidAndUserid(t.getCid(), userid);
                if (israted.isPresent()) {
                    t.setIsrated(israted.get().getUpdown());
                }
            });
            HashMap<String, Object> result = new HashMap<>();
            result.put("commentList", dto);
            result.put("page", pageable.getPageNumber());
            result.put("pageTotalCount", cmlist.getTotalPages());
            return result;
        }
        return null;
    }

    @Transactional
    @Override
    public ArticleDTO getArticleInfoByAid(Long aid) {
        ArticlesList result = repo.getByAid(aid);
        ArticleDTO dto = EntityToDTO(result);
        List<String> hashString = hrepo.getList(result.getAid())
                .stream()
                .map(hentity -> hentity.getHashTagName())
                .collect(Collectors.toList());
        dto.setTags(hashString);
        repo.updateOpencount(aid);
        return dto;
    }

    @Override
    public String addArticle(ArticleDTO dto, List<String> tags) {
        dto.setOpencount(0L);
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
        if (subcount==null) {
            subcount=0L;            
        }
        result.put("articleUserName", articleUserEntity.getAirName());
        result.put("articleUserImg", "./images/read/userid/" + (articleUserId.toString()));
        result.put("isgudoked", subchecking.isPresent() ? "true" : "false");
        result.put("subCount", subcount.toString());
        return result;
    }

    @Override
    public String subsFunction(Long aid, Long userid) {
        Long articleUserId = repo.getArticleAuthorIdByAid(aid);
        Optional<subscribList> subchecking = surepo.getIsSubcedByTargetIdAndUserid(articleUserId, userid);
        log.info(articleUserId+"아니왜들어가는거야"+userid);
        airUser articleUserEntity = urepo.findByUserId(userid).get();
        if (subchecking.isPresent()) {
            surepo.delete(subchecking.get());
            return "구독이 해지되었습니다";
        } else {
            surepo.save(subscribList.builder().targetId(articleUserId).userid(articleUserEntity).build());
            return "구독되었습니다";
        }

    }

    @Override
    public List<Object[]> getArticleList() {
        return repo.getListAndAuthor();
    }

    @Override
    public List<Object[]> getSearchArticleList(String search) {
        String decode = "";
        try{
            decode = URLDecoder.decode(search, "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return repo.getListAndAuthorByAuthorOrAtitle(decode);
    }


    @Override
    public Optional<Object[]> aaaaa(String search) {
        Optional<Object[]> result = repo.aaaaaa(search);
        return result;
    }
}
