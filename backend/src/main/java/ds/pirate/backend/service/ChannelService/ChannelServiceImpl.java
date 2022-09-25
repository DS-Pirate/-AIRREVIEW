package ds.pirate.backend.service.ChannelService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.entity.uImagesList;
import ds.pirate.backend.repository.ArticleRepository;
import ds.pirate.backend.repository.UserImageListRepository;
import ds.pirate.backend.repository.ArticleRepository.getMyChannelArticleList;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChannelServiceImpl implements ChannelService{
    private final ArticleRepository arepo;
    private final UserImageListRepository uirepo;

    @Override
    public void uploadCHImage(Long userid, String fileName) {
        uirepo.save(uImagesList.builder().airuser(airUser.builder().userid(userid).build()).fileName(fileName).idx(99).build());
    }
    @Override
    public Page<getMyChannelArticleList> articleListByUserid(Long userid, Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, 10);
        log.info("page:::::::::::::" + pageNum);
        return arepo.getArticleListByUserIdWithPageable(userid, pageable);
    }

    @Override
    public String getCHImage(Long userid) {
        uirepo.getCHImageByUserId(userid).getFileName();
        return uirepo.getCHImageByUserId(userid).getFileName();
    }
}
