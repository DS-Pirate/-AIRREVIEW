package ds.pirate.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class acommentRate extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long crid;
    
    @Column(nullable = false)
    private Integer rate;

    @Column(nullable = false)
    private Long commentGroup;

    @Column(nullable = false)
    private Long commnetDepth;

    @Column(nullable = false)
    private Long commentSorts;
    @ManyToOne
    private acomments commentid;
}

