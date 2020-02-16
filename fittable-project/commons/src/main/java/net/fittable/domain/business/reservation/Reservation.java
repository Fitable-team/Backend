package net.fittable.domain.business.reservation;

import lombok.*;
import net.fittable.domain.authentication.ClientMember;
import net.fittable.domain.business.BatchDeletable;
import net.fittable.domain.business.Review;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "RESERVATION")
@Data
@NoArgsConstructor
public class Reservation implements BatchDeletable {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private ClientMember reservedClient;

    @ManyToOne
    @JoinColumn(name = "TARGET_SLOT_ID")
    private Session targetSession;

    @OneToOne(mappedBy = "originatedReservation", cascade = CascadeType.PERSIST)
    private Review userReview;

    private int requestedCapacity = 1;
    private boolean used = false;
    private boolean accepted = false;

    @Setter(AccessLevel.NONE)
    private boolean canceled = false;

    @CreatedDate
    private LocalDateTime reservedDateTime;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @Setter(AccessLevel.NONE)
    private LocalDateTime canceledDateTime;

    @Builder
    public Reservation(ClientMember reservedClient, Session targetSession, int requestedCapacity) {
        this.reservedClient = reservedClient;
        this.targetSession = targetSession;
        this.requestedCapacity = requestedCapacity;
    }

    public void setRequestedCapacity(int requestedCapacity) {
        if(this.targetSession.getCapacity() <= requestedCapacity) {
            throw new IllegalArgumentException("예약 요청 인원이 열려있는 인원을 초과하였습니다.");
        }
        this.requestedCapacity = requestedCapacity;
    }

    public void markAsCanceled() {
        this.canceledDateTime = LocalDateTime.now();

        this.canceled = true;
    }

    public void markAsAccepted() {
        this.accepted = true;
    }

    public boolean userHasWroteReview() {
        return this.userReview != null;
    }

    public boolean isEligibleForWritingReview() {
        return this.userReview == null;
    }

    @Override
    public boolean isInBatchEvictionTarget() {
        return this.canceled;
    }
}
