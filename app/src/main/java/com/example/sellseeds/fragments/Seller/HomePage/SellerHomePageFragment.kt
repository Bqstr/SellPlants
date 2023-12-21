package com.example.sellseeds.fragments.Seller.HomePage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.clearFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sellseeds.adapters.OrdersAdapter

import com.example.sellseeds.R
import com.example.sellseeds.adapters.SeedsAdapter
import com.example.sellseeds.dataClass_enum.Category
import com.example.sellseeds.dataClass_enum.Discount
import com.example.sellseeds.dataClass_enum.OrderStatus
import com.example.sellseeds.dataClass_enum.Orders
import com.example.sellseeds.dataClass_enum.Rating
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.databinding.ActivitySellerHomepageOneBinding
import com.example.sellseeds.fragments.Seller.AddProduct.ADD_PRODUCT_KEY
import com.example.sellseeds.fragments.Seller.OrderFragment.ActionOrder
import com.example.sellseeds.fragments.Seller.OrderFragment.BACK_FROM_ORDER
import com.example.sellseeds.model.Repositories
import com.example.sellseeds.viewModelCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val GO_SELLER_EDITPROFILE ="GO_SELLER_EDITPROFILE"
const val GO_ORDER_DETAILS ="GO_ORDER_DETAILS"
class SellerHomePageFragment : Fragment() {

lateinit var binding:ActivitySellerHomepageOneBinding

val viewModel by viewModelCreator { SellerHomePageViewModel(Repositories.shopRepository ,Repositories.plantsRepository ,Repositories.shopCurrentId) }
var isProductPressed =true
    lateinit var productAdapter: SeedsAdapter
    lateinit var orderAdapter: OrdersAdapter
init {
    //intialize all data from database


}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =ActivitySellerHomepageOneBinding.inflate(layoutInflater)


        productAdapter = SeedsAdapter(findNavController(), context, layoutInflater)

         orderAdapter = OrdersAdapter(findNavController(),context, false)


        viewModel.shop_currentId.observe(viewLifecycleOwner){
            Log.d("xzxzxzxzx" ,it.toString())

        }


            lifecycleScope.launch (Dispatchers.IO){
                viewModel.getCurrentId()
            }
        if(arguments!=null){
            Log.d("111111111111111111","not null  ")

            if(arguments?.getSerializable(ADD_PRODUCT_KEY)!=null){
                val product =arguments?.getSerializable(ADD_PRODUCT_KEY) as Seed
                Log.d("111111111111111111",product.name)
                viewModel.addProduct(product)
            }
            Log.d("111111111111111111",(arguments?.getSerializable(BACK_FROM_ORDER) as ActionOrder).orderId.toString())

            if(arguments?.getSerializable(BACK_FROM_ORDER)!=null){

                //viewModel.addProduct(product)
            }


        }
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getAllMyPlants()
        }
        viewModel.plantList.observe(viewLifecycleOwner) {

            productAdapter.seeds =it.toMutableList()
            productAdapter.notifyDataSetChanged()

        }








        setFragmentResultListener("KEY") { key, bundle ->
            Log.d("123123123123123", key)

            if(bundle.getSerializable(ADD_PRODUCT_KEY)!=null){
                val result=bundle.getSerializable(ADD_PRODUCT_KEY) as Seed
                viewModel.addProduct(result)
            }
            Log.d("111111111111111111","123123123123")

            if(bundle.getSerializable(BACK_FROM_ORDER)!=null){
                val actionOrder =bundle?.getSerializable(BACK_FROM_ORDER) as ActionOrder
                Log.d("111111111111111111","${actionOrder.orderId.toString()}   ${actionOrder.toDeliver}    ")
            }


            clearFragmentResult("key")

        }







        binding.myaccountBtn.setOnClickListener{
            val arg =Bundle()
           // arg.putSerializable(GO_SELLER_EDITPROFILE , createSellerData())
           // arg.putSerializable("12323")  Pass Seller Infomration


            findNavController().navigate(R.id.action_sellerHomePageFragment_to_seller_EditProfileFragment)
        }


        viewModel.isProductPressed.observe(viewLifecycleOwner,{
            Log.d("12312312312312321","changed")
            isProductPressed =it
        })
        viewModel.orderList.observe(viewLifecycleOwner,{
            orderAdapter.orders =it
            orderAdapter.notifyDataSetChanged()
        })
        viewModel.productList.observe(viewLifecycleOwner,{
            Log.d("dsfnjkasdbfjsabdljfksa",it.get(0).name)
            productAdapter.seeds =it
            productAdapter.notifyDataSetChanged()

        })




        binding.btnProduct.setOnClickListener{
            Log.d("12312312312312321",isProductPressed.toString())
            if(!isProductPressed) productPressed()
        }
        binding.btnOrders.setOnClickListener{
            Log.d("12312312312312321",isProductPressed.toString())

            if(isProductPressed) orderPressed()
        }

        productPressed()




        binding.floatingBtnFloatingactionbutton.setOnClickListener{
             findNavController().navigate(R.id.action_sellerHomePageFragment_to_addProductFragment)
        }



        // Inflate the layout for this fragment
        return binding.root
    }



    private fun productPressed() {
        binding.sellerHomePageRecyclerView.adapter =productAdapter
        val layoutManager =GridLayoutManager(context,2)
        binding.sellerHomePageRecyclerView.layoutManager =layoutManager



        viewModel.changeAdapter(true)
        binding.btnProduct.setBackgroundColor(getResources().getColor(R.color.light_green_800))
        binding.btnOrders.setBackgroundColor(getResources().getColor(R.color.white))
    }

    private fun orderPressed() {
       // orderAdapter = OrdersAdapter(findNavController(),context)
        binding.sellerHomePageRecyclerView.adapter =orderAdapter
        val layoutManager =LinearLayoutManager(context)
        binding.sellerHomePageRecyclerView.layoutManager =layoutManager
//




        viewModel.changeAdapter(false)

        binding.btnOrders.setBackgroundColor(getResources().getColor(R.color.light_green_800))
        binding.btnProduct.setBackgroundColor(getResources().getColor(R.color.white))

    }



//    private fun addProduct(product: Seed) {
//        viewModel.addProduct(product)
//        productAdapter.notifyDataSetChanged()
//
//    }














//
//    private fun createSellerData(): Shop {
//        return (Shop(
//            0,
//            "Hydra",
//            "3_head_gydra@gmai.com",
//            "jail",
//            "+666",
//            createData(),
//            createOrderData(),
//            R.drawable.img_rectangle19,
//            Rating.FIVE_STAR,
//            100,"123"
//        ))
//    }
//
//
//     fun createData(): MutableList<Seed> {
//        val mutableList = mutableListOf<Seed>(
//            Seed(
//                0,
//                "Olive Tree",
//                "description",
//                1000,
//                R.drawable.img_rectangle12,
//                Category.SmallPlant,
//                1,
//                Discount(true, 0.2)
//            ),
//            Seed(
//                1,
//                "Money Tree",
//                "description",
//                2000,
//                listOf(R.drawable.img_rectangle12_1),
//                Category.SmallPlant,
//                1,
//                Discount(true, 0.2)
//            ),
//            Seed(
//                2,
//                "Faux Palm Tree",
//                "description",
//                3000,
//                listOf(R.drawable.img_rectangle12_108x110),
//                Category.SmallPlant,
//                1,
//                Discount(true, 0.2)
//            ),
//            Seed(
//                3,
//                "Kek Tree",
//                "description",
//                999,
//                listOf(R.drawable.img_rectangle12_2),
//                Category.SmallPlant,
//                1,
//                Discount(true, 0.2)
//            ),
//            Seed(
//                0,
//                "Olive Tree",
//                "description",
//                1000,
//                listOf(R.drawable.img_rectangle12),
//                Category.SmallPlant,
//                1,
//                Discount(true, 0.2)
//            ),
//            Seed(
//                2,
//                "Faux Palm Tree",
//                "description",
//                3000,
//                listOf(R.drawable.img_rectangle12_108x110),
//                Category.SmallPlant,
//                1,
//                Discount(true, 0.2)
//            ),
//
//
//            )
//        return mutableList
//
//    }
//
//    fun createOrderData(): MutableList<Orders> {
//        val examplePlant = Seed(
//            0,
//            "Olive Tree",
//            "description",
//            1000,
//            listOf(R.drawable.img_rectangle12),
//            Category.SmallPlant,
//            1,
//            Discount(true, 0.2)
//        )
//        val exampleShop = Shop(0,"1","123","123","123",createData() , mutableListOf(),R.drawable.img_rectangle19 , Rating.FOUR_STAR ,100,"123")
//        val exampleUser =
//            User(0, "USER_NAME", "email_user@gmail.com", "USER_ADRESS", "+777777777777777", mutableListOf<Orders>(),"123")
//
//        var mut = mutableListOf(
//            Orders(
//                123,
//                1000,
//                examplePlant,
//                10,
//                adress = "ADRESS",
//                shop = exampleShop,
//                buyer = exampleUser,
//                date = 12312,
//                status = OrderStatus.InProgress
//            ),
//            Orders(
//                124,
//                2000,
//                examplePlant,
//                20,
//                adress = "ADRESS",
//                shop = exampleShop,
//                buyer = exampleUser,
//                date = 12312123,
//                status = OrderStatus.Completed
//            ),
//
//            Orders(
//                125,
//                6000,
//                examplePlant,
//                10,
//                adress = "ADRESS",
//                shop = exampleShop,
//                buyer = exampleUser,
//                date = 12312,
//                status = OrderStatus.Canceled
//            )
//        )
//
//        return mut
//    }
//
//

    }

