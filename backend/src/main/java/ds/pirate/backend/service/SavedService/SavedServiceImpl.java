package ds.pirate.backend.service.SavedService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.dto.ImageListDTO;
import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.SaveList;
import ds.pirate.backend.repository.ArticleRepository;
import ds.pirate.backend.repository.ImageRepository;
import ds.pirate.backend.repository.SavedRepository;
import ds.pirate.backend.repository.UserRepository;
import ds.pirate.backend.service.ArticleService.ArticleService;
import ds.pirate.backend.service.UserService.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class SavedServiceImpl implements SavedService {
  private final SavedRepository savrepo;
  private final ArticleRepository arepo;
  private final ArticleService aService;
  private final UserRepository urepo;
  private final UserService uService;
  private final ImageRepository irepo;

  @Override
  public HashMap<String, Object> getListAid(Long userid) {
    List<ImageListDTO> imageList = new ArrayList<>();

    List<airUserDTO> user = new ArrayList<>();
    Optional<List<SaveList>> tmp = savrepo.getList(userid);

    List<ArticleDTO> result = tmp.get().stream().map((Function<SaveList, ArticleDTO>) v -> {
      ArticlesList tmp2 = arepo.findByAid(v.getAid());
      ArticleDTO dto = aService.EntityToDTO(tmp2);
      irepo.findFirstByArticlesOrderByIidAsc(tmp2).ifPresentOrElse(set->{imageList.add(ImageListDTO.builder().fileName(set.getFileName()).build());}, ()->imageList.add(ImageListDTO.builder().fileName("basic.png").build()));
      user.add(uService.entityToDTO(urepo.findByUserId(dto.getUserId()).get()));
      return dto;
    }).toList();


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
