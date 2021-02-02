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
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @brief class represents travel which is an entity in our project.
 */
@Entity(tableName = "travels")
public class Travel {

    public static Travel.CompanyConverter CompanyConverter;
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

    public Travel() {

        this.company = new HashMap<String, Boolean>();


    }

    /**
     * getter of firebase id
     *
     * @return firebase id.
     */
    @NonNull
    public String getTravelId() {

        return travelId;
    }

    /**
     * setter of firebase travel id
     *
     * @param travelId - an id string
     */
    public void setTravelId(@NonNull String travelId) {
        if (this.travelId == null)
            this.travelId = travelId;

    }

    /**
     * getter of name of client
     *
     * @return a string of the name of client
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * setter of the name of the client
     *
     * @param clientName - a string which is the name of client.
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * getter of client phone number
     *
     * @return a string of client's phone number.
     */
    public String getClientPhone() {
        return clientPhone;
    }

    /**
     * setter of client's phone number,
     * set only if it has phone number format.
     *
     * @param clientPhone - a client phone number.
     */
    public void setClientPhone(String clientPhone) {
        try {
            String regex = "(0[0-9]*)";
            Pattern r = Pattern.compile(regex);

            Matcher m = r.matcher(clientPhone.toString());
            if (m.matches())
                this.clientPhone = clientPhone;
            else
                throw new IllegalArgumentException("illegal phone number format");
        } catch (Exception e) {
            this.clientPhone = "";
        }
    }

    /**
     * getter of client email
     *
     * @return a string of client email
     */
    public String getClientEmail() {
        return clientEmail;
    }

    /**
     * setter of the email of the client,
     * set only if it has an email format.
     *
     * @param clientEmail - a string of client email
     */
    public void setClientEmail(String clientEmail) {
        try {
            String regex = "(.*)@(.*)";
            Pattern r = Pattern.compile(regex);

            Matcher m = r.matcher(clientEmail);
            if (m.matches())
                this.clientEmail = clientEmail;
            else
                throw new IllegalArgumentException("illegal email format");
        } catch (Exception e) {
            this.clientEmail = "";
        }
    }

    /**
     * getter of pick up addresses
     *
     * @return a userlocation of the pickup address
     */
    public UserLocation getPickupAddress() {
        return pickupAddress;
    }

    /**
     * setter of pickup address
     *
     * @param pickupAddress - a string of pickup address
     */
    public void setPickupAddress(UserLocation pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    /**
     * getter of destination address.
     *
     * @return an userlocation of destination address
     */
    public UserLocation getDestAddress() {
        return destAddress;
    }

    /**
     * setter of destination address
     *
     * @param destAddress - an userlocation of destination address
     */
    public void setDestAddress(UserLocation destAddress) {
        this.destAddress = destAddress;
    }

    /**
     * getter of number of passengers.
     *
     * @return the number of passengers.
     */
    public Integer getNumOfPassengers() {
        return numOfPassengers;
    }

    /**
     * setter of number of passengers
     *
     * @param numOfPassengers - an integer of the number of passengers.
     */
    public void setNumOfPassengers(Integer numOfPassengers) {
        this.numOfPassengers = numOfPassengers;
    }

    /**
     * getter of is safe guarded variable
     *
     * @return a boolean variable which true if the bus is unbreakable by stones and bullets
     */
    public Boolean getSafeGuarded() {
        return isSafeGuarded;
    }

    /**
     * setter of is safe guarded variable
     *
     * @param safeGuarded - a boolean variable give true if the bus is unbreakable by stones and bullets
     */
    public void setSafeGuarded(Boolean safeGuarded) {
        isSafeGuarded = safeGuarded;
    }

    /**
     * getter of is childrenTransportation
     *
     * @return a boolean value
     */
    public Boolean getChildrenTransportation() {
        return isChildrenTransportation;
    }

    /**
     * setter of children transportation.
     *
     * @param childrenTransportation - a boolean value
     */
    public void setChildrenTransportation(Boolean childrenTransportation) {
        isChildrenTransportation = childrenTransportation;
    }

    /**
     * getter of status of travel,
     *
     * @return a request type
     */
    public RequestType getStatus() {
        return status;
    }

    /**
     * setter of the status of the travel,
     *
     * @param status - a request type
     */
    public void setStatus(RequestType status) {
        this.status = status;
    }

    /**
     * getter of company hashmap of a single travel
     *
     * @return an hash map of companies
     */
    public HashMap<String, Boolean> getCompany() {
        return company;
    }

    /**
     * getter of company hash map,
     * in the firebase we cannot store '.' in the email of the company,
     * therefore, we decided to replace '.' by '*' and store it that
     * way in firebase and then when we read from firebase to replace it back to '.'
     *
     * @return an hash map of companies
     */
    public HashMap<String, Boolean> companyGetter() {
        LinkedList<String> companySet = new LinkedList<String>();
        companySet.addAll(company.keySet());
        for (String c : companySet) {
            String newc = c.replace("*", ".");
            boolean cValue = company.get(c);
            company.remove(c);
            company.put(newc, cValue);
        }
        return company;
    }

    /**
     * Firebase cannot store a '.' in the hash map, thus we have replaced all the '.' by '*'
     *
     * @param company - an hash map of companies
     */
    public void setCompany(HashMap<String, Boolean> company) {

        LinkedList<String> companySet = new LinkedList<String>();
        if (company !=null) {
            companySet.addAll(company.keySet());
            for (String c : companySet) {
                String newc = c.replace(".", "*");
                boolean cValue = company.get(c);
                company.remove(c);
                company.put(newc, cValue);
            }
        }
        this.company = company;
    }

    /**
     * setter of company email's value,
     * set the value of the key by false
     * in the firebase we cannot store '.' in the email of the company,
     * therefore, we decided to replace '.' by '*' and store it that
     * way in firebase and then when we read from firebase to replace it back to '.'
     *
     * @param company - an string of email of company.
     */
    public void setOneCompany(String company) {
        String newc = company.replace(".", "*");


        this.company.put(newc, false);
    }

    /**
     * setter of company email's value,
     * set the value of the key by false
     *
     * @param company - an string of company email.
     */
    public void setOneCompanyTrue(String company) {
        String newc = company.replace(".", "*");
        this.company.put(newc, true);
    }

    /**
     * replace a value of hey of company email to true.
     *
     * @param email - a string of company email
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void changeCompanyValue(String email) {
        this.company.replace(email, true);
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


    /**
     * getter of travel date
     *
     * @return the date of the travel.
     */
    public String getTravelDate() {
        return travelDate;
    }

    /**
     * setter of travel date
     *
     * @param travelDate - a string of date
     */
    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    /**
     * getter of arrival date
     *
     * @return an arrival date.
     */
    public String getArrivalDate() {
        return arrivalDate;
    }

    /**
     * setter of arrival date
     *
     * @param arrivalDate - an string of arrival date.
     */
    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * getter of date
     *
     * @return a date class
     */
    public Date travelDateTypeGetter() {
        DateConverter dateConverter = new DateConverter();
        return dateConverter.fromTimestamp(travelDate);
    }

    /**
     * setter of travel date,
     * decreases the number of years by 1900 and the months by one
     *
     * @param travelDate - a travel date
     */
    public void travelDateTypeSetter(Date travelDate) {
        DateConverter dateConverter = new DateConverter();
        travelDate.setYear(travelDate.getYear() - 1900);
        travelDate.setMonth(travelDate.getMonth() - 1);
        this.travelDate = dateConverter.dateToTimestamp(travelDate);
    }

    /**
     * getter of arrival date
     *
     * @return an arrival date.
     */
    public Date arrivalDateTypeGetter() {
        DateConverter dateConverter = new DateConverter();
        return dateConverter.fromTimestamp(arrivalDate);
    }

    /**
     * setter of arrival date,
     * decreases the number of years by 1900 and the months by one
     *
     * @param arrivalDate - an arrival date
     */
    public void arrivalDateTypeSetter(Date arrivalDate) {
        DateConverter dateConverter = new DateConverter();
        arrivalDate.setYear(arrivalDate.getYear() - 1900);
        arrivalDate.setMonth(arrivalDate.getMonth() - 1);
        this.arrivalDate = dateConverter.dateToTimestamp(arrivalDate);
    }

    /*************** Converters **************/

    /**
     * class represents data converter of a date.
     */
    public static class DateConverter {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        @TypeConverter
        public Date fromTimestamp(String date) {
            Date d = null;
            try {
                return (date == null ? null : format.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return d;
        }

        @TypeConverter
        public String dateToTimestamp(Date date) {
            return date == null ? null : format.format(date);
        }
    }

    /**
     * class represents company data converter
     */
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

    /**
     * class represents user location converter
     */
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


    /**
     * enum represents types of travel statuses.
     */
    public enum RequestType {
        sent(0), accepted(1), run(2), close(3), paid(4);
        private final Integer code;

        /**
         * constructs a request type by a value from the above.
         * @param value
         */
        RequestType(Integer value) {
            this.code = value;
        }

        /**
         * getter of the enum value code
         * @return an integer of code of status.
         */
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
