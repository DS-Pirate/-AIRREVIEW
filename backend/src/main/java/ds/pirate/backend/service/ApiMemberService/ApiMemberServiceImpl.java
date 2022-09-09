package ds.pirate.backend.service.ApiMemberService;

import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ApiMemberServiceImpl implements ApiMemberService{
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @Transactional
    @Override
    public String register(airUserDTO dto) {
        dto.setPasswd(encoder.encode(dto.getPasswd()));
        repository.save(dtoToEntity(dto));
        return "회원가입에 성공하였습니다";

    }
}
