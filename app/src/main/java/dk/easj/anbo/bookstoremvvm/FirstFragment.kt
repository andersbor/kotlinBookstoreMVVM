package dk.easj.anbo.bookstoremvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dk.easj.anbo.bookstoremvvm.databinding.FragmentFirstBinding
import dk.easj.anbo.bookstoremvvm.models.BooksAdapter
import dk.easj.anbo.bookstoremvvm.models.BooksViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val booksViewModel: BooksViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        booksViewModel.booksLiveData.observe(viewLifecycleOwner) { books ->
            //Log.d("APPLE", "observer $books")
            binding.progressbar.visibility = View.GONE
            binding.recyclerView.visibility = if (books == null) View.GONE else View.VISIBLE
            if (books != null) {
                val adapter = BooksAdapter(books) { position ->
                    val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(position)
                    findNavController().navigate(action /*R.id.action_FirstFragment_to_SecondFragment*/)
                }
                binding.recyclerView.layoutManager = LinearLayoutManager(activity)
                binding.recyclerView.adapter = adapter
            }
        }

        booksViewModel.errorMessageLiveData.observe(viewLifecycleOwner) { errorMessage ->
            binding.textviewMessage.text = errorMessage
        }

        booksViewModel.reload()

        binding.swiperefresh.setOnRefreshListener {
            booksViewModel.reload()
            binding.swiperefresh.isRefreshing = false
        }

        /* binding.buttonFirst.setOnClickListener {
             findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
         }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}