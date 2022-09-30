package ds.pirate.backend.service.MailService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import ds.pirate.backend.entity.emailAuth;
import ds.pirate.backend.repository.MailRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final AirMailSender mailSender;
    private final MailRepository mrepo;

    @Override
    public boolean mailCheckingRequest(String useremail) {
        Integer authnum = (int) (Math.floor(Math.random() * 10000) - 1);
        Optional<emailAuth> isCheck = mrepo.getByEmail(useremail);
        if(isCheck.isPresent()){
            mrepo.delete(isCheck.get());
        }
        mrepo.save(emailAuth.builder().email(useremail).authNum(authnum).build());
        mailSender.send(useremail, "[에어리뷰] 이메일 인증을 위한 인증번호를 안내 드립니다.", "인증번호 : " + authnum);
        return true;
    }

    @Override
    @Transactional
    public String mailCheckingNumCheckRequest(String useremail, Integer authNum) {
        Optional<emailAuth> isCheck = mrepo.getByEmail(useremail);
        if(isCheck.isPresent()){
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime pastTime = isCheck.get().getRegDate().plusMinutes(3);
            if(ChronoUnit.SECONDS.between(now, pastTime)<=0){
                return "인증시간이 만료되었습니다";
            }
            if(isCheck.get().getAuthNum().intValue() == authNum.intValue() && isCheck.get().getIsAuthrized()==false){
                mrepo.updateAuthTime(isCheck.get().getEauthId(), isCheck.get().getRegDate().plusMinutes(60));
                return "인증이 완료되었습니다 1시간 이내에 가입을 완료해주세요";
            }else if (isCheck.get().getAuthNum().intValue() == authNum.intValue() && isCheck.get().getIsAuthrized()==true){
                return "이미 인증된 이메일입니다 가입을 완료해주세요";
            }else{
                return "인증 번호가 잘못되었습니다";
            }
        }else{
            return "잘못된 접근입니다";
        }
    }
}
