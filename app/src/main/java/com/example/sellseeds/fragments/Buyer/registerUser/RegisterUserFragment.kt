package com.example.sellseeds.fragments.Buyer.registerUser

import android.os.Bundle
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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterUserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterUserFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
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
            if(it){
                 findNavController().navigate(R.id.action_registerUserFragment_to_buyer_HomePageFragment)

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
            var password =binding.etGroupFive.text.toString()

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


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterUserFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterUserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}