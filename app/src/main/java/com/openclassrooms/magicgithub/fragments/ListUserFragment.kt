package com.openclassrooms.magicgithub.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.magicgithub.api.FakeApiService
import com.openclassrooms.magicgithub.di.Injection
import com.openclassrooms.magicgithub.model.User
import com.openclassrooms.magicgithub.repository.UserRepository
import com.openclassrooms.magicgithub.ui.user_list.UserListAdapter
import com.openclassrooms.magicgithub.databinding.ListUsersFragmentBinding

class ListUserFragment: Fragment(), UserListAdapter.Listener  {
    @VisibleForTesting
    lateinit var repository: UserRepository

    // FOR DESIGN ---
    private lateinit var binding: ListUsersFragmentBinding
    lateinit var recyclerView: RecyclerView

    // FOR DATA ---
    private lateinit var apiService: FakeApiService
    private lateinit var adapter: UserListAdapter
    private lateinit var users: MutableList<User>

    // OVERRIDE ---
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ListUsersFragmentBinding.inflate(inflater, container, false)
        binding.activityListUserRv.layoutManager = LinearLayoutManager(context)
        binding.activityListUserRv.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureApi()
        configureFab()
        injectRepository()
        configureRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        loadData(scrollToTop = false)
    }

    // CONFIGURATION ---
    private fun configureApi() {
        apiService = FakeApiService()
    }

    private fun configureRecyclerView() {
        recyclerView = binding.activityListUserRv
        apiService = FakeApiService()
        users = UserRepository.getInstance(apiService).users
        adapter = UserListAdapter(users, this)
        recyclerView.adapter = adapter
    }

    private fun configureFab() {
        binding.activityListUserFab.setOnClickListener { view: View? ->
            repository.generateRandomUser()
            loadData(scrollToTop = true)
        }
    }

    // INJECTION ---
    private fun injectRepository() {
        repository = Injection.createUserRepository()
    }

    private fun loadData(scrollToTop: Boolean) {
        println("**newusers" + users.count())
        adapter.updateList(Injection.createUserRepository().users)

        if (scrollToTop) {
            binding.activityListUserRv.layoutManager?.smoothScrollToPosition(recyclerView, null, 0);
        }

    }

    // ACTIONS ---
    override fun onClickDelete(user: User) {
        Log.d(ListUserFragment::class.java.name, "User tries to delete a item.")
        repository.deleteUser(user)
        loadData(scrollToTop = false)
    }
}