package com.example.vehicles.dao.impl;

import com.example.vehicles.dao.VehicleDao;
import com.example.vehicles.entity.*;
import com.example.vehicles.utils.SearchCriteria;
import com.example.vehicles.utils.VehicleUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

@Repository
@Transactional(readOnly=true)
@Scope("prototype")
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

        vehicleEntity.getAirplaneEntities().clear();
        vehicleEntity.getAmphibianEntities().clear();
        vehicleEntity.getBoatEnties().clear();
        vehicleEntity.getCarEntities().clear();
        vehicleEntity.getDroneEntities().clear();
        vehicleEntity.getTruckEntities().clear();

        session.flush();

        session.delete((session.load(VehicleEntity.class,vehicleEntity.getId())));

        tx.commit();
    }

    @Override
    public List<VehicleEntity> findAll(List<SearchCriteria> params) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<VehicleEntity> query = builder.createQuery(VehicleEntity.class);

        Root r = query.from(VehicleEntity.class);

        Predicate predicate = builder.conjunction();

        predicate = VehicleUtils.getPredicate(params, builder, r, predicate);

        query.where(predicate);

        List<VehicleEntity> result = session.createQuery(query).getResultList();

        return result;
    }

    @Override
    public VehicleEntity findByAirplane(AirplaneEntity airplaneEntity) {
        Session session = sessionFactory.openSession();
        String hql = "select v FROM VehicleEntity v join fetch v.airplaneEntities a where a.id = :air_id";
        Query query = session.createQuery(hql);
        query.setParameter("air_id", airplaneEntity.getId());
        return (VehicleEntity) query.getSingleResult();
    }

    @Override
    public VehicleEntity findByAmphibian(AmphibianEntity amphibianEntity) {
        Session session = sessionFactory.openSession();
        String hql = "select v FROM VehicleEntity v join fetch v.amphibianEntities a where a.id = :amp_id";
        Query query = session.createQuery(hql);
        query.setParameter("amp_id", amphibianEntity.getId());
        return (VehicleEntity) query.getSingleResult();
    }

    @Override
    public VehicleEntity findByBoat(BoatEnty boatEnty) {
        Session session = sessionFactory.openSession();
        String hql = "select v FROM VehicleEntity v join fetch v.boatEnties b where b.id = :boat_id";
        Query query = session.createQuery(hql);
        query.setParameter("boat_id", boatEnty.getId());
        return (VehicleEntity) query.getSingleResult();
    }

    @Override
    public VehicleEntity findByCar(CarEntity carEntity) {
        Session session = sessionFactory.openSession();
        String hql = "select v FROM VehicleEntity v join fetch v.carEntities c where c.id = :car_id";
        Query query = session.createQuery(hql);
        query.setParameter("car_id", carEntity.getId());
        return (VehicleEntity) query.getSingleResult();

    }

    @Override
    public VehicleEntity findByDrone(DroneEntity droneEntity) {
        Session session = sessionFactory.openSession();
        String hql = "select v FROM VehicleEntity v join fetch v.droneEntities d where d.id = :drone_id";
        Query query = session.createQuery(hql);
        query.setParameter("drone_id", droneEntity.getId());
        return (VehicleEntity) query.getSingleResult();
    }

    @Override
    public VehicleEntity findByTruck(TruckEntity truckEntity) {
        Session session = sessionFactory.openSession();
        String hql = "select v FROM VehicleEntity v join fetch v.truckEntities t where t.id = :truck_id";
        Query query = session.createQuery(hql);
        query.setParameter("truck_id", truckEntity.getId());
        return (VehicleEntity) query.getSingleResult();
    }
}
