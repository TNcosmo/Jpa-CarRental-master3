package domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Rental {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Rental rental = (Rental) o;
        return id != null && Objects.equals(id, rental.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @GeneratedValue
    @Id
    private Long id;

    private Double drivenKm;

    private LocalDateTime beginning;

    @Column(name = "ending")
    private LocalDateTime end;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Station rentalStation;

    @ManyToOne
    private Station returnStation;

    @AssertTrue
    private boolean isAllNullOrAllNotNull() {
        return drivenKm == null && returnStation == null && end == null ||
                drivenKm != null && returnStation != null && end != null;
    }

    public void update(Station station, double drivenKm, LocalDateTime dateTime) {
        returnStation = station;
        this.drivenKm = drivenKm;
        end = dateTime;
    }
}


