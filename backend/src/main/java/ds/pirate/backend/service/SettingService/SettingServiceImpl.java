package ds.pirate.backend.service.SettingService;

import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.repository.UserRepository;
import ds.pirate.backend.service.UserService.UserService;
import ds.pirate.backend.vo.userid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SettingServiceImpl implements SettingService {
  private final UserRepository urepo;
  private final UserService uService;

  @Override
  public airUserDTO getByUserId(Long userid) {
    airUserDTO result = uService.entityToDTO(urepo.findByUserId(userid).get());
    return result;
  }

  @Override
  public String changePasswd(userid vo) {
    
    


    
    return "변경";
  }
}
