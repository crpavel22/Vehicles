package com.example.vehicles.dao.impl;

import com.example.vehicles.dao.CarDao;
import com.example.vehicles.entity.CarEntity;
import com.example.vehicles.utils.SearchCriteria;
import com.example.vehicles.utils.VehicleUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional(readOnly = true)
@Scope("prototype")
public class CarDaoImpl implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<CarEntity> getAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(" FROM CarEntity");
        List<CarEntity> vehicles = query.list();
        return vehicles;
    }

    @Override
    public CarEntity get(Integer id) {
        Session session = sessionFactory.openSession();

        return session.get(CarEntity.class, id);
    }

    @Transactional(readOnly = false)
    @Override
    public void add(CarEntity carEntity) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        System.out.println(session.save(carEntity));

        tx.commit();
    }

    @Transactional(readOnly = false)
    @Override
    public void remove(CarEntity carEntity) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        session.delete((session.load(CarEntity.class, carEntity.getId())));

        tx.commit();
    }

    @Transactional(readOnly = false)
    @Override
    public void alter(CarEntity carEntity) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        session.merge(carEntity);

        tx.commit();
    }

    @Override
    public List<CarEntity> findAll(List<SearchCriteria> params) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CarEntity> query = builder.createQuery(CarEntity.class);

        Root r = query.from(CarEntity.class);

        Predicate predicate = builder.conjunction();

        predicate = VehicleUtils.getPredicate(params, builder, r, predicate);

        query.where(predicate);

        List<CarEntity> result = session.createQuery(query).getResultList();

        return result;
    }
}
