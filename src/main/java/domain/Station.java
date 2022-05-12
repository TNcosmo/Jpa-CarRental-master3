package domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import java.util.Objects;

@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Station {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Station station = (Station) o;
        return id != null && Objects.equals(id, station.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @GeneratedValue
    @Id
    private Long id;

    @Column(unique = true)
    private String title;
}
