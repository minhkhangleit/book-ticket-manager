package com.example.bookticketmanager.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookticketmanager.R;
import com.example.bookticketmanager.models.DownloadImageTask;
import com.example.bookticketmanager.models.Ticket;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.DataViewHolder>{
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ArrayList<Ticket> tickets;
    private Context context;
    private int statusTickets = 0;
    DecimalFormat formatterPrice = new DecimalFormat("#.##");

    public TicketAdapter(Context context, ArrayList<Ticket> tickets, int status) {
        this.context = context;
        this.tickets = getStatusTicket(tickets, status);
        this.statusTickets = status;
    }

    @Override
    public int getItemCount() {
        return tickets == null ? 0 : tickets.size();
    }

    @Override
    public TicketAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataViewHolder((View)LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket_layout, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    private ArrayList<Ticket> getStatusTicket(ArrayList<Ticket> tickets, int status){
        ArrayList<Ticket> result = new ArrayList<>();
        switch (status){
            case 0:
                for(Ticket ticket: tickets){
                    if(ticket.getTicketPayType() == 0){
                        result.add(ticket);
                    }
                }
                break;
            case 1:
                for(Ticket ticket: tickets){
                    if(ticket.getTicketPayType() == 1){
                        result.add(ticket);
                    }
                }
                break;
            default :
                for(Ticket ticket: tickets){
                    if(ticket.isTicketStatus() == 1){
                        result.add(ticket);
                    }
                }
                break;
        }
        return result;
    }

    @Override
    public void onBindViewHolder(final TicketAdapter.DataViewHolder holder, final int position) {
        final Ticket ticket = tickets.get(position);
        new DownloadImageTask(holder.imgTicket).execute(ticket.getImageUrl());
        holder.txtMovieName.setText(ticket.getMovieName());
        holder.txtTicketDate.setText(ticket.getTicketDate());
        holder.txtTicketTime.setText(ticket.getTicketTime());
        holder.txtTicketAmount.setText(String.valueOf(ticket.getListSeat().size()));
        holder.txtInfoCustomer.setText(ticket.getCustomerName() + " - " + ticket.getCustomerPhone());
        Log.d("customerPhone-adapter", ticket.getCustomerName() + " - " + ticket.getCustomerPhone());
        holder.txtTicketPrice.setText(formatterPrice.format(ticket.getTicketPrice()) + " vnđ");

        holder.imgMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Customer Infomation");
                String message = "Name: " + ticket.getCustomerName() + "\n";
                message += "Phone: " + ticket.getCustomerPhone() + "\n";
                if(ticket.getCustomerAddress() != null){
                    message += "Address: " + ticket.getCustomerAddress();
                }
                alertDialog.setMessage(message);
                // Alert dialog button
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Alert dialog action goes here
                                // onClick button code here
                                dialog.dismiss();// use dismiss to cancel alert dialog
                            }
                        });
                alertDialog.show();
            }
        });

        holder.btnRemoveTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Remove firebasee by ticketID
                db.collection("tickets")
                        .document(tickets.get(position).getTicketID())
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // tickets.remove(position);
                                tickets.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(context, "Remove ticket done!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Remove ticket FAIL!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        if(statusTickets == 2){
//            holder.btnChecked.setVisibility(View.GONE);
            //Change checked firebasee by ticketID
            //Set status = 0
            holder.btnChecked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Change checked firebasee by ticketID
                    //Set status = 1
                    db.collection("tickets")
                            .document(tickets.get(position).getTicketID())
                            .update("paymentStatus", 0)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    // holder.btnChecked.setVisibility(View.GONE);
                                    tickets.remove(position);
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "The ticket has been Confirmed successfully!", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });
        }else{
            holder.btnChecked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Change checked firebasee by ticketID
                    //Set status = 1
                    db.collection("tickets")
                            .document(tickets.get(position).getTicketID())
                            .update("paymentStatus", 1)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    // tickets.remove(position);
                                    tickets.remove(position);
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "The ticket has been Confirmed successfully!", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });
        }
        holder.txtTicketAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Ticket List");
                alertDialog.setMessage(ticket.getTicketListString());
                // Alert dialog button
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Alert dialog action goes here
                                // onClick button code here
                                dialog.dismiss();// use dismiss to cancel alert dialog
                            }
                        });
                alertDialog.show();
            }
        });
    }

    /**
     * Data ViewHolder class.
     */
    public static class DataViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTicket;
        TextView txtMovieName;
        TextView txtTicketDate;
        TextView txtTicketTime;
        TextView txtTicketAmount;
        TextView txtInfoCustomer;
        TextView txtTicketPrice;
        ImageView imgMoreInfo;
        Button btnRemoveTicket;
        Button btnChecked;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTicket = (ImageView) itemView.findViewById(R.id.imgViewTicket);
            txtMovieName = (TextView) itemView.findViewById(R.id.txtNameMovie);
            txtTicketDate = (TextView) itemView.findViewById(R.id.txtTicketDate);
            txtTicketTime = (TextView) itemView.findViewById(R.id.txtTicketTime);
            txtTicketAmount = (TextView) itemView.findViewById(R.id.txtTicketAmount);
            txtInfoCustomer = (TextView) itemView.findViewById(R.id.txtInfoCustomer);
            txtTicketPrice = (TextView) itemView.findViewById(R.id.txtPriceTicket);
            imgMoreInfo = (ImageView) itemView.findViewById(R.id.btnMoreInfo);
            btnRemoveTicket = (Button) itemView.findViewById(R.id.btnRemove);
            btnChecked = (Button) itemView.findViewById(R.id.btnChecked);
        }

    }
}
