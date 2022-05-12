package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Entity
@NoArgsConstructor


public class Car {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Car car = (Car) o;
        return plate != null && Objects.equals(plate, car.plate);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Id
    @Length(max = 9, min = 4)
    private String plate;

    @PositiveOrZero
    private double mileage;

    private String model;
    @ManyToOne
    private Station location;
}
