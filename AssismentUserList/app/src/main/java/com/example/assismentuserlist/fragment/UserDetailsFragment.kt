package com.example.assismentuserlist.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.assismentuserlist.R
import com.example.assismentuserlist.modle.UserDetails
import com.example.assismentuserlist.utils.DataOperation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var tvName: TextView
    lateinit var tvEmail: TextView
    lateinit var tvRegistrationDate: TextView
    lateinit var tvCountry: TextView
    private lateinit var userName: String
    val args:UserDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        tvName = view.findViewById(R.id.tvName)
        tvEmail = view.findViewById(R.id.tvEmail)
        tvRegistrationDate = view.findViewById(R.id.tvRegistrationDate)
        tvCountry = view.findViewById(R.id.tvCountry)

        setUserDetails()

    }

    private fun setUserDetails(){
        val userDetails = args.userdetails
        tvName.text = DataOperation.getFullName(userDetails.name)
        tvEmail.text = userDetails.email
        tvRegistrationDate.text = userDetails.registered.date
        tvCountry.text = userDetails.location.country
    }
    companion object{

        private const val TAG = "UserDetailsFragment"
    }
}