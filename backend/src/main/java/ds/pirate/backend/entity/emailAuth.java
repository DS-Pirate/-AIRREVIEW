package ds.pirate.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class emailAuth extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long eauthId;

    @Column(nullable = false)
    String email;
    
    @Column(nullable = false)
    Integer authNum;    

    @ColumnDefault("0")
    Boolean isAuthrized;
}
