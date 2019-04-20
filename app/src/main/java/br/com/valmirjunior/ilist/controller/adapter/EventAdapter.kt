package br.com.valmirjunior.ilist.controller.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.valmirjunior.ilist.R
import br.com.valmirjunior.ilist.model.Event
import br.com.valmirjunior.ilist.model.StatusEventEnum
import br.com.valmirjunior.ilist.util.DateUtil
import kotlinx.android.synthetic.main.item_event.view.*

class EventAdapter(var events: List<Event>?,
                   var managerEvents: ManagerEvents? )
    : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_event, parent, false))
    }

    override fun getItemCount(): Int {
        return events?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = events!![position]
        val context = holder.itemView.context
        val status = StatusEventEnum.getConstByValue(event.status)
        with(holder){
            tvTitle.text = event.title
            tvUpdatedAt.text = String.format(context.getString(R.string.fmt_last_updated_at),
                    DateUtil.sdfDateTimeHuman.format(event.updatedAt))
            status?.let {
                tvTitle.setCompoundDrawablesWithIntrinsicBounds(status.icon,  null, null, null)
            }
            managerEvents?.let { manager ->
                tvTitle.setOnClickListener { manager.updateStatus(event) }
                itemView.setOnClickListener { manager.edit(event) }
            }
        }
    }

    interface ManagerEvents{
        fun edit(event: Event)
        fun updateStatus(event: Event)
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tv_title_modal
        val tvUpdatedAt: TextView = view.tv_updated_at
    }
}