package ds.pirate.backend.service.ArticleService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.dto.acommentDTO;
import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.HashTags;
import ds.pirate.backend.entity.ImagesList;
import ds.pirate.backend.entity.acomments;
import ds.pirate.backend.repository.ArticleRepository;
import ds.pirate.backend.repository.CommentRepository;
import ds.pirate.backend.repository.HashTagRepository;
import ds.pirate.backend.repository.ImageRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{
    private final ArticleRepository repo;
    private final HashTagRepository hrepo;
    private final ImageRepository irepo;
    private final CommentRepository crepo;
    @Override
    public List<acommentDTO> getCommentListByAid(Long aid) {
        Optional<List<acomments>> entity = crepo.getListByAid(aid);
        
        if (entity.isPresent()){
            List<acommentDTO> dto = entity.get()
                                            .stream()
                                            .map(cmt -> commentEntityToDTO(cmt))
                                            .collect(Collectors.toList());
            return dto;
        }
        return null;
    }

    @Override
    public ArticleDTO getArticleInfoByAid(Long aid) {
        ArticlesList result = repo.getByAid(aid);
        ArticleDTO dto = EntityToDTO(result);
        List<String> hashString =  hrepo.getList(result.getAid())
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
        lists.forEach(i->{
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

