package service;

import domain.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDateTime;
import java.util.*;

public record JpaService(EntityManagerFactory entityManagerFactory) implements Service {

    @Override
    public Rental save(Rental rental) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(rental);
            manager.getTransaction().commit();
        }
        finally {
            manager.close();
        }
        return rental;
    }

    @Override
    public Station save(Station station) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            manager.getTransaction().begin();
            station = manager.merge(station);
            manager.getTransaction().commit();
        }
        finally {
            manager.close();
        }
        return station;
    }

    @Override
    public Car save(Car car) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(car);
            manager.getTransaction().commit();
        }
        finally {
            manager.close();
        }
        return car;
    }

    @Override
    public List<Station> findAllStations() {
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            return manager.createQuery("select station from Station station", Station.class).getResultList();
        }finally {
            manager.close();
        }
    }

    @Override
    public List<Car> findAllCars() {
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            return manager.createQuery("select car from Car car", Car.class).getResultList();
        }finally {
            manager.close();
        }
    }

    @Override
    public List<Rental> findAllRentals() {
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            return manager.createQuery("select rental from Rental rental", Rental.class).getResultList();
        }finally {
            manager.close();
        }
    }

    @Override
    public Optional<Rental> findRentalById(long id) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            return Optional.ofNullable(manager.find(Rental.class, id));

        }finally {
            manager.close();
        }
    }

    @Override
    public Set<Car> findCarsStationedAt(Station station) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            return new HashSet<Car>(manager.createQuery("""
                    select car
                    from  Car car
                    where car.location = :station
                    """, Car.class).setParameter("station", station).getResultList());
        }finally {
            manager.close();
        }
    }

    @Override
    public Rental finish(Rental rental, Station station, double drivenKm) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            rental.update(station, drivenKm, LocalDateTime.now());
            return rental;
        }finally {
            manager.close();
        }

    }
}