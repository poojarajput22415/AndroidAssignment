package com.example.assismentuserlist.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.assismentuserlist.R
import com.example.assismentuserlist.repository.UserDataListRepository
import com.example.assismentuserlist.repository.UserDataListRepositoryImpl
import com.example.assismentuserlist.utils.MainViewModelsFactory
import com.example.assismentuserlist.viewmodel.UserListViewModel

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assismentuserlist.UserListAdapter
import com.example.assismentuserlist.handler.OnItemClickListener
import com.example.assismentuserlist.modle.UserDetails

class UserListFragment : Fragment() {

    lateinit var userLisViewModel:UserListViewModel
    private val userDataListRepository: UserDataListRepository = UserDataListRepositoryImpl()
    private lateinit var rvUserList:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userLisViewModel = ViewModelProviders.of(this, MainViewModelsFactory(userDataListRepository))[UserListViewModel::class.java]
        userLisViewModel.getAllMovies()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvUserList = view.findViewById(R.id.rvUserList)
        val mLayoutManager =
            LinearLayoutManager(activity?.applicationContext)
        rvUserList.layoutManager = mLayoutManager
       // super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initObserver(){
        userLisViewModel.userDetailsList.observe(viewLifecycleOwner , Observer {
            val userListAdapter = UserListAdapter(it, object : OnItemClickListener{
                override fun onItemClick(item: UserDetails?) {
                    if (item != null) {
                        Log.d(TAG, "onItemClick: ${item.name}")
                        val action =
                            UserListFragmentDirections.actionBlankFragmentToUserDetailsFragment(item)
                        findNavController().navigate(action)
                    }
                }
            })
            rvUserList.adapter = userListAdapter
        })
    }


    companion object {
        private const val TAG = "UserListFragment"
    }
}