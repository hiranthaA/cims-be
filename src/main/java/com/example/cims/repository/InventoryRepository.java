package com.example.cims.repository;

import com.example.cims.Entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer> {

    @Query("select i.inv_id as inv_id, i.added_on as added_on, i.exp_on as exp_on, i.item_type as item_type, i.stock as stock, c.car_id as car_id, c.plate_no as plate_no, c.brand as brand, c.model as model, c.prod_yr as prod_yr, c.color as color, c.mileage as mileage, c.photo as photo, c.description as description, c.price as price, c.down_payment as down_payment from Inventory i, Car c where i.inv_id=c.inv_id and i.item_type=:filter")
    List<Object[]> getAllInventoryCars(@Param("filter") String filter);

    @Query("select i.inv_id, i.added_on, i.exp_on, i.item_type, i.stock, p.part_id, p.part_name, p.brand, p.photo, p.description, p.price from Inventory i, Part p where i.inv_id=p.inv_id and i.item_type=:filter")
    List<Object[]> getAllInventoryParts(@Param("filter") String filter);

    @Query("select i.inv_id as inv_id, i.added_on as added_on, i.exp_on as exp_on, i.item_type as item_type, i.stock as stock, c.car_id as car_id, c.plate_no as plate_no, c.brand as brand, c.model as model, c.prod_yr as prod_yr, c.color as color, c.mileage as mileage, c.photo as photo, c.description as description, c.price as price, c.down_payment as down_payment from Inventory i, Car c where i.inv_id=c.inv_id and i.item_type=:filter and i.inv_id=:id")
    List<Object> getInventoryCar(@Param("filter") String filter, @Param("id") int id);

    @Query("select i.inv_id, i.added_on, i.exp_on, i.item_type, i.stock, p.part_id, p.part_name, p.brand, p.photo, p.description, p.price from Inventory i, Part p where i.inv_id=p.inv_id and i.item_type=:filter and i.inv_id=:id")
    List<Object> getInventoryPart(@Param("filter") String filter, @Param("id") int id);

//    Inventory findByInv_id(int inv_id);

}
