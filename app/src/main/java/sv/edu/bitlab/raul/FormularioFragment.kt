package sv.edu.bitlab.raul

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_formulario.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FormularioFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FormularioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormularioFragment : Fragment(), AdapterView.OnItemSelectedListener {


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var spintext : String?=null
    private var listener: OnFragmentInteractionListener? = null
    var btn: Button? = null
    var accountName: EditText? = null
    var accountEmail: EditText? = null
    var accountPhone: EditText?=null
    var accountFoundOutBy: Spinner?=null
    //handler
    private lateinit var mHandler: Handler
    var i:Int=1
    var constraint_success: View?=null
    val mRunnable = object :Runnable{
        override fun run() {
            mHandler!!.postDelayed(this,5000)
            success()
        }
    }
    /////////////////////////////
    var item: TextView?=null
    val account: Account?=null
    val accountss : MutableList<String>?=null
    //accediendo a la instancia de firestore
    val db= FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_formulario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn = view.findViewById<Button>(R.id.button)
        val text_colections = view.findViewById<TextView>(R.id.textView_colecction)
        accountName = view.findViewById(R.id.input_name)
        accountEmail = view.findViewById(R.id.input_correo)
        accountPhone=view.findViewById(R.id.input_telphone)
        accountFoundOutBy=view.findViewById(R.id.spinner_find_out)
        ArrayAdapter.createFromResource(activity!!.applicationContext,R.array.spinner,android.R.layout.simple_spinner_item).also {
            adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            accountFoundOutBy!!.adapter = adapter
        }
        constraint_success = view.findViewById<View>(R.id.constraint_success)
        mHandler = Handler(Looper.getMainLooper())

        btn.setOnClickListener {
            mHandler!!.post(mRunnable)
        }
        constraint_success!!.setOnClickListener{ showSuccess(false)
        }
        text_colections.setOnClickListener{
            next()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        //item?.text = p0?.getItemAtPosition(p2).toString()
         spintext= accountFoundOutBy!!.getItemAtPosition(p2).toString()
        Toast.makeText(activity, "$spintext", Toast.LENGTH_LONG).show()

    }
    fun button(){

        if (accountName!!.text.toString().equals("") && accountEmail!!.text.toString().equals("")){
            Toast.makeText(activity, "Debe ingresar nombre y correo", Toast.LENGTH_LONG).show()

        }else{
            accountFoundOutBy?.onItemSelectedListener=this
            //Toast.makeText(activity, "$accountPhone", Toast.LENGTH_LONG).show()


            var name=accountName!!.text.toString()
            var email = accountEmail!!.text.toString()
            var phone = accountPhone!!.text.toString()

            val accounts: Account= Account("$name","$email", "$phone", "$spintext", "image")
            db.collection("accounts")
                .add(accounts)
                .addOnSuccessListener{documentReference -> Log.d("EnvioData", "$accounts")
                    Toast.makeText(activity, "Enviado con exito", Toast.LENGTH_LONG).show()}
                .addOnFailureListener { e -> Log.w("Error", "$e") }
            listener!!.loadsucces(CollectionViewFragment())

        }
    }
    fun next(){
        listener!!.loadsucces(CollectionViewFragment())

    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
        fun loadsucces(containerID: CollectionViewFragment)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FormularioFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FormularioFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun success(){
        when(i){
            1->{
                showSuccess(true)
                i++
            }
            2->{
                button()
                mHandler!!.removeCallbacks(mRunnable)
                i=1
            }
        }


    }

    fun showSuccess(show: Boolean){
        val visibility = if (show) View.VISIBLE
        else View.GONE
        constraint_success!!.visibility=visibility
    }
}
