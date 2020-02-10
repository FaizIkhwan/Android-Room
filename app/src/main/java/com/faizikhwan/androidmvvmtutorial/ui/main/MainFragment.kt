package com.faizikhwan.androidmvvmtutorial.ui.main


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.faizikhwan.androidmvvmtutorial.R
import com.faizikhwan.androidmvvmtutorial.model.Note
import com.faizikhwan.androidmvvmtutorial.ui.newnote.NewNoteFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val TAG = "MainFragment"
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: NoteListAdapter
//    private val newWordActivityRequestCode = 1
    private lateinit var navController: NavController
    private var message: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        navController = Navigation.findNavController(view)
        storeDataToDB()
        setupRecyclerView()
        setupFloatingActionButton()
        observeData()
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
//            data?.getStringExtra(NewNoteFragment.EXTRA_REPLY)?.let {
//                Log.d(TAG, "data: $it")
//                val note = Note(title = it,  description = "hello world 1", priority = 0)
//                mainViewModel.insert(note)
//            }
//        } else {
//            Toast.makeText(context, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
//        }
//    }

    fun storeDataToDB() {
        message = arguments?.getString("message")
        message?.let {
            val note = Note(title = it,  description = "hello world 1", priority = 0)
            mainViewModel.insert(note)
        }
    }

    fun setupFloatingActionButton() {
        floating_action_button.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_newNoteFragment)
        }
    }

    fun setupRecyclerView() {
        context?.let {
            adapter = NoteListAdapter(it)
            recyclerview.adapter = adapter
            recyclerview.layoutManager = LinearLayoutManager(it)
        }
    }

    fun observeData() {
        mainViewModel.allNotes.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setWords(it) }
        })
    }

}
