package br.edu.ifsp.dmo.trabalhofinal.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.dmo.trabalhofinal.data.model.Plant
import br.edu.ifsp.dmo.trabalhofinal.data.model.PlantUser
import br.edu.ifsp.dmo.trabalhofinal.databinding.ItemStockBinding

class PlantUserAdapter : ListAdapter<PlantUser, PlantUserAdapter.PlantUserViewHolder>(DiffCallback()) {

    private var plantMap: Map<Long, Plant> = emptyMap()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantUserViewHolder {
        val binding = ItemStockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlantUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantUserViewHolder, position: Int) {
        val plantUser = getItem(position)
        val plant = plantMap[plantUser.idPlant]
        holder.bind(plantUser, plant)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updatePlantMap(newMap: Map<Long, Plant>) {
        plantMap = newMap
        notifyDataSetChanged()
    }

    class PlantUserViewHolder(private val binding: ItemStockBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(plantUser: PlantUser, plant: Plant?) {
            binding.textPlantName.text = plant?.name ?: "Desconhecido"
            binding.textSpecies.text = plant?.species ?: "Desconhecido"
            binding.textSize.text = (plant?.size ?: "Desconhecido").toString()
            binding.textQuantity.text = "Quantidade: ${plantUser.quantity}"
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<PlantUser>() {
        override fun areItemsTheSame(oldItem: PlantUser, newItem: PlantUser): Boolean {
            return oldItem.idPlant == newItem.idPlant && oldItem.idUser == newItem.idUser
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: PlantUser, newItem: PlantUser): Boolean {
            return oldItem == newItem
        }
    }
}
