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
    @Query("delete from Favourite f where f.userid=:userid and f.invid=:invid")
    int deleteFavourite(@Param("userid") int userid, @Param("invid") int invid);

    @Query("select i.invid, i.addedon, i.expon, i.itemtype, i.stock, i.state, c.carid, c.plateno, c.brand, c.model, c.prodyr, c.color, c.mileage, c.photo, c.description, c.price, c.downpayment from Inventory i, Favourite f, Car c where f.invid=i.invid and i.itemtype=:filter and i.invid=c.invid and f.userid=:userid and i.state not in ('deleted')")
    List<Object[]>getUserFavouriteCars(@Param("userid") int userid, @Param("filter") String filter);

    @Query("select i.invid, i.addedon, i.expon, i.itemtype, i.stock, i.state, p.partid, p.partname, p.brand, p.photo, p.description, p.price from Inventory i, Favourite f, Part p where f.invid=i.invid and i.itemtype=:filter and i.invid=p.invid  and f.userid=:userid and i.state not in ('deleted')")
    List<Object[]>getUserFavouriteParts(@Param("userid") int userid, @Param("filter") String filter);

    Favourite findByUseridAndInvid(int userid, int invid);

//    List<FavCarFilter>fetchAllFavouritesOfUser(@Param("userid") int userid, @Param("filter") String filter);

//    @Query(value = "select i.added_on, i.exp_on, i.item_type, i.stock, c.car_id, c.plate_no, c.brand, c.model, c.prod_yr, c.color, c.mileage, c.photo, c.description, c.price, c.down_payment from inventory i, favourite f, car c where f.inv_id=i.inv_id and i.item_type=?1 and i.inv_id=c.inv_id and f.user_id=?2",nativeQuery = true)
//    List<FavCarFilter>getUserFavourites(int userid, String filter);

}
