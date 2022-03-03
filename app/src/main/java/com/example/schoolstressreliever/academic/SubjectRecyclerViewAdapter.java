//package com.example.schoolstressreliever.academic;
//
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class SubjectRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
////    ArrayList<Vehicle> vehiclesData;
////    Context currentContext;
////
////
////    public SubjectRecyclerViewAdapter(ArrayList vehiclesListInput, Context context){
////        vehiclesData = vehiclesListInput;
////        this.currentContext = context;
////    }
////
////    @NonNull
////    @Override
////    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_vehicle_view_holder, parent, false );
////        return new SubjectViewHolder(myView);
////    }
////
////    @Override
////    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
////        Vehicle newVehicle = (vehiclesData.get(position));
////
////        String owner = newVehicle.getVehicleOwner();
////        int capacity_as_rating = newVehicle.getRating();
////
////        String str = "";
////        for (String fruit : newVehicle.getRiderIDs()) {
////            str += fruit;
////            str += "\n";
////        }
////
////        //note using rating methods to get capacity because it returns null for some reason
////        //get rating is get capacity
////
////        String displayCapacity = Integer.toString(capacity_as_rating);
////
////        ((SubjectViewHolder) holder).statusText1.setText(displayCapacity);
////
////        ((SubjectViewHolder) holder).nameText.setText(owner);
////
////        ((SubjectViewHolder) holder).statusText1.setText(displayCapacity);
////
////
////        String finalStr = str;
////        ((SubjectViewHolder) holder).getLayout().setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent myIntent = new Intent(view.getContext(), VehicleProfileActivity.class);
////                myIntent.putExtra("ownerName", newVehicle.getVehicleOwner());
////                myIntent.putExtra("location", newVehicle.getLocation());
////                myIntent.putExtra("model", newVehicle.getModel());
////                myIntent.putExtra("capacity", displayCapacity);
////                myIntent.putExtra("type", newVehicle.getVehicleType());
////                myIntent.putExtra("passengers", finalStr);
////                currentContext.startActivity(myIntent);
////            }
////        });
////    }
////
////    @Override
////    public int getItemCount() {
////        return vehiclesData.size();
////    }
////
////    public void setVehiclesData(ArrayList<Vehicle> vehicles)
////    {
////        this.vehiclesData = vehicles;
////    }
//
//
//
//}
