package net.fittable.domain.business;

import lombok.Data;
import net.fittable.domain.authentication.Member;
import net.fittable.domain.authentication.enums.MemberAuthority;
import net.fittable.domain.business.reservation.Reservation;

import javax.persistence.*;

@Entity
@Table(name = "STORE_REVIEW")
@Data
public class Review {

    @Id
    @GeneratedValue
    private long id;

    private String content;
    private double starPoint;

    private String ownersReply;

    private boolean deleted;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "RESERVATION_ID")
    private Reservation originatedReservation;

    @ManyToOne
    @JoinColumn(name = "STORE_ID")
    private Studio targetStudio;

    public boolean isEligibleForWritingReply(Member member) {
        if(member.getAuthority() == MemberAuthority.ADMIN) {
            return true;
        }

        if(member.getAuthority() == MemberAuthority.MEMBER) {
            throw new IllegalArgumentException("일반회원은 댓글을 작성할 수 없습니다.");
        }

        return this.originatedReservation.getTargetSession().getTargetStudio().getOwner().equals(member);
    }
}
