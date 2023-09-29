package com.example.sellseeds

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sellseeds.databinding.FragmentListBinding
import com.google.android.material.navigation.NavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters

    lateinit var binding :FragmentListBinding


//take photos from this site !!!!!!!!!!!!!!!!!https://www.thesill.com/!!!!!!!!!!!!!!!!!!!
    //тут я понял что более логичным названием будет Plants а не Seeds



lateinit var adapter: SeedsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        binding = FragmentListBinding.inflate(layoutInflater)
        adapter = SeedsAdapter()

        val layoutManager =GridLayoutManager(context,2)


        binding.recyclerView.adapter =adapter
        binding.recyclerView.layoutManager =layoutManager


        adapter.seeds =createData()














        return binding.root



    }

    private fun createData(): MutableList<Seed> {
        val mutableList = mutableListOf<Seed>(
            Seed(0,"Olive Tree","description",1000, listOf(R.drawable.olive_tree)),
            Seed(1,"Money Tree","description",2000, listOf(R.drawable.money_tree)),
            Seed(2,"Faux Palm Tree","description",3000, listOf(R.drawable.faux_palm_tree)),
            Seed(3,"Kek Tree","description",999, listOf(R.drawable.olive_tree)),



            )
        return mutableList

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}