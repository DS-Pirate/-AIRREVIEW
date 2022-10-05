package ds.pirate.backend.service.LikeUnlikeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.entity.likeUnlikeList;
import ds.pirate.backend.entity.uImagesList;
import ds.pirate.backend.repository.ArticleRepository;
import ds.pirate.backend.repository.LikeUnlikeRepository;
import ds.pirate.backend.repository.UserImageListRepository;
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
  private final UserImageListRepository uilrepo;

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
  public String getUImageNameByUserid(Long userid) {
    Optional<uImagesList> entity = uilrepo.getByAiruser(userid);
    if (entity.isPresent()) {
      return entity.get().getFileName();
    } else {
      return "basic.png";
    }
  }
};
