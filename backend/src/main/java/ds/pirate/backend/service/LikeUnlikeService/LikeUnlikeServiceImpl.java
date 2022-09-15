package ds.pirate.backend.service.LikeUnlikeService;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.entity.likeUnlikeList;
import ds.pirate.backend.repository.ArticleRepository;
import ds.pirate.backend.repository.LikeUnlikeRepository;
import ds.pirate.backend.service.ArticleService.ArticleService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class LikeUnlikeServiceImpl implements LikeUnlikeService {
  private final LikeUnlikeRepository likeunlikerepo;
  private final ArticleRepository arepo;
  private final ArticleService aService;

  @Override
  public List<ArticleDTO> getListAid(Long aid) {
    List<ArticleDTO> result = likeunlikerepo.getList(aid).get().stream()
        .map((Function<? super likeUnlikeList, ? extends ArticleDTO>) v -> {
          ArticleDTO list = aService.EntityToDTO(arepo.getByAid(v.getAid()));
          return list;
        }).collect(Collectors.toList());
    return result;
  }
};
