package dk.easj.anbo.bookstoremvvm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dk.easj.anbo.bookstoremvvm.databinding.FragmentSecondBinding
import dk.easj.anbo.bookstoremvvm.models.Book
import dk.easj.anbo.bookstoremvvm.models.BooksViewModel

class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
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
        binding.editTextTitle.setText(book.title)
        binding.editTextPrice.setText(book.price.toString())

        binding.buttonBack.setOnClickListener {
            // findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            // https://stackoverflow.com/questions/60003039/why-android-navigation-component-screen-not-go-back-to-previous-fragment-but-a-m
            findNavController().popBackStack()
        }

        binding.buttonDelete.setOnClickListener {
            booksViewModel.delete(book.id)
            findNavController().popBackStack()
        }

        binding.buttonUpdate.setOnClickListener {
            val title = binding.editTextTitle.text.toString().trim()
            //val publisher = binding.editTextPublisher.text.toString().trim()
            //val author = binding.editTextAuthor.text.toString().trim()
            val price = binding.editTextPrice.text.toString().trim().toDouble()
            val updatedBook = Book(book.id, title,  price)
            Log.d("APPLE", "update $updatedBook")
            booksViewModel.update(updatedBook)
            findNavController().popBackStack()
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