package ds.pirate.backend.service.SubscribeService;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.entity.subscribList;
import ds.pirate.backend.repository.ArticleRepository;
import ds.pirate.backend.repository.SubscribeRepository;
import ds.pirate.backend.service.ArticleService.ArticleService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class SubscribeServiceImpl implements SubscribeService {
  private final SubscribeRepository subrepo;
  private final ArticleRepository arepo;
  private final ArticleService aService;

  @Override
  public List<ArticleDTO> getListAid(Long aid) {
    List<ArticleDTO> result = subrepo.getByUserId(aid).get().stream()
        .map((Function<? super subscribList, ? extends ArticleDTO>) v -> {
          ArticleDTO list = aService.EntityToDTO(arepo.getByAid(v.getTargetId()));
          return list;
        }).collect(Collectors.toList());
    return result;
  }
}
