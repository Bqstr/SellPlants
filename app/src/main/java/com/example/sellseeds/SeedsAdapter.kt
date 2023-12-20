package com.example.sellseeds

import android.R
import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.databinding.ProductDetailsBinding
import com.example.sellseeds.databinding.RowGridrectangletwelveTwoBinding


class SeedsAdapter(val navConteoller: NavController, val context: Context?  ,var layoutInflater: LayoutInflater): RecyclerView.Adapter<SeedsAdapter.SeedsViewHolder>() {
    var seeds = mutableListOf<Seed>()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }


    val builder  =AlertDialog.Builder(context)



    //когда recyclerView создает новый элемент
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeedsViewHolder {
        val inflater =LayoutInflater.from(parent.context)
    val binding =RowGridrectangletwelveTwoBinding.inflate(inflater,parent,false)
    return SeedsViewHolder(binding)
    }




    override fun getItemCount()= seeds.size




        //когда recyclerView хочет обновить существующий элемиент
    override fun onBindViewHolder(holder: SeedsViewHolder, position: Int) {
        val plant =seeds[position]
            holder.binding.root.setOnClickListener{

                var binding =ProductDetailsBinding.inflate(layoutInflater)



                val layut =binding.dialogLayout

                builder.setView(binding.root)

                val alertDialog =builder.create()

                binding.imageArrowleft.setOnClickListener{
                    alertDialog.cancel()
                }



                if(alertDialog.window!=null){
                    alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
                }

                alertDialog.show()





//                val args = Bundle()
//                args.putSerializable("12321321",plant )
//                navConteoller.navigate(R.id.action_sellerHomePageFragment_to_sellerProductInfoFragment,args)


            }
            with(holder.binding) {

                imageRectangleTwelveTwo.setImageResource(plant.images[0])
                plantName.text =plant.name
                plantPrice.text =plant.price.toString()
                plantCount.text =plant.quantity.toString()

            }

    }





    class SeedsViewHolder(val binding:RowGridrectangletwelveTwoBinding):RecyclerView.ViewHolder(binding.root){

    }
}