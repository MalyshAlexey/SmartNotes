package com.appprivategalery.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.appprivategalery.myapplication.databinding.ActivityMainBinding
import com.appprivategalery.myapplication.presentation.adapter.AlarmsAdapter
import com.appprivategalery.myapplication.presentation.adapter.EventsAdapter
import com.appprivategalery.myapplication.presentation.adapter.NotesAdapter
import com.appprivategalery.myapplication.presentation.adapter.VoiceNotesAdapter
import com.appprivategalery.myapplication.presentation.viewmodel.alarm.AlarmsViewModel
import com.appprivategalery.myapplication.presentation.viewmodel.alarm.AlarmsViewModelFactory
import com.appprivategalery.myapplication.presentation.viewmodel.event.EventsViewModel
import com.appprivategalery.myapplication.presentation.viewmodel.event.EventsViewModelFactory
import com.appprivategalery.myapplication.presentation.viewmodel.note.NotesViewModel
import com.appprivategalery.myapplication.presentation.viewmodel.note.NotesViewModelFactory
import com.appprivategalery.myapplication.presentation.viewmodel.voiceNote.VoiceNotesModelFactory
import com.appprivategalery.myapplication.presentation.viewmodel.voiceNote.VoiceNotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var notesViewModelFactory: NotesViewModelFactory

    @Inject
    lateinit var notesAdapter: NotesAdapter
    lateinit var notesViewModel: NotesViewModel


    @Inject
    lateinit var voiceNotesViewModelFactory: VoiceNotesModelFactory

    @Inject//provide in di.adapter
    lateinit var voiceNotesAdapter: VoiceNotesAdapter
    lateinit var voiceNotesViewModel: VoiceNotesViewModel

    @Inject
    lateinit var eventsViewModelFactory: EventsViewModelFactory

    @Inject
    lateinit var eventsAdapter: EventsAdapter
    lateinit var eventsViewModel: EventsViewModel

    @Inject
    lateinit var comeEventsAdapter: EventsAdapter
    lateinit var comeEventsViewModel: EventsViewModel

    @Inject
    lateinit var alarmsViewModelFactory: AlarmsViewModelFactory

    @Inject
    lateinit var alarmsAdapter: AlarmsAdapter
    lateinit var alarmsViewModel: AlarmsViewModel


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvBottom.setupWithNavController(
            navController
        )
        notesViewModel = ViewModelProvider(this, notesViewModelFactory)[NotesViewModel::class.java]

        voiceNotesViewModel = ViewModelProvider(this, voiceNotesViewModelFactory)[VoiceNotesViewModel::class.java]

        eventsViewModel = ViewModelProvider(this, eventsViewModelFactory)[EventsViewModel::class.java]

        comeEventsViewModel = ViewModelProvider(this, eventsViewModelFactory)[EventsViewModel::class.java]

        alarmsViewModel =
            ViewModelProvider(this, alarmsViewModelFactory)[AlarmsViewModel::class.java]
        alarmsAdapter.alarmsViewModel = alarmsViewModel
    }
}