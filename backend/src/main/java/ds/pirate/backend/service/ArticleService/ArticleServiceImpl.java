package ds.pirate.backend.service.ArticleService;

import java.util.List;

import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ArticleServiceImpl implements ArticleService{
    private final ArticleRepository repo;

    @Override
    public String addArticle(ArticleDTO dto, List<String> tags) {
        repo.save(dtoToEntity(dto));
        
        return "done";
    }
}
