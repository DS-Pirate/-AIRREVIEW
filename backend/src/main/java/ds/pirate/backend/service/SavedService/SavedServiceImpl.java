package ds.pirate.backend.service.SavedService;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.entity.SaveList;
import ds.pirate.backend.repository.ArticleRepository;
import ds.pirate.backend.repository.SavedRepository;
import ds.pirate.backend.service.ArticleService.ArticleService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class SavedServiceImpl implements SavedService {
  private final SavedRepository savrepo;
  private final ArticleRepository arepo;
  private final ArticleService aService;

  @Override
  public List<ArticleDTO> getListAid(Long aid) {
    List<ArticleDTO> result = savrepo.getList(aid).get().stream()
        .map((Function<? super SaveList, ? extends ArticleDTO>) v -> {
          ArticleDTO list = aService.EntityToDTO(arepo.getByAid(v.getAid()));
          return list;
        }).collect(Collectors.toList());
    return result;
  }
};
