package br.com.valmirjunior.ilist.controller.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.com.valmirjunior.ilist.controller.fragment.EventListFragment
import br.com.valmirjunior.ilist.model.StatusEventEnum

class EventsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val titles = ArrayList<String>()

    private val fragments = ArrayList<Fragment>()

    init {
        StatusEventEnum.values().forEach {
            titles.add(it.label)
            fragments.add(EventListFragment.newInstance(it))
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return titles.size
    }
}