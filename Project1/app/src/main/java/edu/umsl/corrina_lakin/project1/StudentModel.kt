package edu.umsl.corrina_lakin.project1

enum class AttendanceTracker {
    Present,
    Late,
    Absent,
    Unknown
}

data class Student (var title: String, var attendanceTracker: AttendanceTracker = AttendanceTracker.Unknown) {

    object Roster1 {

        val students = listOf(
            Student("Black Widow"),
            Student("Captain America"),
            Student("Hawkeye"),
            Student("Hulk"),
            Student("Iron Man"),
            Student("Spencer Reid")
        )
    }

    object Roster2 {

        val students = listOf(
            Student("Spencer Reid"),
            Student("Magneto"),
            Student("Mystique"),
            Student("Professor X"),
            Student("Storm"),
            Student("Wolverine")
        )
    }

}
