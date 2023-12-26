package com.example.sellseeds.fragments.Buyer.registerUser

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.sellseeds.R
import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.databinding.ActivityLoginBinding
import com.example.sellseeds.databinding.ActivityRegisterUserBinding
import com.example.sellseeds.model.Repositories
import com.example.sellseeds.viewModelCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegisterUserFragment : Fragment() {

    lateinit var binding:ActivityRegisterUserBinding
    val viewModel by viewModelCreator {RegisterUserViewModel(Repositories.accountsRepository, Repositories.userCurrentId) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =ActivityRegisterUserBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment



        viewModel.state.observe(viewLifecycleOwner){
            val userState:RegisterUserState =it
            binding.searchViewGroupEleven.setQuery(it.name, false);
            binding.searchViewGroupEleven.clearFocus();
            binding.etGroupTwelve.setText(it.number)
            binding.etGroupThirteen.setText(it.adress)
                binding.etGroupFive.setText(it.email)
                binding.etGroupFive.setText(it.password)
        }
        viewModel.navigateToMain.observe(viewLifecycleOwner){
            Log.d("ryewguygweqyruqwegruie", it.toString())
            if(it){

                findNavController().navigate(R.id.action_registerUserFragment_to_buyer_HomePageFragment)
                viewModel.navigateToMain.postValue(false)


            }
            else {

                findNavController().navigateUp()
            }
        }



        binding.tulutulu.imageArrowleft.setOnClickListener{
            findNavController().popBackStack()

        }
        binding.btnRegister.setOnClickListener{
            var name =binding.searchViewGroupEleven.getQuery().toString()
            var number =binding.etGroupTwelve.text.toString()
            var adress =binding.etGroupThirteen.text.toString()
            var email =binding.etGroupFive.text.toString()
            var password =binding.etGroupTen.text.toString()

            if(name.isNotBlank() && number.isNotBlank() && adress.isNotBlank() && email.isNotBlank() && password.isNotBlank()){
                var user = User(0,//Will be auto generated
                    name =name, adress =adress, email = email , number = number , orders = mutableListOf() , password = password)

                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.register(user)

                }


            }
            else{
                Toast.makeText(context ,"Full all " ,Toast.LENGTH_LONG).show()
            }


           // findNavController().navigate(R.id.action_registerUserFragment_to_buyer_HomePageFragment)
        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var name =binding.searchViewGroupEleven.getQuery().toString()
        var number =binding.etGroupTwelve.text.toString()
        var adress =binding.etGroupThirteen.text.toString()
        var email =binding.etGroupFive.text.toString()
        var password =binding.etGroupFive.text.toString()
        val registerUserState =RegisterUserState(name =name ,adress =adress ,number =number ,email =email ,password =password)

        viewModel.state.postValue(registerUserState)
    }



}