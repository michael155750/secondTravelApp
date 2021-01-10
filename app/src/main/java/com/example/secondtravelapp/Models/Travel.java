package com.example.secondtravelapp.Models;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

import java.util.LinkedList;


import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * @brief class represents travel which is an entity in our project.
 */
@Entity (tableName = "travels")
public class Travel {

    /*********** properties **************/

    @NonNull
    @PrimaryKey
    private String travelId = null;
    private String clientName = null;
    private String clientPhone = null;
    private String clientEmail = null;
    @TypeConverters(UserLocationConverter.class)
    private UserLocation pickupAddress = null;
    @TypeConverters(UserLocationConverter.class)
    private UserLocation destAddress;
    private Integer numOfPassengers = null;
    private Boolean isSafeGuarded = false;
    private Boolean isChildrenTransportation = false;
    @TypeConverters(RequestType.class)
    private RequestType status = RequestType.sent;
    @TypeConverters(CompanyConverter.class)
    private HashMap<String, Boolean> company;
    /*@TypeConverters(DateConverter.class)
    private Date travelDate;
    @TypeConverters(DateConverter.class)
    private Date arrivalDate ;*/

    private String travelDate;
    private String arrivalDate;

    /*********** Constructors ***************/

    /**
     * Construct a travel without parameters
     */

    public Travel(){

        this.company = new HashMap<String, Boolean>();


    }

    @NonNull
    public String getTravelId() {

        return travelId;
    }

    public void setTravelId(@NonNull String travelId) {
        if (travelId == null)
            this.travelId = travelId;

    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        try {
            String regex = "(0[0-9]*)";
            Pattern r = Pattern.compile(regex);

            Matcher m = r.matcher(clientPhone.toString());
            if (m.matches())
                this.clientPhone = clientPhone;
            else
                throw new IllegalArgumentException("illegal phone number format");
        }catch (Exception e){
            this.clientPhone = "";
        }
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        try {
            String regex = "(.*)@(.*)";
            Pattern r = Pattern.compile(regex);

            Matcher m = r.matcher(clientEmail);
            if (m.matches())
                this.clientEmail = clientEmail;
            else
                throw new IllegalArgumentException("illegal email format");
        }catch (Exception e){
            this.clientEmail = "";
        }
    }

    public UserLocation getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(UserLocation pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public UserLocation getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(UserLocation destAddress) {
        this.destAddress = destAddress;
    }

    public Integer getNumOfPassengers() {
        return numOfPassengers;
    }

    public void setNumOfPassengers(Integer numOfPassengers) {
        this.numOfPassengers = numOfPassengers;
    }

    public Boolean getSafeGuarded() {
        return isSafeGuarded;
    }

    public void setSafeGuarded(Boolean safeGuarded) {
        isSafeGuarded = safeGuarded;
    }

    public Boolean getChildrenTransportation() {
        return isChildrenTransportation;
    }

    public void setChildrenTransportation(Boolean childrenTransportation) {
        isChildrenTransportation = childrenTransportation;
    }

    public RequestType getStatus() {
        return status;
    }

    public void setStatus(RequestType status) {
        this.status = status;
    }

    public HashMap<String, Boolean> getCompany() {
        return company;
    }

    public void setCompany(HashMap<String, Boolean> company) {
        this.company = company;
    }

    public void setOneCompany(String company) {

        this.company.put(company, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void changeCompanyValue(String email){
        this.company.replace(email,true);
    }
/*
    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
*/


    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date travelDateTypeGetter() {
        DateConverter dateConverter = new DateConverter();
        return dateConverter.fromTimestamp(travelDate);
    }

    public void travelDateTypeSetter(Date travelDate) {
        DateConverter dateConverter = new DateConverter();
        this.travelDate = dateConverter.dateToTimestamp(travelDate);
    }

    public Date arrivalDateTypeGetter() {
        DateConverter dateConverter = new DateConverter();
        return dateConverter.fromTimestamp(arrivalDate);
    }

    public void arrivalDateTypeSetter(Date arrivalDate) {
        DateConverter dateConverter = new DateConverter();
        this.travelDate = dateConverter.dateToTimestamp(arrivalDate);
    }

    /*************** Converters **************/
    public static class DateConverter {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        @TypeConverter
        public Date fromTimestamp(String date)  {
            Date d = null;
            try {
                return (date == null ? null : format.parse(date));
            }catch (ParseException e){
                e.printStackTrace();
            }
            return d;
        }

        @TypeConverter
        public String dateToTimestamp(Date date) {
            return date == null ? null : format.format(date);
        }
    }

    public static class CompanyConverter {
        @TypeConverter
        public HashMap<String, Boolean> fromString(String value) {
            if (value == null || value.isEmpty())
                return null;
            String[] mapString = value.split(","); //split map into array of (string,boolean) strings
            HashMap<String, Boolean> hashMap = new HashMap<>();
            for (String s1 : mapString) //for all (string,boolean) in the map string
            {
                if (!s1.isEmpty()) {//is empty maybe will needed because the last char in the string is ","
                    String[] s2 = s1.split(":"); //split (string,boolean) to company string and boolean string.
                    Boolean aBoolean = Boolean.parseBoolean(s2[1]);
                    hashMap.put(/*company string:*/s2[0], aBoolean);
                }
            }
            return hashMap;
        }

        @TypeConverter
        public String asString(HashMap<String, Boolean> map) {
            if (map == null)
                return null;
            StringBuilder mapString = new StringBuilder();
            for (Map.Entry<String, Boolean> entry : map.entrySet())
                mapString.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
            return mapString.toString();
        }
    }

    public static class UserLocationConverter {
        @TypeConverter
        public UserLocation fromString(String value) {
            if (value == null || value.equals(""))
                return null;
            double lat = Double.parseDouble(value.split(" ")[0]);
            double lang = Double.parseDouble(value.split(" ")[1]);
            return new UserLocation(lat, lang);
        }

        @TypeConverter
        public String asString(UserLocation warehouseUserLocation) {
            return warehouseUserLocation == null ? "" : warehouseUserLocation.getLat() + " " + warehouseUserLocation.getLon();
        }
    }


    /*************** Enums *****************/



    public enum RequestType {
        sent(0), accepted(1), run(2), close(3), paid(4);
        private final Integer code;

        RequestType(Integer value) {
            this.code = value;
        }

        public Integer getCode() {
            return code;
        }

        @TypeConverter
        public static RequestType getType(Integer numeral) {
            for (RequestType ds : values())
                if (ds.code.equals(numeral))
                    return ds;
            return null;
        }
        @TypeConverter
        public static Integer getTypeInt(RequestType requestType) {
            if (requestType != null)
                return requestType.code;
            return null;
        }
    }
}
