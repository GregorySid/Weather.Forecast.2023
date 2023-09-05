package com.example.hi23.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hi23.MViewModel
import com.example.hi23.adapters.WeatherAdapter
import com.example.hi23.adapters.WeatherModel
import com.example.hi23.databinding.FragmentHoursBinding
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList


class HoursFragment : Fragment() {
    private lateinit var binding: FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter
    private val model: MViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHoursBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        model.liveDataCurrent.observe(viewLifecycleOwner){
            adapter.submitList(getHoursList(it))
        }
    }

    private fun init() = with(binding){
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter(null)
        rcView.adapter = adapter
    }

    private fun getHoursList(wItem: WeatherModel): List<WeatherModel>{
        val horseArray = JSONArray(wItem.hours)
        val list = ArrayList<WeatherModel>()
        for(i in 0 until horseArray.length()) {
            val item = WeatherModel(
                wItem.city,
                (horseArray[i] as JSONObject).getString("time"),
                (horseArray[i] as JSONObject).getJSONObject("condition").getString("text"),
                (horseArray[i] as JSONObject).getString("temp_c"),
                "",
                "",
                (horseArray[i] as JSONObject).getJSONObject("condition").getString("icon"),
                ""
                )
            list.add(item)
        }
        return list
    }


    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}