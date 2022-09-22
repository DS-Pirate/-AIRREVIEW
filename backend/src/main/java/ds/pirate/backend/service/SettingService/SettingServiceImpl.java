package ds.pirate.backend.service.SettingService;

import org.springframework.security.crypto.password.PasswordEncoder;
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
  private final PasswordEncoder encoder;

  @Override
  public airUserDTO getByUserId(Long userid) {
    airUserDTO result = uService.entityToDTO(urepo.findByUserId(userid).get());
    return result;
  }

  @Override
  public String changePasswd(userid vo) {
    urepo.changePasswdbyIdAndcpass(vo.getUserid(), vo.getName(), vo.getEmail(), vo.getBirthday(), vo.getUserintro(),
        encoder.encode(vo.getCpasswd()), encoder.encode(vo.getUpasswd()));

    return "변경";
  }
}
