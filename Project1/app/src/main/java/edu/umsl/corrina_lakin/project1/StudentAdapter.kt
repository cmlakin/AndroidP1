package edu.umsl.corrina_lakin.project1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.student_list.view.*


class StudentAdapter(val context: Context, private val students: List<Student>, private val refreshListener: RefreshListener) : RecyclerView.Adapter<StudentAdapter.MyViewHolder> (){

    // will create list of view holder items.
    // links student adapter with recylcerview - helps to bind data to the UI
    // or attach models to the recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //this will return the layout as a view
        val view = LayoutInflater.from(context).inflate(R.layout.student_list, parent, false)
        return MyViewHolder(view)
    }

    // will return length of List<Student>
    override fun getItemCount(): Int {
        return students.size
    }

    // will bind all the data to the views that are being created, this will
    // add our student names to our list
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val student = students[position]
        // sets which student at which index
        holder.setData1(student, position)
        holder.setData2(student, position)

    }

    // the itemview is each individual student listed.
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val radioGroup: RadioGroup = itemView.findViewById(R.id.radioGroup)

        var currentStudent: Student? = null
        var currentPosition: Int = 0


        fun setData1(student: Student?, pos: Int) {
            // this represents the individual itemviews of each student
            // .text is what sets the text for us.
            itemView.txvTitle.text = student!!.title

            this.currentStudent = student
            this.currentPosition = pos

            when (student.attendanceTracker) {
                AttendanceTracker.Present -> radioGroup.check(R.id.radioPresent)
                AttendanceTracker.Late -> radioGroup.check(R.id.radioLate)
                AttendanceTracker.Absent -> radioGroup.check(R.id.radioAbsent)
                AttendanceTracker.Unknown -> radioGroup.check(R.id.radioUnknown)
            }

            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.radioPresent -> Student.Roster1.students[pos].attendanceTracker =
                        AttendanceTracker.Present
                    R.id.radioLate -> Student.Roster1.students[pos].attendanceTracker =
                        AttendanceTracker.Late
                    R.id.radioAbsent -> Student.Roster1.students[pos].attendanceTracker =
                        AttendanceTracker.Absent
                    R.id.radioUnknown -> Student.Roster1.students[pos].attendanceTracker =
                        AttendanceTracker.Unknown
                }

                when (checkedId) {
                    R.id.radioPresent -> Student.Roster2.students[pos].attendanceTracker =
                        AttendanceTracker.Present
                    R.id.radioLate -> Student.Roster2.students[pos].attendanceTracker =
                        AttendanceTracker.Late
                    R.id.radioAbsent -> Student.Roster2.students[pos].attendanceTracker =
                        AttendanceTracker.Absent
                    R.id.radioUnknown -> Student.Roster2.students[pos].attendanceTracker =
                        AttendanceTracker.Unknown
                }

                refreshListener.onRefresh()
            }
        }

        fun setData2(student: Student?, pos: Int){
            itemView.txvTitle.text = student!!.title

            this.currentStudent = student
            this.currentPosition = pos

            when (student.attendanceTracker) {
                AttendanceTracker.Present -> radioGroup.check(R.id.radioPresent)
                AttendanceTracker.Late -> radioGroup.check(R.id.radioLate)
                AttendanceTracker.Absent -> radioGroup.check(R.id.radioAbsent)
                AttendanceTracker.Unknown -> radioGroup.check(R.id.radioUnknown)
            }

            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.radioPresent -> Student.Roster2.students[pos].attendanceTracker = AttendanceTracker.Present
                    R.id.radioLate -> Student.Roster2.students[pos].attendanceTracker = AttendanceTracker.Late
                    R.id.radioAbsent -> Student.Roster2.students[pos].attendanceTracker = AttendanceTracker.Absent
                    R.id.radioUnknown -> Student.Roster2.students[pos].attendanceTracker = AttendanceTracker.Unknown
                }

                refreshListener.onRefresh()
            }
        }

    }
}