package com.openclassrooms.magicgithub.ui.user_list

import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.openclassrooms.magicgithub.databinding.ActivityListUserBinding
import com.openclassrooms.magicgithub.fragments.ListUserFragment
import com.openclassrooms.magicgithub.repository.UserRepository

class ListUserActivity : AppCompatActivity(){
    @VisibleForTesting
    lateinit var repository: UserRepository
    private lateinit var binding: ActivityListUserBinding

    // OVERRIDE ---
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showFragment(ListUserFragment())
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.listUserFragmentWrapper.id, fragment)
            addToBackStack(null)
        }.commit()
    }



}