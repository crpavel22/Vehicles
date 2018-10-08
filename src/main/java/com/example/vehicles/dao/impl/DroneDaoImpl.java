package com.example.vehicles.dao.impl;

import com.example.vehicles.dao.DroneDao;
import com.example.vehicles.entity.DroneEntity;
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
public class DroneDaoImpl implements DroneDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<DroneEntity> getAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(" FROM DroneEntity");
        List<DroneEntity> vehicles = query.list();
        return vehicles;
    }

    @Override
    public DroneEntity get(Integer id) {
        Session session = sessionFactory.openSession();

        return session.get(DroneEntity.class, id);
    }

    @Transactional(readOnly = false)
    @Override
    public void add(DroneEntity droneEntity) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        System.out.println(session.save(droneEntity));

        tx.commit();
    }

    @Transactional(readOnly = false)
    @Override
    public void remove(DroneEntity droneEntity) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        session.delete((session.load(DroneEntity.class, droneEntity.getId())));

        tx.commit();
    }

    @Transactional(readOnly = false)
    @Override
    public void alter(DroneEntity droneEntity) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        session.merge(droneEntity);

        tx.commit();
    }

    @Override
    public List<DroneEntity> findAll(List<SearchCriteria> params) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DroneEntity> query = builder.createQuery(DroneEntity.class);

        Root r = query.from(DroneEntity.class);

        Predicate predicate = builder.conjunction();

        predicate = VehicleUtils.getPredicate(params, builder, r, predicate);

        query.where(predicate);

        List<DroneEntity> result = session.createQuery(query).getResultList();

        return result;
    }
}
