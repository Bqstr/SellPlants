package com.example.sellseeds.fragments.LogInFramgnet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.sellseeds.MainActivity

import com.example.sellseeds.R
import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.databinding.ActivityLoginBinding
import com.example.sellseeds.model.Repositories
import com.example.sellseeds.viewModelCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [SignInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignInFragment : Fragment() {
val viewModel by viewModelCreator{SignInViewModel(Repositories.accountsRepository ,Repositories.userCurrentId, Repositories.shopRepository)  }
lateinit var binding:ActivityLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =ActivityLoginBinding.inflate(layoutInflater)

        viewModel.userData.observe(viewLifecycleOwner) {
            Log.d("abibbb",it.toString())
            Log.d("abibbb",it.size.toString())

            for (a in 0 until it.size){

                Log.d("abibbb",it.get(a).name)

            }

        }
//        binding.register.setOnClickListener{
//            lifecycleScope.launch(Dispatchers.IO) {
//
//                viewModel.register(User(0,"admin","admin@gmail.com","adressss","+777", mutableListOf(),"123"))
//
//            }
//        }
//        binding.showResult.setOnClickListener{
//            lifecycleScope.launch(Dispatchers.IO) {
//                viewModel.userData.postValue(viewModel.getData())
//            }
//
//        }




        (requireActivity() as MainActivity).binding.bottomNavigationView.visibility=View.INVISIBLE


        viewModel.navigateToMain.observe(viewLifecycleOwner){
            Log.d("dhhdhdhdhd","in observer")
            if(it==null){}
            else {
                if (it) {
                    viewModel.navigateToMain.postValue(null)
                    findNavController().navigate(R.id.action_signInFragment_to_buyer_HomePageFragment)

                } else {
                    viewModel.navigateToMain.postValue(null)

                    findNavController().navigate(R.id.action_signInFragment_to_sellerHomePageFragment)


                }
            }
        }
        lifecycleScope.launch(Dispatchers.IO){
           val t = viewModel.getShops()
            for(a in 0 until t!!.size){
                Log.d("aaaaaaaaasdsdsdw", t[a].toString())
            }
        }

        binding.logInApp.setOnClickListener{
            val email =binding.emailLogIn.text.toString()
            val password =binding.passwordLogIn.text.toString()

            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.signInUser(email ,password)
                viewModel.signInShop(email,password)

            }








        }
        binding.registerAsShop.setOnClickListener{

           // findNavController().navigate(R.id.action_signInFragment_to_registerSellerFragment)
        }
        binding.registerAsUser.setOnClickListener{


           // findNavController().navigate(R.id.action_signInFragment_to_registerUserFragment)
        }
            return binding.root


    }


}