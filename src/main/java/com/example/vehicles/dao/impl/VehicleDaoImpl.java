package com.example.vehicles.dao.impl;

import com.example.vehicles.dao.VehicleDao;
import com.example.vehicles.entity.VehicleEntity;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly=true)
public class VehicleDaoImpl implements VehicleDao {



    @Autowired
    private SessionFactory sessionFactory;



    @Override
    public List<VehicleEntity> getAll() {

        Session session = sessionFactory.openSession();
        String hql = "FROM VehicleEntity";
        Query query = session.createQuery(hql);
        List<VehicleEntity> vehicles = query.list();
        return vehicles;
    }

    @Override
    public List<VehicleEntity> getByType(String type) {
        Session session = sessionFactory.openSession();
        String hql = "FROM VehicleEntity";
        Query query = session.createQuery(hql);
        List<VehicleEntity> vehicles = query.list();
        return vehicles;
    }

    @Override
    public VehicleEntity selectLast() {
        Session session = sessionFactory.openSession();
        String hql = "select max (id) FROM VehicleEntity";
        Query query = session.createQuery(hql);
        int id = (Integer) query.getSingleResult();
        return session.get(VehicleEntity.class,id);
    }

    @Transactional(readOnly = false)
    @Override
    public void add(VehicleEntity vehicleEntity) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        System.out.println(session.save(vehicleEntity)); //session.save(vehicleEntity);

        tx.commit();
    }

    @Transactional(readOnly = false)
    @Override
    public void remove(VehicleEntity vehicleEntity) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        session.delete(vehicleEntity);

        tx.commit();
    }
}
