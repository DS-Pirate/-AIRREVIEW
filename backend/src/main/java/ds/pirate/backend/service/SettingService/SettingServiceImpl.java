package ds.pirate.backend.service.SettingService;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.repository.UserRepository;
import ds.pirate.backend.service.UserService.UserService;
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

  @Transactional
  @Override
  public String changePasswd(airUserDTO dto) {
    dto.setPasswd(encoder.encode(dto.getPasswd()));
    urepo.save(dtoToEntity(dto));
    return "비밀번호를 변경하였습니다";
  }

}
