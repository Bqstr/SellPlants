package com.example.sellseeds.adapters

import android.app.AlertDialog
import android.content.Context
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sellseeds.dataClass_enum.Converter
import com.example.sellseeds.dataClass_enum.OrderStatus
import com.example.sellseeds.dataClass_enum.Orders
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.databinding.AddToCartBinding
import com.example.sellseeds.databinding.BuyerShopItemsBinding
import com.example.sellseeds.model.Repositories
import com.example.sellseeds.model.orders.OrdersRepository
import com.example.sellseeds.model.user.UserCurrentId
import com.example.sellseeds.model.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BuyerShopDetailsAdapter(
    val navConteoller: NavController, val context: Context?,
    val layoutInflater: LayoutInflater,
    val ordersRepository: OrdersRepository,
    val currentUser_Id:UserCurrentId,
    val userRepository: UserRepository
): RecyclerView.Adapter<BuyerShopDetailsAdapter.BuyerShopDetailsViewHolder>(){
    val builder  = AlertDialog.Builder(context)
    var plants = mutableListOf<Seed>()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyerShopDetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =BuyerShopItemsBinding.inflate(inflater,parent,false)
        return BuyerShopDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuyerShopDetailsViewHolder, position: Int) {
        val plant =plants[position]
        with(holder.binding){

            txtMonsteraplantsOne.text =plant.name
            txtFlowerplantOne.text =Converter.CategorytoString(plant.category)//category
            txtPriceThree.text =plant.price.toString()//price
            imageRectangleTwelveOne.setImageResource(plant.images)   //image

            if(plant.discount.haveDiscount){
                txtOffer.visibility = View.VISIBLE
                txtNewPrice.visibility =View.VISIBLE

                txtOffer.text ="${(plant.discount.amoutOfPrice?.times(100)).toString()}%"
                txtNewPrice.text =txtPriceThree.text
                txtNewPrice.setPaintFlags(txtPriceThree.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
                var i :Int=(txtNewPrice.text.toString().toInt()*plant.discount.amoutOfPrice.toString().toDouble()).toInt()
                txtPriceThree.text =(txtNewPrice.text.toString().toInt() -i).toString()
            }
            else{
                txtOffer.visibility = View.GONE
                txtNewPrice.visibility =View.GONE
            }

        }

        holder.binding.root.setOnClickListener{
            var binding = AddToCartBinding.inflate(layoutInflater)
            var quatity =1

            val layut =binding.root

            binding.imageRectangleTwelveFour.setImageResource(plant.images)
            binding.txtMonsteraplantsFour.text =plant.name
            binding.txtLanguage.text =plant.name
            binding.txtPriceEight.text =holder.binding.txtPriceThree.text
            binding.txtPriceNine.text =holder.binding.txtPriceThree.text//total price
            binding.imageVectorThree.setOnClickListener{
                //plus
                if(quatity<999) {
                    quatity++
                    binding.txtPriceNine.text =(holder.binding.txtPriceThree.text.toString().toInt()*quatity).toString()
                    binding.txtOne.text=quatity.toString()
                }
            }

            binding.imageVectorTwo.setOnClickListener{
                //minus
                if(quatity>1){
                    quatity--
                    binding.txtPriceNine.text =(holder.binding.txtPriceThree.text.toString().toInt()*quatity).toString()
                    binding.txtOne.text =quatity.toString()
                }
            }


            builder.setView(binding.root)

            val alertDialog =builder.create()

            binding.btnAddToCart.setOnClickListener{
                //add to cart!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                val lifecycleScope =
                    holder.itemView.findViewTreeLifecycleOwner()!!.lifecycleScope
                lifecycleScope.launch (Dispatchers.IO){
                    val amount =binding.txtOne.text.toString().toInt()
                    val total_price =binding.txtPriceNine.text.toString().toInt()
                    val current_id1 =currentUser_Id.getCurrentUserId()
                    val user =userRepository.getUserById(current_id1)
                    ordersRepository.createOrder(Orders(0 ,total_price ,plant ,amount ,plant.id.toInt() ,current_id1 ,userRepository.getUserById(current_id1),OrderStatus.InProgress ,123123 ,user.adress))
                    alertDialog.cancel()

                }


            }



            if(alertDialog.window!=null){
                alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            }

            alertDialog.show()

        }
    }

    override fun getItemCount(): Int =plants.size
    class BuyerShopDetailsViewHolder(val binding:BuyerShopItemsBinding ):RecyclerView.ViewHolder(binding.root)
}