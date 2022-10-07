package ds.pirate.backend.service.SavedService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.ImagesList;
import ds.pirate.backend.entity.SaveList;
import ds.pirate.backend.entity.likeUnlikeList;
import ds.pirate.backend.repository.ArticleRepository;
import ds.pirate.backend.repository.SavedRepository;
import ds.pirate.backend.repository.UserRepository;
import ds.pirate.backend.service.ArticleService.ArticleService;
import ds.pirate.backend.service.UserService.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2

public class SavedServiceImpl implements SavedService {
  private final SavedRepository savrepo;
  private final ArticleRepository arepo;
  private final ArticleService aService;
  private final UserRepository urepo;
  private final UserService uService;

  @Override
  public HashMap<String, Object> getListAid(Long userid) {
    List<ImagesList> imageList = new ArrayList<>();
    imageList.add(0, ImagesList.builder().fileName("basic.png").build());
    List<airUserDTO> user = new ArrayList<>();
    Optional<List<SaveList>> tmp = savrepo.getList(userid);

    List<ArticleDTO> result = tmp.get().stream().map((Function<SaveList, ArticleDTO>) v -> {
      ArticlesList tmp2 = arepo.findByAid(v.getAid());
      ArticleDTO dto = aService.EntityToDTO(tmp2);
      user.add(uService.entityToDTO(urepo.findByUserId(dto.getUserId()).get()));

      if (!tmp2.getImages().isEmpty()) {
        dto.setImages(tmp2.getImages());
      } else {
        dto.setImages(imageList);
      }

      return dto;
    }).toList();

    result.forEach(v -> {
      log.info(v);
    });

    List<String> usernames = user.stream().map((Function<airUserDTO, String>) v -> {
      return v.getAirName();
    }).collect(Collectors.toList());

    HashMap<String, Object> hash = new HashMap<>();
    hash.put("card", result);
    hash.put("username", usernames);
    hash.put("imageList", imageList);
    return hash;
  }
};
