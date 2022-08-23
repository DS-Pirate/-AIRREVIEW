package ds.pirate.backend.service.ArticleService;

import java.util.List;

import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.HashTags;
import ds.pirate.backend.repository.ArticleRepository;
import ds.pirate.backend.repository.HashTagRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ArticleServiceImpl implements ArticleService{
    private final ArticleRepository repo;
    private final HashTagRepository hrepo;
    @Override
    public String addArticle(ArticleDTO dto, List<String> tags) {
        ArticlesList result = dtoToEntity(dto);
        repo.save(result);
        for(String i : tags){
            HashTags hresult = listToHSEntity(i);
            hresult.updateArticles(result);
            hrepo.save(hresult);
        }

        
        return "done";
    }
}
