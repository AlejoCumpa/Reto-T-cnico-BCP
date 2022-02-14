package cumpa.alejandro.retotcnicobcp.ui.adapter

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import cumpa.alejandro.retotcnicobcp.R

@BindingAdapter("imageUrl")
fun setImageUrl(imgView: ImageView, imgUrl: String?){

    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    //.placeholder(R.drawable.loading_animation)
                    //.error(R.drawable.ic_broken_image)
                )
            .into(imgView)
    }
}