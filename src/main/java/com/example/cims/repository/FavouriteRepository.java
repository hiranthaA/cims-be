package com.example.cims.repository;

import com.example.cims.Entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite,Integer> {

    @Transactional
    @Modifying
    @Query("delete from Favourite f where f.user_id=:userid and f.inv_id=:invid")
    int deleteFavourite(@Param("userid") int userid, @Param("invid") int invid);

    @Query("select i.inv_id as inv_id, i.added_on as added_on, i.exp_on as exp_on, i.item_type as item_type, i.stock as stock, c.car_id as car_id, c.plate_no as plate_no, c.brand as brand, c.model as model, c.prod_yr as prod_yr, c.color as color, c.mileage as mileage, c.photo as photo, c.description as description, c.price as price, c.down_payment as down_payment from Inventory i, Favourite f, Car c where f.inv_id=i.inv_id and i.item_type=:filter and i.inv_id=c.inv_id and f.user_id=:userid")
    List<Object[]>getUserFavouriteCars(@Param("userid") int userid, @Param("filter") String filter);

    @Query("select i.inv_id, i.added_on, i.exp_on, i.item_type, i.stock, p.part_id, p.part_name, p.brand, p.photo, p.description, p.price from Inventory i, Favourite f, Part p where f.inv_id=i.inv_id and i.item_type=:filter and i.inv_id=p.inv_id  and f.user_id=:userid")
    List<Object[]>getUserFavouriteParts(@Param("userid") int userid, @Param("filter") String filter);

//    List<FavCarFilter>fetchAllFavouritesOfUser(@Param("userid") int userid, @Param("filter") String filter);

//    @Query(value = "select i.added_on, i.exp_on, i.item_type, i.stock, c.car_id, c.plate_no, c.brand, c.model, c.prod_yr, c.color, c.mileage, c.photo, c.description, c.price, c.down_payment from inventory i, favourite f, car c where f.inv_id=i.inv_id and i.item_type=?1 and i.inv_id=c.inv_id and f.user_id=?2",nativeQuery = true)
//    List<FavCarFilter>getUserFavourites(int userid, String filter);

}
