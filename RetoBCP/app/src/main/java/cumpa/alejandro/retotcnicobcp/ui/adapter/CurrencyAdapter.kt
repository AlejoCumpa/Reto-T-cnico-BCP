package cumpa.alejandro.retotcnicobcp.ui.adapter

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import cumpa.alejandro.retotcnicobcp.R
import cumpa.alejandro.retotcnicobcp.databinding.ItemCurrencyBinding
import cumpa.alejandro.retotcnicobcp.domain.model.Currency
import javax.inject.Inject

class CurrencyAdapter( val listener: CurrenciesListener) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    var currencies = mutableListOf<Currency>()
    fun setCurrenciesList(currencies: List<Currency>) {
        this.currencies = currencies.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount() = currencies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCurrencyBinding.inflate(inflater, parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.binding.currency = currencies[position]
        holder.binding.root.setOnClickListener(View.OnClickListener {
            listener.onClickItem(currencies[position])
        })
    }

    inner class CurrencyViewHolder(val binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {}

    interface CurrenciesListener {
        fun onClickItem(item:Currency)
    }
}