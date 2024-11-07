<?xml version="1.0" encoding="utf-8"?>
<androidx.cardview.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
   xmlns:app="http://schemas.android.com/apk/res-auto"
   xmlns:tools="http://schemas.android.com/tools"
   android:layout_width="match_parent"
   android:layout_height="wrap_content"
   android:layout_margin="8dp"
   app:cardBackgroundColor="@color/white">

   <LinearLayout
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:orientation="horizontal"
       android:padding="16dp">

       <TextView
           android:id="@+id/textViewStatus"
           android:layout_width="0dp"
           android:layout_height="wrap_content"
           android:layout_weight="1"
           tools:text="OFF" />

       <Switch
           android:id="@+id/switchStatus"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content" />
   </LinearLayout>
</androidx.cardview.widget.CardView>

class SwitchAdapter(private var items: List<String>) : RecyclerView.Adapter<SwitchAdapter.ViewHolder>() {
   class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       val textView: TextView = itemView.findViewById(R.id.textViewStatus)
       val switch: Switch = itemView.findViewById(R.id.switchStatus)
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_switch, parent, false)
       return ViewHolder(view)
   }

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.textView.text = "OFF"
       holder.switch.setOnCheckedChangeListener { _, isChecked ->
           if (isChecked) {
               holder.textView.text = "ON"
               holder.itemView.setBackgroundColor(Color.GREEN)
           } else {
               holder.textView.text = "OFF"
               holder.itemView.setBackgroundColor(Color.RED)
           }
       }
   }

   override fun getItemCount(): Int = items.size
}

recyclerView.layoutManager = LinearLayoutManager(this)
recyclerView.adapter = SwitchAdapter(listOf("Item 1", "Item 2", "Item 3"))
