package com.example.cims.repository;

import com.example.cims.Entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer> {

    @Query("select i.invid, i.addedon, i.expon, i.itemtype, i.stock, i.state, c.carid, c.plateno, c.brand, c.model, c.prodyr, c.color, c.mileage, c.photo, c.description, c.price, c.downpayment from Inventory i, Car c where i.invid=c.invid and i.itemtype=:filter and i.state not in ('deleted')")
    List<Object[]> getAllInventoryCars(@Param("filter") String filter);

    @Query("select i.invid, i.addedon, i.expon, i.itemtype, i.stock, i.state, p.partid, p.partname, p.brand, p.photo, p.description, p.price from Inventory i, Part p where i.invid=p.invid and i.itemtype=:filter and i.state not in ('deleted')")
    List<Object[]> getAllInventoryParts(@Param("filter") String filter);

    @Query("select i.invid, i.addedon, i.expon, i.itemtype, i.stock, i.state, c.carid, c.plateno, c.brand, c.model, c.prodyr, c.color, c.mileage, c.photo, c.description, c.price, c.downpayment from Inventory i, Car c where i.invid=c.invid and i.itemtype=:filter and i.invid=:id and i.state not in ('deleted')")
    List<Object[]> getInventoryCar(@Param("filter") String filter, @Param("id") int id);

    @Query("select i.invid, i.addedon, i.expon, i.itemtype, i.stock, i.state, p.partid, p.partname, p.brand, p.photo, p.description, p.price from Inventory i, Part p where i.invid=p.invid and i.itemtype=:filter and i.invid=:id and i.state not in ('deleted')")
    List<Object[]> getInventoryPart(@Param("filter") String filter, @Param("id") int id);

    Inventory findByInvid(int invid);

    @Transactional
    @Modifying
    @Query("update Inventory set stock=:stock,state=:state where invid=:invid")
    int updateStock(@Param("invid") int invid, @Param("stock") int stock, @Param("state") String state);

    @Transactional
    @Modifying
    @Query("update Inventory set state=:state where invid=:invid")
    int updateState(@Param("invid") int invid, @Param("state") String state);

}
