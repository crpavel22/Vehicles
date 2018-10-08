package com.example.vehicles.dao.impl;

import com.example.vehicles.dao.AmphibianDao;
import com.example.vehicles.entity.AmphibianEntity;
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
public class ApmphibianDaoImpl implements AmphibianDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<AmphibianEntity> getAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(" FROM AmphibianEntity");
        List<AmphibianEntity> vehicles = query.list();
        return vehicles;
    }

    @Override
    public AmphibianEntity get(Integer id) {
        Session session = sessionFactory.openSession();

        return session.get(AmphibianEntity.class, id);
    }

    @Transactional(readOnly = false)
    @Override
    public void add(AmphibianEntity amphibianEntity) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        System.out.println(session.save(amphibianEntity));

        tx.commit();
    }

    @Transactional(readOnly = false)
    @Override
    public void remove(AmphibianEntity amphibianEntity) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        session.delete((session.load(AmphibianEntity.class, amphibianEntity.getId())));

        tx.commit();
    }

    @Transactional(readOnly = false)
    @Override
    public void alter(AmphibianEntity amphibianEntity) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        session.merge(amphibianEntity);

        tx.commit();
    }

    @Override
    public List<AmphibianEntity> findAll(List<SearchCriteria> params) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<AmphibianEntity> query = builder.createQuery(AmphibianEntity.class);

        Root r = query.from(AmphibianEntity.class);

        Predicate predicate = builder.conjunction();

        predicate = VehicleUtils.getPredicate(params, builder, r, predicate);

        query.where(predicate);

        List<AmphibianEntity> result = session.createQuery(query).getResultList();

        return result;
    }
}
