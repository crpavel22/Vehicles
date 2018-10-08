package com.example.vehicles.dao.impl;

import com.example.vehicles.dao.AirplaneDao;
import com.example.vehicles.entity.AirplaneEntity;
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
public class AirplaneDaoImpl implements AirplaneDao {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<AirplaneEntity> getAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(" FROM AirplaneEntity");
        List<AirplaneEntity> vehicles = query.list();
        return vehicles;
    }

    @Override
    public AirplaneEntity get(Integer id) {
        Session session = sessionFactory.openSession();

        return session.get(AirplaneEntity.class, id);
    }

    @Transactional(readOnly = false)
    @Override
    public void add(AirplaneEntity airplaneEntity) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        System.out.println(session.save(airplaneEntity));

        tx.commit();
    }

    @Transactional(readOnly = false)
    @Override
    public void remove(AirplaneEntity airplaneEntity) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        session.delete((session.load(AirplaneEntity.class, airplaneEntity.getId())));

        tx.commit();
    }

    @Transactional(readOnly = false)
    @Override
    public void alter(AirplaneEntity airplaneEntity) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        session.merge(airplaneEntity);

        tx.commit();
    }

    @Override
    public List<AirplaneEntity> findAll(List<SearchCriteria> params) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<AirplaneEntity> query = builder.createQuery(AirplaneEntity.class);

        Root r = query.from(AirplaneEntity.class);

        Predicate predicate = builder.conjunction();

        predicate = VehicleUtils.getPredicate(params, builder, r, predicate);

        query.where(predicate);

        List<AirplaneEntity> result = session.createQuery(query).getResultList();

        return result;
    }
}