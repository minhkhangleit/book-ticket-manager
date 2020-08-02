package com.example.bookticketmanager.models;

import java.util.ArrayList;
import java.util.Date;

public class Ticket {
    private String ticketID;
    private String movieName;
    private String imageUrl;
    private String ticketDate;
    private String ticketTime;
    private String ticketRoom;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private double ticketPrice;
    private Date orderTime;
    private int ticketPayType = 0;
    private int ticketStatus = 0;
    private ArrayList<String> listSeat;

    public Ticket() {}

    public Ticket(String ticketID, String movieName, String imageUrl, String ticketDate, String ticketTime, String ticketRoom, int ticketPayType, int ticketStatus, double ticketPrice, ArrayList<String> listSeat, String customerName, String customerPhone, Date orderTime) {
        this.ticketID = ticketID;
        this.movieName = movieName;
        this.imageUrl = imageUrl;
        this.ticketDate = ticketDate;
        this.ticketTime = ticketTime;
        this.ticketRoom = ticketRoom;
        this.ticketPrice = ticketPrice;
        this.listSeat = listSeat;
        this.ticketPayType = ticketPayType;
        this.ticketStatus = ticketStatus;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.orderTime = orderTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhone() {
        return this.customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getTicketID() {
        return ticketID;
    }

    public int getTicketPayType() {
        return ticketPayType;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public int isTicketStatus() {
        return ticketStatus;
    }

    public double getTotalPrice() {
        return this.ticketPrice * this.listSeat.size();
    }

    public String getMovieName() {
        return movieName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTicketDate() {
        return ticketDate;
    }

    public String getTicketTime() {
        return ticketTime;
    }

    public String getTicketRoom() {
        return ticketRoom;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public String getTicketListString(){
        String result = "";
        for(String str: listSeat){
            result += str + " - ";
        }
        return result.substring(0, result.length() - 3);
    }

    public ArrayList<String> getListSeat() {
        return listSeat;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTicketDate(String ticketDate) {
        this.ticketDate = ticketDate;
    }

    public void setTicketTime(String ticketTime) {
        this.ticketTime = ticketTime;
    }

    public void setTicketRoom(String ticketRoom) {
        this.ticketRoom = ticketRoom;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public void setListSeat(ArrayList<String> listSeat) {
        this.listSeat = listSeat;
    }

    public void setTicketStatus(int ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public void setTicketPayType(int ticketPayType) {
        this.ticketPayType = ticketPayType;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
}
