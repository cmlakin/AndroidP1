package edu.umsl.corrina_lakin.project1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_attendance.*


class Course1Activity : AppCompatActivity(), RefreshListener {

    private lateinit var textAttendanceRateP: TextView
    private lateinit var textAttendanceRateL: TextView
    private lateinit var textAttendanceRateA: TextView
    private lateinit var textAttendanceRateU: TextView
    private lateinit var btnReset1: Button
    private lateinit var btnReset2: Button
    private lateinit var studentAdapter1: StudentAdapter
    private lateinit var studentAdapter2: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance)

        setUpRecyclerView()

        textAttendanceRateP = findViewById(R.id.textAttendanceViewP)
        textAttendanceRateL = findViewById(R.id.textAttendanceViewL)
        textAttendanceRateA = findViewById(R.id.textAttendanceViewA)
        textAttendanceRateU = findViewById(R.id.textAttendanceViewU)
        btnReset1 = findViewById(R.id.btnReset)
        btnReset2 = findViewById(R.id.btnReset)

        btnReset1.setOnClickListener {
            val students = Student.Roster1.students
            for (student in students) {
                student.attendanceTracker = AttendanceTracker.Unknown
                studentAdapter1.notifyDataSetChanged()
            }
        }

        btnReset2.setOnClickListener {
            val students = Student.Roster2.students
            for (student in students) {
                student.attendanceTracker = AttendanceTracker.Unknown
                studentAdapter2.notifyDataSetChanged()
            }
        }

        setRates1()
        setRates2()
    }

    private  fun setUpRecyclerView(){
        //pick what kind of layout manager you want to use.
        // if layout is not what you want, try changing it to another kind.
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        // this sets our recyclerview to the current layout manager we have created
        recylcerView.layoutManager = layoutManager

        // initialized Student adapter, since list of students is declared under Roster1,

        studentAdapter1 = StudentAdapter(
            this,
            Student.Roster1.students,
            this
        )
        recylcerView.adapter = studentAdapter1

        studentAdapter2 = StudentAdapter(
            this,
            Student.Roster2.students,
            this
        )

        recylcerView.adapter = studentAdapter2


    }

    private fun setRates1() {
        val studentCount1 = Student.Roster1.students.size
        val presentCount1 = Student.Roster1.students.filter { s -> s.attendanceTracker == AttendanceTracker.Present }.size
        val lateCount1 = Student.Roster1.students.filter { s -> s.attendanceTracker == AttendanceTracker.Late }.size
        val absentCount1 = Student.Roster1.students.filter { s -> s.attendanceTracker == AttendanceTracker.Absent }.size
        val unknownCount1 = Student.Roster1.students.filter { s -> s.attendanceTracker == AttendanceTracker.Unknown }.size

        val presentRate = (presentCount1.toDouble() / studentCount1 * 100).toInt()
        val lateRate = (lateCount1.toDouble() / studentCount1 * 100).toInt()
        val absentRate = (absentCount1.toDouble() / studentCount1 * 100).toInt()
        val unknownRate = (unknownCount1.toDouble() / studentCount1 * 100).toInt()

        textAttendanceRateP.text = String.format(getString(R.string.attendance_rateP), presentRate)
        textAttendanceRateL.text = String.format(getString(R.string.attendance_rateL), lateRate)
        textAttendanceRateA.text = String.format(getString(R.string.attendance_rateA), absentRate)
        textAttendanceRateU.text = String.format(getString(R.string.attendance_rateU), unknownRate)

    }

    private fun setRates2() {

        val studentCount2 = Student.Roster2.students.size
        val presentCount2 = Student.Roster2.students.filter { s -> s.attendanceTracker == AttendanceTracker.Present }.size
        val lateCount2 = Student.Roster2.students.filter { s -> s.attendanceTracker == AttendanceTracker.Late }.size
        val absentCount2 = Student.Roster2.students.filter { s -> s.attendanceTracker == AttendanceTracker.Absent }.size
        val unknownCount2 = Student.Roster2.students.filter { s -> s.attendanceTracker == AttendanceTracker.Unknown }.size

        val presentRate2 = (presentCount2.toDouble() / studentCount2 * 100).toInt()
        val lateRate2 = (lateCount2.toDouble() / studentCount2 * 100).toInt()
        val absentRate2 = (absentCount2.toDouble() / studentCount2 * 100).toInt()
        val unknownRate2 = (unknownCount2.toDouble() / studentCount2 * 100).toInt()


        textAttendanceRateP.text = String.format(getString(R.string.attendance_rateP), presentRate2)
        textAttendanceRateL.text = String.format(getString(R.string.attendance_rateL), lateRate2)
        textAttendanceRateA.text = String.format(getString(R.string.attendance_rateA), absentRate2)
        textAttendanceRateU.text = String.format(getString(R.string.attendance_rateU), unknownRate2)
    }

    override fun onRefresh() {
        setRates1()
        setRates2()
    }

}