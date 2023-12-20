package com.example.sellseeds.fragments.Seller.EditPlantFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.sellseeds.databinding.PlantDetailsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [EditPlant.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditPlant : Fragment() {

lateinit var binding : PlantDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PlantDetailsBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        return binding.root
    }

    companion object {

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}