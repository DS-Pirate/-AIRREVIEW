package ds.pirate.backend.service.SavedService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.entity.SaveList;
import ds.pirate.backend.repository.ArticleRepository;
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

  @Override
  public HashMap<String, Object> getListAid(Long aid) {
    List<airUserDTO> user = new ArrayList<>();
    List<ArticleDTO> result = savrepo.getList(aid).get().stream()
        .map((Function<? super SaveList, ? extends ArticleDTO>) v -> {
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
};
