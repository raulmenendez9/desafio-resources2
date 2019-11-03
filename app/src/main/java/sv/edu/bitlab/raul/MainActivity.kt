package sv.edu.bitlab.raul

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import kotlinx.android.synthetic.main.fragment_formulario.*

class MainActivity : AppCompatActivity(), FormularioFragment.OnFragmentInteractionListener, CollectionViewFragment.OnFragmentInteractionListener {

    override fun atras(fr: FormularioFragment) {
        val formmu = supportFragmentManager.beginTransaction()
        formmu.replace(R.id.fragment, fr).addToBackStack(null)
        formmu.commit()
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)
        loadbitlab(FormularioFragment())
    }

    private fun loadbitlab(fr: FormularioFragment){
        val formmu = supportFragmentManager.beginTransaction()
        formmu.replace(R.id.fragment, fr).addToBackStack(null)
        formmu.commit()
    }

    override fun loadsucces(containerID: CollectionViewFragment){
        val success = supportFragmentManager.beginTransaction()
        success.replace(R.id.fragment2,containerID).addToBackStack("atras")
        success.commit()
    }
    //prueba de branch
}
