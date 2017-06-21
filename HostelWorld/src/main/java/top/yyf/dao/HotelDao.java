package top.yyf.dao;

import org.springframework.stereotype.Repository;
import top.yyf.dao.base.BaseDao;
import top.yyf.entity.HotelEntity;
import top.yyf.mess.retmess.AgeNumber;
import top.yyf.mess.retmess.PlaceNumber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2017/2/27.
 */
@Repository
public class HotelDao extends BaseDao<HotelEntity, String> {
    public String getMaxId() {
        List li = getSession().createQuery("from HotelEntity order by id " +
                "desc")
                .setMaxResults
                        (1).list();
        if (li == null || li.size() == 0)
            return null;
        return ((HotelEntity) li.get(0)).getHotelId();
    }

    List<HotelEntity> getHotelsByName(String hotelName) {
        return getListByHQL("from HotelEntity where name like ? AND is_passed=1", hotelName);
    }

    public HotelEntity getHotelById(String hotel){
        return getByHQL("from HotelEntity where hotelId =? AND is_passed = 1", hotel);
    }

    public List<HotelEntity> getAllHotels() {
        return getListByHQL("from HotelEntity where is_passed = 1");
    }

    public List<HotelEntity> getUnpassedHotels() {
        return getListByHQL("from HotelEntity where is_passed=0");
    }

    /**
     * 获得地域分布
     * @param hotel
     * @return
     */
    public List<PlaceNumber> getPlaceNumberByHotelId(String hotel){

        List<PlaceNumber> placeNumbers = new ArrayList<>();

        String sql1 ="SELECT DISTINCT membership_id FROM check_in WHERE membership_id IS NOT NULL AND room_id IN " +
                "      (SELECT room_id FROM actual_room WHERE hotel_id =? )";
        List<String> memberList = getSession().createSQLQuery(sql1).setParameter(0, hotel).list();

        for(String member:memberList){
            String sql2 ="SELECT count(*) FROM check_in WHERE check_in.membership_id =? and room_id IN " +
                    "       (SELECT room_id FROM actual_room WHERE hotel_id =? )";
            int number = Integer.valueOf(getSession().createSQLQuery(sql2).setParameter(0, member).setParameter(1, hotel).list().get(0).toString());

            String sql3 = "SELECT city from user WHERE merbership_id =? ";
            String place = (String) getSession().createSQLQuery(sql3).setParameter(0, member).uniqueResult();

            String tempPlace = "";
            int tempNum = 0;
            PlaceNumber placeNumber = new PlaceNumber();
            placeNumber.place = place;
            if(tempPlace.equals(place)){
                number = number + tempNum;
            }
            placeNumber.number = number;

            tempPlace = place;
            tempNum = number;

            placeNumbers.add(placeNumber);
        }

        return placeNumbers;
    }

    public List<AgeNumber> getAgeNumberByHotelId(String hotel){
        List<AgeNumber> ageNumbers = new ArrayList<>();

        String sql1 ="SELECT DISTINCT membership_id FROM check_in WHERE membership_id IS NOT NULL AND room_id IN " +
                "      (SELECT room_id FROM actual_room WHERE hotel_id =? )";
        List<String> memberList = getSession().createSQLQuery(sql1).setParameter(0, hotel).list();

        int[]  level = new int[5];


        for(String member:memberList){
            String sql2 ="SELECT count(*) FROM check_in WHERE check_in.membership_id =? and room_id IN " +
                    "       (SELECT room_id FROM actual_room WHERE hotel_id =? )";
            int number = Integer.valueOf(getSession().createSQLQuery(sql2).setParameter(0, member).setParameter(1, hotel).list().get(0).toString());

            String sql3 = "SELECT age from user WHERE merbership_id =? ";
            String age = (String) getSession().createSQLQuery(sql3).setParameter(0, member).uniqueResult();

            int tempAge = Integer.valueOf(age);

            if((tempAge>=20)&&(tempAge<30)){
                level[1] += number;
//                ageNumber.age = "20~30";
            }else if((tempAge>=30)&&(tempAge<40)){
                level[2] += number;
//                ageNumber.age = "30~40";
            }else if((tempAge>=40)&&(tempAge<50)){
                level[3] += number;
//                ageNumber.age = "40~50";
            }else if(tempAge>=50){
                level[4] += number;
//                ageNumber.age = ">=50";
            }else{
                level[0] += number;
//                ageNumber.age = "未成年";
            }
//            ageNumbers.add(ageNumber);
        }

        for(int i = 0;i<5;i++){
            System.out.println(level[i]);
        }
        for(int i = 0;i<5;i++){
            if(level[i] != 0){
                AgeNumber ageNumber = new AgeNumber();
                ageNumber.number = level[i];
                switch(i){
                    case 0:
                        ageNumber.age = "未成年";
                        break;
                    case 1:
                        ageNumber.age = "20~30";
                        break;
                    case 2:
                        ageNumber.age = "30~40";
                        break;
                    case 3:
                        ageNumber.age = "40~50";
                        break;
                    case 4:
                        ageNumber.age = ">=50";
                        break;

                }
                ageNumbers.add(ageNumber);
            }
        }

        return ageNumbers;
    }


}
