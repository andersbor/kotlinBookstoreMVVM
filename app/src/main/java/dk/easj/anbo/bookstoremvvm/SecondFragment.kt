package dk.easj.anbo.bookstoremvvm

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dk.easj.anbo.bookstoremvvm.databinding.FragmentSecondBinding
import dk.easj.anbo.bookstoremvvm.models.Book
import dk.easj.anbo.bookstoremvvm.models.BooksViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val booksViewModel: BooksViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = requireArguments()
        val secondFragmentArgs: SecondFragmentArgs = SecondFragmentArgs.fromBundle(bundle)
        val position = secondFragmentArgs.position
        val book = booksViewModel[position]
        if (book == null) {
            binding.textviewMessage.text = "No such book!"
            return
        }
        binding.editTextAuthor.setText(book.author)
        binding.editTextPublisher.setText(book.publisher)
        binding.editTextTitle.setText(book.title)
        binding.editTextPrice.setText(book.price.toString())

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.buttonDelete.setOnClickListener {
            booksViewModel.delete(book.id)
        }

        binding.buttonUpdate.setOnClickListener {
            val title = binding.editTextTitle.text.toString().trim()
            val publisher = binding.editTextPublisher.text.toString().trim()
            val author = binding.editTextAuthor.text.toString().trim()
            val price = binding.editTextPrice.text.toString().trim().toDouble()
            val updatedBook = Book(book.id, author, title, publisher, price)
            Log.d("APPLE", "update $updatedBook")
            booksViewModel.update(updatedBook)
        }


        /*binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}