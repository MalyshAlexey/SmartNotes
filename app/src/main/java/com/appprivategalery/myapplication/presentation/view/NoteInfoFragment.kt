package com.appprivategalery.myapplication.presentation.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent.ACTION_UP
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import asm.asmtunis.com.mhcolorpicker.dialog.MHColorsDialog
import com.appprivategalery.myapplication.MainActivity
import com.appprivategalery.myapplication.R
import com.appprivategalery.myapplication.data.model.Note
import com.appprivategalery.myapplication.databinding.FragmentNoteInfoBinding
import com.appprivategalery.myapplication.presentation.viewmodel.note.NotesViewModel


class NoteInfoFragment : Fragment(), OnClickListener {
    private lateinit var fragmentNoteInfoBinding: FragmentNoteInfoBinding
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var mNote: Note


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentNoteInfoBinding = FragmentNoteInfoBinding.bind(view)
        val args: NoteInfoFragmentArgs by navArgs()
        mNote = args.selectedNote

        fragmentNoteInfoBinding.nfTvTitle.setText(mNote.title)
        fragmentNoteInfoBinding.nfTvTitle.setText(mNote.content)

        initLayouts()

        notesViewModel = (activity as MainActivity).notesViewModel
    }


    private fun initLayouts() {
        fragmentNoteInfoBinding.nfTvTitle.setOnClickListener(this)
    }

    override fun onDestroyView() {
        notesViewModel.updateNote(
            Note(
                mNote.id,
                fragmentNoteInfoBinding.nfTvTitle.text.toString(),
                fragmentNoteInfoBinding.nfTvTitle.text.toString()
            )
        )
        super.onDestroyView()
    }

    private val arrayEditText = ArrayList<View>()
    private val arrayIdEditText = ArrayList<Int>()
    private val arrayColorSetEditText = ArrayList<EditText>()
    private var editId = 3
    private var buttonId = 10000
    override fun onClick(p0: View?) {
        //  Log.e("==das=>",p0>.rootView.id)


        when (p0?.id) {
            R.id.nf_tv_title -> {
                createNewEditText()
                arrayIdEditText.add(R.id.nf_tv_title)
            }
            else -> findPreviousNote(p0)
        }
    }

    private fun createNewEditText() {
        val layout =
            context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val note: View = layout.inflate(R.layout.edit_note_list_item, null)
        note.findViewById<EditText>(R.id.editText).setOnClickListener(this)

        val editText = editId
        note.findViewById<ImageButton>(R.id.color).id = buttonId
        note.findViewById<ImageButton>(buttonId).setOnClickListener {
            colorDialog(note.findViewById<EditText>(editText))
        }

        buttonId++

        note.findViewById<EditText>(R.id.editText).id = editId
        note.findViewById<EditText>(editId)
            .setOnEditorActionListener { v, actionId, event ->
                if (event.keyCode  == 66 && event.action == ACTION_UP) {
                    findPreviousNote(note.findViewById<EditText>(editText))
                }
                true
            }



// insert into main view
        fragmentNoteInfoBinding.llMain.addView(
            note,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )

        Log.e("===>", note.id.toString())


        arrayEditText.add(note.findViewById<EditText>(editId))
        arrayIdEditText.add(editId)
        editId++

    }


    private fun findPreviousNote(p0: View?) {
        for (editText in arrayEditText) {
            if (editText.id == p0?.id) {
                var nextEditText = editText.id
                if (!arrayIdEditText.contains(++nextEditText)) {
                    createNewEditText()
                }
                break
            }
        }
    }


    private fun colorDialog(editText: EditText) {
        MHColorsDialog(requireContext())
            .setColorListener { color, _ ->
                if (!arrayColorSetEditText.contains(editText)) {
                    editText.setTextColor(color)
                    arrayColorSetEditText.add(editText)
                }
            }
            .show()

        Log.e("FFF", editText.id.toString())
    }

}