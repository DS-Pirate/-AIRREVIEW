package ds.pirate.backend.service.ArticleService;

import java.util.List;

import org.springframework.stereotype.Service;
import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.HashTags;
import ds.pirate.backend.entity.ImagesList;
import ds.pirate.backend.repository.ArticleRepository;
import ds.pirate.backend.repository.HashTagRepository;
import ds.pirate.backend.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ArticleServiceImpl implements ArticleService{
    private final ArticleRepository repo;
    private final HashTagRepository hrepo;
    private final ImageRepository irepo;

    @Override
    public String getArticleInfoByAid(Long aid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String addArticle(ArticleDTO dto, List<String> tags) {
        ArticlesList result = dtoToEntity(dto);
        repo.save(result);

        List<ImagesList> lists = dto.getImages();
        log.info("얘느ㅜㄴ 뭐라찍히냐"+lists);
        log.info("Tostirng치면 ?"+lists.toString());
        lists.forEach(i->{
            log.info("아니 얘 왜 값이 없냐"+i.getFileName());
            
            irepo.save(ImagesList.builder().fileName(i.getFileName()).articles(result).build());
        });

        for(String i : tags){
            HashTags hresult = listToHSEntity(i);
            hresult.updateArticles(result);
            hrepo.save(hresult);
        }



        
        return result.getAid().toString();
    }
}

