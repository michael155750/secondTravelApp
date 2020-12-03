package com.example.secondtravelapp.Models;

import android.os.Build;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

import java.util.LinkedList;


import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * @brief class represents travel which is an entity in our project.
 */

public class Travel {

    /*********** properties **************/


    private String travelId = null;
    private String clientName = null;
    private String clientPhone = null;
    private String clientEmail = null;
    private UserLocation pickupAddress = null;
    private LinkedList<UserLocation> destAddresses;
    private Integer numOfPassengers = null;
    private Boolean isSafeGuarded = false;
    private Boolean isChildrenTransportation = false;
    private RequestType status = RequestType.sent;
    private HashMap<String, Boolean> company;
    private Date travelDate = null;
    private Date arrivalDate = null;
    DateTimeFormatter formatter;


    /*********** Constructors ***************/

    /**
     * Construct a travel without parameters
     */

    public Travel(){
        this.destAddresses = new LinkedList<UserLocation>();
        this.company = new HashMap<String, Boolean>();


    }

    /**
     * Construct a travel with the parameters that required by basic document
     * @param clientName
     * @param clientPhone
     * @param clientEmail
     * @param pickupAddress
     * @param numOfPassengers
     * @param travelDate
     * @param arrivalDate
     * @param request
     * @param destAddresses
     * @throws Exception
     */

    public Travel(String clientName, String clientPhone, String clientEmail,
                  UserLocation pickupAddress,


                  Integer numOfPassengers, Date travelDate,
                  Date arrivalDate, RequestType request, UserLocation... destAddresses) throws Exception {
        this();
        setClientName(clientName);
        setClientPhone(clientPhone);
        setClientEmail(clientEmail);
        setPickupAddress(pickupAddress);
        setNumOfPassengers(numOfPassengers);
        setTravelDate(travelDate);
        setArrivalDate(arrivalDate);
        setStatus(request);
        for (UserLocation address:destAddresses){
            setDestAddresses(address);
        }
    }

    /**
     * Construct a travel via document required and our own fields.
     * @param clientName - client name
     * @param clientPhone
     * @param clientEmail
     * @param pickupAddress
     * @param numOfPassengers
     * @param travelDate
     * @param isSafeGuarded
     * @param isChildrenTransportation
     * @param destAddresses
     * @throws Exception
     */

    public Travel(String clientName, String clientPhone, String clientEmail,
                  UserLocation pickupAddress,
                  Integer numOfPassengers, Date travelDate,
                  Boolean isSafeGuarded, Boolean isChildrenTransportation,
                  UserLocation... destAddresses) throws Exception {

        this(clientName,clientPhone,clientEmail,pickupAddress,
                numOfPassengers,travelDate, null,RequestType.sent,destAddresses);

        setSafeGuarded(isSafeGuarded);
        setChildrenTransportation(isChildrenTransportation);
    }



    /*********** getters & setters *****************/

    /**
     * setter of id
     * @param id - inner parameter of the program
     */
    public void setTravelId(String id) {

        if (travelId == null)
            travelId = id;
    }

    /**
     * Getter of id of travel
     * @return travel's id
     */
    public String getTravelId() {
        return travelId;
    }

    /**
     * Getter of the client's name
     * @return client name
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Setter of client name
     * @param clientName
     */
    public void setClientName(String clientName) {

        this.clientName = clientName;
    }

    /**
     * getter of client name
     * @return client name
     */
    public String getClientPhone() {
        return clientPhone;
    }

    /**
     * setter of cleint name
     * @param clientPhone
     * @throws Exception IllegalArgumentException("illegal phone number format") where email is not as format
     */
    public void setClientPhone(String clientPhone) throws Exception {

        String regex = "(0[0-9]*)";
        Pattern r = Pattern.compile(regex);

        Matcher m = r.matcher(clientPhone.toString());
        if (m.matches())
            this.clientPhone = clientPhone;
        else
            throw new IllegalArgumentException("illegal phone number format");

    }

    /**
     *
     * @return client email
     */
    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) throws Exception{
        String regex = "(.*)@(.*)";
        Pattern r = Pattern.compile(regex);

        Matcher m = r.matcher(clientEmail);
        if (m.matches())
            this.clientEmail = clientEmail;
        else
            throw new IllegalArgumentException("illegal email format");
    }

    public UserLocation getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(UserLocation pickupAddress) {


        this.pickupAddress = pickupAddress;
    }


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

    public LinkedList<UserLocation> getDestAddresses() {
        return destAddresses;
    }

    public void setDestAddresses(UserLocation destAddresses) {

        this.destAddresses.add(destAddresses);
    }

    public Integer getNumOfPassengers() {
        return numOfPassengers;
    }

    public void setNumOfPassengers(Integer numOfPassengers) {

        this.numOfPassengers = numOfPassengers;
    }



    public HashMap<String, Boolean> getCompany() {
        return company;
    }

    public void setCompany(String company) {

        this.company.put(company, false);
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


    /*************** Enums *****************/



    public enum RequestType {
        sent(0), accepted(1), run(2), close(3);
        private final Integer code;

        RequestType(Integer value) {
            this.code = value;
        }

        public Integer getCode() {
            return code;
        }

        public static RequestType getType(Integer numeral) {
            for (RequestType ds : values())
                if (ds.code.equals(numeral))
                    return ds;
            return null;
        }
    }
}
