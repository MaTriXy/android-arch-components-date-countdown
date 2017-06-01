package za.co.riggaroo.datecountdown.ui.event.add

import android.app.DatePickerDialog
import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView

import org.threeten.bp.LocalDateTime

import za.co.riggaroo.datecountdown.CountdownApplication
import za.co.riggaroo.datecountdown.R
import za.co.riggaroo.datecountdown.injection.CountdownFactory

class AddEventFragment : LifecycleFragment(), DatePickerDialog.OnDateSetListener {


    private var editTextTitle: EditText? = null
    private var editTextDescription: EditText? = null
    private var buttonAddEvent: Button? = null
    private var buttonSetDate: Button? = null
    private var textViewCurrentDate: TextView? = null
    private var addEventViewModel: AddEventViewModel? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_add_event, container, false)

        setupViews(view)
        setupClickListeners()
        setupViewModel()
        return view
    }

    private fun setupViewModel() {
        val countdownApplication = activity.application as CountdownApplication
        addEventViewModel = ViewModelProviders.of(this, CountdownFactory(countdownApplication))
                .get(AddEventViewModel::class.java)
        editTextTitle!!.setText(addEventViewModel!!.getEventName().value)
        editTextDescription!!.setText(addEventViewModel!!.getEventDescription().value)
        textViewCurrentDate!!.text = addEventViewModel!!.eventDateTime.value!!.toString()
    }

    private fun setupClickListeners() {
        editTextTitle!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                addEventViewModel!!.setEventName(s.toString())
            }
        })
        editTextDescription!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                addEventViewModel!!.setEventDescription(s.toString())
            }
        })
        buttonAddEvent!!.setOnClickListener { v ->
            addEventViewModel!!.addEvent()
            activity.finish()
        }
        buttonSetDate!!.setOnClickListener { v ->
            val localDateTime = addEventViewModel!!.eventDateTime.value
            val datePickerDialog = DatePickerDialog(
                    context, this, localDateTime!!.year, localDateTime.monthValue - 1, localDateTime.dayOfMonth)

            datePickerDialog.show()
        }
    }

    private fun setupViews(view: View) {
        buttonAddEvent = view.findViewById(R.id.button_add) as Button
        editTextTitle = view.findViewById(R.id.edit_text_title) as EditText
        editTextDescription = view.findViewById(R.id.edit_text_description) as EditText
        buttonSetDate = view.findViewById(R.id.button_set_date) as Button
        textViewCurrentDate = view.findViewById(R.id.text_view_date_set) as TextView
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        addEventViewModel!!.setEventDateTime(LocalDateTime.of(year, month + 1, dayOfMonth, 0, 0))
        textViewCurrentDate!!.text = addEventViewModel!!.eventDateTime.value!!.toString()
    }


}
