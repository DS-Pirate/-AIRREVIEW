package ds.pirate.backend.service.SubscribeService;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.entity.subscribList;
import ds.pirate.backend.repository.SubscribeRepository;
import ds.pirate.backend.repository.UserRepository;
import ds.pirate.backend.service.UserService.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class SubscribeServiceImpl implements SubscribeService {
  private final SubscribeRepository subrepo;
  private final UserRepository urepo;
  private final UserService uService;

  @Override
  public List<airUserDTO> getListUserId(Long userid) {
    List<airUserDTO> result = subrepo.getByUserId(userid).get().stream()
        .map((Function<? super subscribList, ? extends airUserDTO>) v -> {
          airUserDTO list = uService.entityToDTO(urepo.findByUserId(v.getTargetId()).get());
          return list;
        }).collect(Collectors.toList());
    return result;
  }
}
