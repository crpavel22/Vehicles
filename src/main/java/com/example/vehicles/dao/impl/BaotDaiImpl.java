package com.example.vehicles.dao.impl;

import com.example.vehicles.dao.BoatDao;
import com.example.vehicles.entity.BoatEnty;
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
public class BaotDaiImpl implements BoatDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<BoatEnty> getAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(" FROM BoatEnty");
        List<BoatEnty> vehicles = query.list();
        return vehicles;
    }

    @Override
    public BoatEnty get(Integer id) {
        Session session = sessionFactory.openSession();

        return session.get(BoatEnty.class, id);
    }

    @Transactional(readOnly = false)
    @Override
    public void add(BoatEnty boatEnty) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        System.out.println(session.save(boatEnty));

        tx.commit();
    }

    @Transactional(readOnly = false)
    @Override
    public void remove(BoatEnty boatEnty) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        session.delete((session.load(BoatEnty.class, boatEnty.getId())));

        tx.commit();
    }

    @Transactional(readOnly = false)
    @Override
    public void alter(BoatEnty boatEnty) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        session.merge(boatEnty);

        tx.commit();
    }

    @Override
    public List<BoatEnty> findAll(List<SearchCriteria> params) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<BoatEnty> query = builder.createQuery(BoatEnty.class);

        Root r = query.from(BoatEnty.class);

        Predicate predicate = builder.conjunction();

        predicate = VehicleUtils.getPredicate(params, builder, r, predicate);

        query.where(predicate);

        List<BoatEnty> result = session.createQuery(query).getResultList();

        return result;
    }
}
