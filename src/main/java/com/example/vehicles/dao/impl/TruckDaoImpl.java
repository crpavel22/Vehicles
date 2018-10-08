package com.example.vehicles.dao.impl;

import com.example.vehicles.dao.TruckDao;
import com.example.vehicles.entity.TruckEntity;
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
public class TruckDaoImpl implements TruckDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<TruckEntity> getAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(" FROM TruckEntity");
        List<TruckEntity> vehicles = query.list();
        return vehicles;
    }

    @Override
    public TruckEntity get(Integer id) {
        Session session = sessionFactory.openSession();

        return session.get(TruckEntity.class, id);
    }

    @Transactional(readOnly = false)
    @Override
    public void add(TruckEntity truckEntity) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        System.out.println(session.save(truckEntity));

        tx.commit();
    }

    @Transactional(readOnly = false)
    @Override
    public void remove(TruckEntity truckEntity) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        session.delete((session.load(TruckEntity.class, truckEntity.getId())));

        tx.commit();
    }

    @Transactional(readOnly = false)
    @Override
    public void alter(TruckEntity truckEntity) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        session.merge(truckEntity);

        tx.commit();
    }

    @Override
    public List<TruckEntity> findAll(List<SearchCriteria> params) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TruckEntity> query = builder.createQuery(TruckEntity.class);

        Root r = query.from(TruckEntity.class);

        Predicate predicate = builder.conjunction();

        predicate = VehicleUtils.getPredicate(params, builder, r, predicate);

        query.where(predicate);

        List<TruckEntity> result = session.createQuery(query).getResultList();

        return result;
    }
}
