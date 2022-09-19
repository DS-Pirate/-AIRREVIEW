package ds.pirate.backend.service.EmbedService;


import org.springframework.stereotype.Service;

import ds.pirate.backend.repository.ArticleRepository;
import ds.pirate.backend.vo.Embed;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmbedServiceImpl implements EmbedService{
    private final ArticleRepository arepo;

    @Override
    public Embed getEmbedInfo(Long article) {
        ArticleRepository.getEmbedInformation geted = arepo.getEmbedInfoByAid(article).get();
        Embed result = new Embed(geted);
        log.info(result);
        

        return result;
    }

}
