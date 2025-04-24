

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.Data
import com.squareup.picasso.Picasso
import com.example.musicapp.R

class MyAdapter(
    val context: Activity,
    val dataList: List<Data>,
    val onPlay: (String) -> Unit,
    val onPause: () -> Unit
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val currentData = dataList[position]

        holder.title.text = currentData.title

        // Load album cover image using Picasso
        Picasso.get()
            .load(currentData.album.cover) // URL from the Data model
            .into(holder.image) // Set into ImageView

        // Handle play and pause actions
        holder.play.setOnClickListener {
            onPlay(currentData.preview) // Call the onPlay function with preview URL
        }

        holder.pause.setOnClickListener {
            onPause() // Call the onPause function to stop the music
        }
    }

    override fun getItemCount(): Int = dataList.size

    // ViewHolder to hold individual items
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.MusicImage)
        val title: TextView = itemView.findViewById(R.id.MusicTitle)
        val play: ImageButton = itemView.findViewById(R.id.btnPlay)
        val pause: ImageButton = itemView.findViewById(R.id.btnPause)
    }
}
