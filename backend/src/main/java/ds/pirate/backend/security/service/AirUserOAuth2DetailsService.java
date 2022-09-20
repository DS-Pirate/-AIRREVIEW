package ds.pirate.backend.security.service;

import ds.pirate.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class AirUserOAuth2DetailsService extends DefaultOAuth2UserService {

    private final UserRepository repository;

    private final PasswordEncoder encoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("loadUser..... userRequest: " + userRequest); //받은 변수
        String clientName = userRequest.getClientRegistration().getClientName(); //받은 클라이언트 이름
        log.info("clientName: " + clientName);
        log.info(userRequest.getAdditionalParameters()); // 구글로 부터 오는 정보를 확인
        OAuth2User oAuth2User = super.loadUser(userRequest);//세션 획득
        oAuth2User.getAttributes().forEach((k, v) -> {
            log.info(k + ":" + v);
        });

        return super.loadUser(userRequest);
    }
}
