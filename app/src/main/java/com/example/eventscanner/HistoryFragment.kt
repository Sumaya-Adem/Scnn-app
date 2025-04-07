package com.example.eventscanner

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventscanner.Adapter.EventAdapter
import com.example.eventscanner.Model.EventsModel
import com.google.android.material.bottomsheet.BottomSheetDialog


class HistoryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)



    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
//
        val menuDots: ImageView = view.findViewById(R.id.menu_dots)
//
        // Set click listener for the menu dots
        menuDots.setOnClickListener {
            showToolbarMenu(it) // Pass the view to anchor the menu
        }

        setupEventList(view) // ✅ Call setupEventList to initialize RecyclerView
    }

    private fun setupEventList(view: View) {
        val eventView: RecyclerView = view.findViewById(R.id.eventView)
        eventView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        // Example event data
        val eventList = mutableListOf(
            EventsModel(
                image = R.drawable.moshing,
                title = "Tribute To Didi Kempot",
                date = "Wed, Jan 18",
                time = "18:00 - 22:00 PM",
                location = "Denny Caknan"
            ),
            EventsModel(
                image = R.drawable.kempot,
                title = "Tribute To Didi Kempot",
                date = "Wed, Jan 18",
                time = "18:00 - 22:00 PM",
                location = "Denny Caknan"
            ),
            EventsModel(
                image = R.drawable.moshing,
                title = "Tribute To Didi Kempot",
                date = "Wed, Jan 18",
                time = "18:00 - 22:00 PM",
                location = "Denny Caknan"
            ),
            EventsModel(
                image = R.drawable.kempot,
                title = "Tribute To Didi Kempot",
                date = "Wed, Jan 18",
                time = "18:00 - 22:00 PM",
                location = "Denny Caknan"
            ),
            EventsModel(
                image = R.drawable.moshing,
                title = "Tribute To Didi Kempot",
                date = "Wed, Jan 18",
                time = "18:00 - 22:00 PM",
                location = "Denny Caknan"
            ),
        )

//        eventView.adapter = EventAdapter(eventList)
        val adapter = EventAdapter(eventList)
        eventView.adapter = adapter

        // Enable swipe-to-delete
        setupSwipeToDelete(eventView, adapter)

    }
    private fun setupSwipeToDelete(eventView: RecyclerView, adapter: EventAdapter) {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {
            private val backgroundPaint = Paint().apply {
                color = Color.RED // Background color
            }
            private val icon: Drawable? = context?.let { ContextCompat.getDrawable(it, R.drawable.delete_white) }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false // No drag-and-drop required
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                showDeleteConfirmationDialog(position, adapter, viewHolder) // Remove item on swipe

            }
            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                val itemHeight = itemView.bottom - itemView.top
                val iconSize = icon?.intrinsicHeight ?: 0
                if (dX < 0) { // Swipe left
                    val backgroundRight = itemView.right.toFloat()
                    val backgroundLeft = itemView.right.toFloat()+ dX
                    val backgroundTop = itemView.top.toFloat()
                    val backgroundBottom = itemView.bottom.toFloat()

                    // Draw red background
                    c.drawRect(backgroundLeft, backgroundTop, backgroundRight, backgroundBottom, backgroundPaint)

                    // Draw delete icon
                    val iconMargin = (itemHeight - iconSize) / 2
                    icon?.setBounds(
                        itemView.right - iconMargin - iconSize,
                        itemView.top + iconMargin,
                        itemView.right - iconMargin,
                        itemView.bottom - iconMargin
                    )
                    icon?.draw(c)
                }

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(eventView)
    }

    private fun showDeleteConfirmationDialog(position: Int, adapter: EventAdapter, viewHolder: RecyclerView.ViewHolder) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.delete_ticket_scan, null)

        val btnCancel = view.findViewById<Button>(R.id.cancel)
        val btnDelete = view.findViewById<Button>(R.id.yes_confirm)

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()

        btnCancel.setOnClickListener {
            bottomSheetDialog.dismiss()
            adapter.notifyItemChanged(position) // Restore item if canceled
        }

        btnDelete.setOnClickListener {
            adapter.removeItem(position) // Delete item if confirmed
            bottomSheetDialog.dismiss()
        }
    }
    private fun showToolbarMenu(anchorView: View) {
        val popup = PopupMenu(requireContext(), anchorView)
        popup.menuInflater.inflate(R.menu.toolbar_menu, popup.menu)

        // Handle menu item clicks
        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.delete_all -> {
                    Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
                    true
                } else -> false
            }
        }
        popup.show()
    }


}
