package ds.pirate.backend.service.ApiMemberService;

import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.entity.emailAuth;
import ds.pirate.backend.repository.MailRepository;
import ds.pirate.backend.repository.UserRepository;
import ds.pirate.backend.vo.findpass;
import ds.pirate.backend.vo.setpass;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiMemberServiceImpl implements ApiMemberService{
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final MailRepository mrepo;

    @Transactional
    @Override
    public String register(airUserDTO dto) {
        dto.setPasswd(encoder.encode(dto.getPasswd()));
        Optional<emailAuth> resultcheck = mrepo.getByEmail(dto.getEmail());
        if(resultcheck.get().getEmail().length()>0&&resultcheck.get().getIsAuthrized()==true){
            repository.save(dtoToEntity(dto));
            return "회원가입이 완료되었습니다.";
        } else{
            return "회원가입에 실패하였습니다.";
        }

    }

    @Override
    public Long findPass(findpass vo) {
        Long result = repository.findUserIdByEmailAndQ(vo.getEmail(),vo.getQ1(), vo.getQ2(), vo.getQ3());
        if(result == null){
            return null;
        } else {
            return result;
        }
    }

    @Override
    public String changePass(setpass vo) {
        repository.changePassbyId(vo.getUserid(), encoder.encode(vo.getPasswd()));
        return "비밀번호를 변경하였습니다.";
    }

    @Override
    public String findEmail(airUserDTO email) {
        Optional<airUser> result = repository.findByEmail(email.getEmail());
        if (result.isEmpty()) {
            return null;
        }
        return "존재하는 이메일입니다!";
    }
}
