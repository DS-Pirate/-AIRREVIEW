package ds.pirate.backend.service.MailService;

public interface MailService {
    
    boolean mailCheckingRequest(String useremail);
    String mailCheckingNumCheckRequest(String useremail, Integer authNum);
}
