package ds.pirate.backend.service.ChannelService;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.entity.uImagesList;
import ds.pirate.backend.repository.ArticleRepository;
import ds.pirate.backend.repository.UserImageListRepository;
import ds.pirate.backend.repository.UserRepository;
import ds.pirate.backend.repository.ArticleRepository.getMyChannelArticleList;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChannelServiceImpl implements ChannelService{
    private final UserRepository urepo;
    private final ArticleRepository arepo;
    private final UserImageListRepository uirepo;

    @Override
    public void uploadCHImage(Long userid, String fileName) {
        Optional<uImagesList> isimage = uirepo.getCHImageByUserId(userid);
        if(isimage.isPresent()){
            urepo.findByUserId(isimage.get().getAiruser().getUserid()).get().getUserImg().removeIf(isimg->{return isimg.getAiruser().getUserid().equals(isimage.get().getAiruser().getUserid());});
            uirepo.deleteById(isimage.get().getIid());    
        }
        uirepo.save(uImagesList.builder().airuser(airUser.builder().userid(userid).build()).fileName(fileName).idx(99).build());
    }
    @Override
    public Page<getMyChannelArticleList> articleListByUserid(Long userid, Integer pageNum, String search) {
        Pageable pageable = PageRequest.of(pageNum, 10, Sort.by(Sort.Direction.DESC, "aid"));
        log.info("page:::::::::::::" + pageNum);
        if (search != null){
            return arepo.getArticleListByUserIdAndSearchWithPageable(userid,search,pageable);
        }
        return arepo.getArticleListByUserIdWithPageable(userid, pageable);
    }

    @Override
    public String getCHImage(Long userid) {
        Optional<uImagesList> result = uirepo.getCHImageByUserId(userid);
        if (result.isPresent()) {
            return uirepo.getCHImageByUserId(userid).get().getFileName();    
        }else{
            return "basic.png";
        }
        
    }
}
