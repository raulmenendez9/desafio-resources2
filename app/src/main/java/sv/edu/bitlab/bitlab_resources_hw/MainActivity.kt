package sv.edu.bitlab.bitlab_resources_hw

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FormularioFragment.OnFragmentInteractionListener {
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

    override fun loadsucces(containerID: Resp_mensajeFragment){
        val success = supportFragmentManager.beginTransaction()
        success.replace(R.id.fragment2,containerID).addToBackStack("atras")
        success.commit()
    }

}
