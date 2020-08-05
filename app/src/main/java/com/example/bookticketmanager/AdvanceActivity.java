package com.example.bookticketmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.bookticketmanager.adapter.TicketAdapter;
import com.example.bookticketmanager.models.Ticket;
import com.example.bookticketmanager.models.TicketList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class AdvanceActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ArrayList<Ticket> listTicket = new ArrayList<>();
    private RecyclerView recyclerViewTicket;
    private Button btnShipPage;
    private Button btnAuthenticPage;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_1_layout);
        this.setTitle("Advance Tickets");
        intent = new Intent();
        btnShipPage = (Button) findViewById(R.id.btnPage21);
        btnAuthenticPage = (Button) findViewById(R.id.btnPage31);
        recyclerViewTicket = (RecyclerView) findViewById(R.id.recyclerTicket);
    }

    @Override
    protected void onResume() {
        listTicket.clear();
        super.onResume();
        db.collection("tickets")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String ticketId = document.getId();
                        String movieName = document.get("movieName").toString();
                        String imageUrl = document.get("imageUrl").toString();
                        String ticketDate = document.get("movieDate").toString();
                        String ticketTime = document.get("movieTime").toString();
                        String ticketRoom = document.get("ticketRoom").toString();
                        int ticketPayType = Integer.parseInt(document.get("paymentType").toString());
                        int ticketStatus = Integer.parseInt(document.get("paymentStatus").toString());
                        double ticketPrice = Double.parseDouble(document.get("totalPrice").toString());
                        ArrayList<String> listSeat = (ArrayList<String>) document.get("listSeat");
                        String customerName = document.get("customerFullName").toString();
                        String customerPhone = document.get("customerPhone").toString();
                        Timestamp orderTime = (Timestamp) document.get("orderDate");
                        Log.d("customerPhone", customerPhone);
                        Ticket ticket = new Ticket(ticketId, movieName, imageUrl, ticketDate, ticketTime, ticketRoom, ticketPayType, ticketStatus, ticketPrice, listSeat, customerName, customerPhone, new Date(orderTime.getSeconds()));

                        if (ticketPayType == 1) ticket.setCustomerAddress(document.get("customerAddress").toString());

                        listTicket.add(ticket);
                        Log.d("triet-test", document.getId() + " => " + document.getData());
                    }
                    LinearLayoutManager layoutManager = new LinearLayoutManager(AdvanceActivity.this);
                    layoutManager.setOrientation(RecyclerView.VERTICAL);
                    recyclerViewTicket.setLayoutManager(layoutManager);
                    TicketAdapter adapter = new TicketAdapter(AdvanceActivity.this, listTicket, 0);
                    recyclerViewTicket.setAdapter(adapter);
                } else {
                    Log.d("triet-test", "Error getting documents.", task.getException());
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnShipPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(AdvanceActivity.this, ShipActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        btnAuthenticPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(AdvanceActivity.this, AuthenticActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }
}