package com.example.bookticketmanager.models;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class TicketList{
    private ArrayList<Ticket> listTicket = new ArrayList<>();

    public TicketList() {

//        listTicket.add(new Ticket("1", "Avenger: End Game", "https://images-na.ssl-images-amazon.com/images/I/71niXI3lxlL._AC_SY741_.jpg", "Dec-14-2020", "16:30", "A1", 0, 0, 45000, new ArrayList<String>(Arrays.asList("A1", "A2")), "Minh Khang", "0985293047", new Date()));
//        listTicket.add(new Ticket("2", "Mắt Biếc","https://images-na.ssl-images-amazon.com/images/I/71niXI3lxlL._AC_SY741_.jpg", "Dec-14-2020", "16:30", "A1", 0, 0, 45000, new ArrayList<String>(Arrays.asList("A1", "A2")), "Minh Khang", "0985293047", new Date()));
//        listTicket.add(new Ticket("3", "Avenger: End Game", "https://images-na.ssl-images-amazon.com/images/I/71niXI3lxlL._AC_SY741_.jpg", "Dec-14-2020", "16:30", "A1", 1, 0, 45000, new ArrayList<String>(Arrays.asList("A1", "A2")), "Minh Khang", "0985293047", new Date()));
//        listTicket.add(new Ticket("4", "Ròm", "https://images-na.ssl-images-amazon.com/images/I/71niXI3lxlL._AC_SY741_.jpg", "Dec-14-2020", "16:30", "A1", 0, 0, 45000, new ArrayList<String>(Arrays.asList("A1", "A2")), "Minh Khang", "0985293047", new Date()));
//        listTicket.add(new Ticket("5", "Avenger: End Game", "https://images-na.ssl-images-amazon.com/images/I/71niXI3lxlL._AC_SY741_.jpg", "Dec-14-2020", "16:30", "A1", 1, 0, 45000, new ArrayList<String>(Arrays.asList("A1", "A2")), "Minh Khang", "0985293047", new Date()));
//        listTicket.add(new Ticket("6", "Tôi thấy hoa vàng trên cỏ xanh", "https://images-na.ssl-images-amazon.com/images/I/71niXI3lxlL._AC_SY741_.jpg", "Dec-14-2020", "16:30", "A1", 1, 1, 45000, new ArrayList<String>(Arrays.asList("A1", "A2")), "Minh Khang", "0985293047", new Date()));
//        listTicket.add(new Ticket("7", "Avenger: End Game", "https://images-na.ssl-images-amazon.com/images/I/71niXI3lxlL._AC_SY741_.jpg", "Dec-14-2020", "16:30", "A1",0, 1, 45000, new ArrayList<String>(Arrays.asList("A1", "A2")), "Minh Khang", "0985293047", new Date()));
    }

    public ArrayList<Ticket> getListTicket() {
        return listTicket;
    }

    public void setListTicket(ArrayList<Ticket> listTicket) {
        this.listTicket = listTicket;
    }
}
