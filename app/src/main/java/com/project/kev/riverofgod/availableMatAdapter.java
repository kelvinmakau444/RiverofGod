package com.project.kev.riverofgod;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Kev on 3/9/2017.
 */

public class availableMatAdapter extends RecyclerView.Adapter<availableMatAdapter.myViewHolder> {
    private List<availableMat> carList;
    private Context context;
    private LayoutInflater inflater;




    public availableMatAdapter(Context context,List<availableMat> carList){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.carList=carList;


    }


    public class myViewHolder extends RecyclerView.ViewHolder{
        public ImageView driver_pic;
        public TextView plate,available_seats,driver, driver_id;
        Button book_now;


        public myViewHolder(View itemView) {
            super(itemView);

           // driver_pic=(ImageView)itemView.findViewById(R.id.driver_pic);
            plate=(TextView)itemView.findViewById(R.id.car_plate);
            available_seats=(TextView)itemView.findViewById(R.id.car_available_seats);
            driver=(TextView)itemView.findViewById(R.id.car_driver_names);
            driver_id=(TextView)itemView.findViewById(R.id.car_driver_id);
            book_now=(Button)itemView.findViewById(R.id.book_now_btn);

            book_now.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    //event eve;
                    //eve=eventList.get(position);

                   // String imgUrl=eve.images;

                    Intent intent=new Intent(context,SelectCarSeat.class);


                  //  intent.putExtra("tag",eve.tag);
                   // intent.putExtra("pic",imgUrl);
                   // intent.putExtra("title",eve.title);
                    //intent.putExtra("desc",eve.description);
                    //intent.putExtra("time","Posted on: "+eve.date);

                    context.startActivity(intent);

                }
            });

        }



    }




    @Override
    public availableMatAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.available_mat_item,parent,false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(availableMatAdapter.myViewHolder holder, int position) {





        availableMat newMat= carList.get(position);
        holder.plate.setText("Matatu Plate No. : "+newMat.plate);
        holder.available_seats.setText("Available seats :"+newMat.available_seats);
        holder.driver.setText("Driver: "+newMat.driver);
        holder.driver_id.setText("Driver Id: "+newMat.driver_id);

       // Glide.with(context).load(newMat.driver_image).diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().placeholder(R.drawable.loader).error(R.drawable.profile).override(200,100).into(holder.driver_pic);

    }

    @Override
    public int getItemCount() {

        return carList.size();
    }

}
