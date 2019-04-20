package br.com.valmirjunior.ilist.controller.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import br.com.valmirjunior.ilist.R
import br.com.valmirjunior.ilist.controller.adapter.EventAdapter
import br.com.valmirjunior.ilist.controller.adapter.EventsPagerAdapter
import br.com.valmirjunior.ilist.controller.fragment.EventFormFragment
import br.com.valmirjunior.ilist.model.Event
import br.com.valmirjunior.ilist.model.StatusEventEnum
import br.com.valmirjunior.ilist.repository.EventRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        EventFormFragment.ManagerFormEvent,
        EventAdapter.ManagerEvents {

    private var mEventsPagerAdapter: EventsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        mEventsPagerAdapter = EventsPagerAdapter(supportFragmentManager)
        vp_events.adapter = mEventsPagerAdapter
        tabs.setupWithViewPager(vp_events)
        fab_add_event.setOnClickListener { openDialogFormEvent(null) }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun save(event: Event) {
        EventRepository.create(event)
        closeDialog()
    }

    override fun closeDialog() {
        supportFragmentManager.findFragmentByTag(TAG_DIALOG_REGISTER)?.let {
            (it as DialogFragment?)?.dismiss()
        }
    }

    private fun openDialogFormEvent(event: Event?) {
        val dialog = EventFormFragment.newInstance(event)
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, TAG_DIALOG_REGISTER)
    }

    override fun edit(event: Event) {
        openDialogFormEvent(event)
    }

    override fun updateStatus(event: Event) {
        val currentStatus = StatusEventEnum.getConstByValue(event.status)
        currentStatus?.let {
            event.status = StatusEventEnum.getNextStatus(currentStatus).value
            EventRepository.update(event)
        }
    }

    companion object {
        private const val TAG_DIALOG_REGISTER = "TAG_DIALOG_REGISTER"
    }

}
