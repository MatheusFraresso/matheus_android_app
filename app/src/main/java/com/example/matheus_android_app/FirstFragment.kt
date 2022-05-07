package com.example.matheus_android_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.matheus_android_app.databinding.FragmentFirstBinding
import com.example.matheus_android_app.model.Activity
import com.example.matheus_android_app.repository.Repository

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    fun getActivityData(act: com.example.matheus_android_app.model.Activity): String {
        return "Activity:" + act.activity + "\n" +
                "Type:" + act.type + "\n" +
                "Participants:" + act.participants + "\n" +
                "Price:" + act.price + "\n" +
                "link:" + act.link + "\n" +
                "key:" + act.key + "\n" +
                "accessbility:" + act.accessibility + "\n";
    }


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val repository = Repository()
    val viewModelFactory = MainViewModelFactory(repository);

    private lateinit var viewModel: MainViewModel;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var act =
            Activity(
                "",
                "",
                0,
                0.0,
                "",
                "",
                0.0
            );

        val activities: MutableSet<Activity> = mutableSetOf();

        binding.buttonFirst.setOnClickListener {

            val type: String = binding.editTextTextPersonName4.text.toString();

            if (type.isBlank()) {
                viewModel.getActivity()

            } else {
                viewModel.getActivityByType(type)

            }

            viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
                act = com.example.matheus_android_app.model.Activity(
                    response.activity,
                    response.type,
                    response.participants,
                    response.price,
                    response.link,
                    response.key,
                    response.accessibility
                );
                binding.textviewFirst.setText(getActivityData(act));
            })


        }

        binding.button.setOnClickListener {
            if (act.activity.isNotBlank()) {
                binding.textView2.setText("");
                activities.add(act);
                val acts:String ="";
                for (activity in activities) {
                    binding.textView2.append(activity.activity+"\n");

                }


            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}