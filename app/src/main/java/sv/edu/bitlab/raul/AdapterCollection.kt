package sv.edu.bitlab.raul

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.extensions.LayoutContainer

class AdapterCollection(options: FirestoreRecyclerOptions<Account>) :
    FirestoreRecyclerAdapter<Account, AdapterCollection.CollectionHolder>(options) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent,false)

        return CollectionHolder(view)
    }

    override fun onBindViewHolder(holder: CollectionHolder, position: Int, model: Account) {
        holder.apply {
            namens.text = model.accountName
            email.text = model.accountEmail
            phone.text = model.accountPhone
            foundby.text = model.accountFoundOutBy
            imageGlide(image,model.accountImage)

        }
    }

    inner class CollectionHolder( override val containerView: View) : RecyclerView.ViewHolder(containerView),LayoutContainer {
        var namens = containerView.findViewById<TextView>(R.id.text_nombre)
        var email=containerView.findViewById<TextView>(R.id.text_set_email)
        var phone =containerView.findViewById<TextView>(R.id.text_set_numero_tel)
        var foundby = containerView.findViewById<TextView>(R.id.text_set_enterado)
        var image = containerView.findViewById<ImageView>(R.id.image_1)
    }

    fun imageGlide(imageView: ImageView, url: String?){
        Glide.with(imageView).load(url).into(imageView)
    }
}