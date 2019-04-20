package br.com.valmirjunior.ilist.controller.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.valmirjunior.ilist.R
import br.com.valmirjunior.ilist.controller.adapter.EventAdapter
import br.com.valmirjunior.ilist.model.StatusEventEnum
import br.com.valmirjunior.ilist.repository.EventRepository
import kotlinx.android.synthetic.main.fragment_list_event.*

class EventListFragment : Fragment() {

    private var managerEvents: EventAdapter.ManagerEvents? = null
    private var statusEventEnum: StatusEventEnum? = null
    private lateinit var eventAdapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusEventEnum = arguments?.getSerializable(ARG_STATUS) as StatusEventEnum?
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is EventAdapter.ManagerEvents){
            managerEvents = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        eventAdapter = EventAdapter(null,managerEvents)
        with(rv_events) {
            this.layoutManager = layoutManager
            adapter = eventAdapter
//            addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        }
        statusEventEnum?.let {
            EventRepository.loadAllByStatus(it).observe(this, Observer { events ->
                eventAdapter.events = events
                eventAdapter.notifyDataSetChanged()
            })
        }
    }

    companion object {
        private const val ARG_STATUS = "ARG_STATUS"

        fun newInstance(statusEventEnum: StatusEventEnum): EventListFragment {
            return EventListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_STATUS, statusEventEnum)
                }
            }
        }
    }


}