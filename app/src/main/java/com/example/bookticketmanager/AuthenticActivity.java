package com.example.bookticketmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bookticketmanager.adapter.TicketAdapter;
import com.example.bookticketmanager.models.Ticket;
import com.example.bookticketmanager.models.TicketList;

import java.util.ArrayList;
import java.util.Arrays;

public class AuthenticActivity extends AppCompatActivity {
    private TicketList ticketList;
    private RecyclerView recyclerViewTicket;
    private Button btnAdvancePage;
    private Button btnShipPage;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_3_layout);
        this.setTitle("Authentic Tickets");
        intent = new Intent();
        btnAdvancePage = (Button) findViewById(R.id.btnPage13);
        btnShipPage = (Button) findViewById(R.id.btnPage23);
        recyclerViewTicket = (RecyclerView) findViewById(R.id.recyclerTicket);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ticketList = new TicketList();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewTicket.setLayoutManager(layoutManager);
        TicketAdapter adapter = new TicketAdapter(this, ticketList.getListTicket(), 2);
        recyclerViewTicket.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnAdvancePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(AuthenticActivity.this, AdvanceActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        btnShipPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(AuthenticActivity.this, ShipActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }
}