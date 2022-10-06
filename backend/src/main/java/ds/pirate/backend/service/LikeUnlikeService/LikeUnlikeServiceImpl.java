package ds.pirate.backend.service.LikeUnlikeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.HashTags;
import ds.pirate.backend.entity.ImagesList;
import ds.pirate.backend.entity.likeUnlikeList;
import ds.pirate.backend.repository.ArticleRepository;
import ds.pirate.backend.repository.HashTagRepository;
import ds.pirate.backend.repository.ImageRepository;
import ds.pirate.backend.repository.LikeUnlikeRepository;
import ds.pirate.backend.repository.UserRepository;
import ds.pirate.backend.service.ArticleService.ArticleService;
import ds.pirate.backend.service.UserService.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class LikeUnlikeServiceImpl implements LikeUnlikeService {
  private final LikeUnlikeRepository likeunlikerepo;
  private final ArticleRepository arepo;
  private final ArticleService aService;
  private final UserRepository urepo;
  private final UserService uService;
  private final ImageRepository irepo;
  private final HashTagRepository hrepo;
  private final LikeUnlikeRepository lurepo;

  @Override
  public HashMap<String, Object> getListAid(Long aid) {
    List<airUserDTO> user = new ArrayList<>();
    List<ArticleDTO> result = likeunlikerepo.getList(aid).get().stream()
        .map((Function<? super likeUnlikeList, ? extends ArticleDTO>) v -> {
          ArticleDTO list = aService.EntityToDTO(arepo.getByAid(v.getAid()));
          user.add(uService.entityToDTO(urepo.findByUserId(list.getUserId()).get()));
          return list;
        }).collect(Collectors.toList());

    List<String> usernames = user.stream().map((Function<airUserDTO, String>) v -> {
      return v.getAirName();
    }).collect(Collectors.toList());

    HashMap<String, Object> hash = new HashMap<>();
    hash.put("card", result);
    hash.put("username", usernames);
    return hash;
  }

  @Override
  public HashMap<String, Object> getCardInfosByHashTagName(Long aid, Pageable pageable) {
    HashMap<String, Object> card = new HashMap<>();
    List<String> ImageList = new ArrayList<>();
    Page<HashTags> result = hrepo.findByHashTagNameContainsIgnoreCaseOrderByHidDesc(
        Optional.ofNullable(lurepo.getByAid(aid).getTags().get(0).getHashTagName()).get(), pageable);
    List<ArticleDTO> aresult = result.get().map((Function<HashTags, ArticleDTO>) dto -> {
      ArticleDTO dtoresult = aService.EntityToDTO(dto.getArticles());
      Optional<ImagesList> tmpImage = irepo
          .findFirstByArticlesOrderByIidAsc(ArticlesList.builder().aid(dtoresult.getAid()).build());
      if (tmpImage.isPresent()) {
        ImageList.add(tmpImage.get().getFileName().strip().substring(0,
            tmpImage.get().getFileName().strip().length() - 1));
      } else {
        ImageList.add("basic.png");
      }
      return dtoresult;
    }).collect(Collectors.toList());
    List<String> uresult = result.get().map((Function<HashTags, String>) dto -> {

      String dtoresult = uService.entityToDTO(urepo.findByUserId(dto.getArticles().getAUser()).get())
          .getAirName();
      return dtoresult;
    }).collect(Collectors.toList());
    card.put("articles", aresult);
    card.put("page", pageable.getPageNumber());
    card.put("pageTotalCount", result.getTotalPages());
    card.put("userInfo", uresult);
    card.put("ImageList", ImageList);
    return card;
  }
}
