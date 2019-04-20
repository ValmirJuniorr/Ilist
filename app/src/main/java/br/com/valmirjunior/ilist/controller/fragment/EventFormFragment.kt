package br.com.valmirjunior.ilist.controller.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.valmirjunior.ilist.R
import br.com.valmirjunior.ilist.model.Event
import br.com.valmirjunior.ilist.model.StatusEventEnum
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_form_event.*
import java.io.InvalidClassException
import java.util.*

class EventFormFragment : BottomSheetDialogFragment() {

    private lateinit var statusEventEnumActual: StatusEventEnum
    private var event: Event? = null
    private lateinit var managerFormEvent: ManagerFormEvent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        event = arguments?.getSerializable(ARG_EVENT) as Event?
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ManagerFormEvent) {
            managerFormEvent = context
        } else {
            throw InvalidClassException("The context must implements ManagerFormEvent")
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_form_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        event?.let {
            til_title.editText?.setText(it.title)
            til_description.editText?.setText(it.description)
            StatusEventEnum.getConstByValue(it.status)?.let { statusEventEnum ->
                updateStatus(statusEventEnum)
            }
        } ?: run {
            updateStatus( StatusEventEnum.first())
        }
        tv_status.setOnClickListener {
            updateStatus(StatusEventEnum.getNextStatus(statusEventEnumActual))
        }
        fab_save_event.setOnClickListener { saveEvent() }
        iv_close.setOnClickListener { managerFormEvent.closeDialog() }
    }

    private fun updateStatus(status: StatusEventEnum) {
        statusEventEnumActual = status
        tv_status.text = String.format(getString(R.string.event_status_formatted), status.label)
        tv_status.setCompoundDrawablesWithIntrinsicBounds(null, null, status.icon, null)
    }

    private fun saveEvent() {
        val newTitle = til_title.editText?.text.toString()
        val newDescription = til_description.editText?.text.toString()
        val status = statusEventEnumActual.value
        val now = Calendar.getInstance().time
        event?.let {
            it.title = newTitle
            it.description = newDescription
            it.updatedAt = now
            it.status = status
        } ?: run {
            event = Event(null, null, newTitle, newDescription, status, now, now)
        }
        managerFormEvent.save(event!!)
    }


    interface ManagerFormEvent {
        fun save(event: Event)

        fun closeDialog()
    }

    companion object {
        private const val ARG_EVENT = "ARG_EVENT"
        fun newInstance(event: Event?): EventFormFragment {
            return EventFormFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_EVENT, event)
                }
            }
        }
    }


}