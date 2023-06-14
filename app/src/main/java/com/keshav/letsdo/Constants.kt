package com.keshav.letsdo

object Constants {
    const val  USER_NAME :String = "User_name"
    const val TOTAL_QUESTION : String = "total_Question"
    const val CORRECT_ANSWER : String = "correct_answer"
    fun getQuestions() : ArrayList<Questions>{
        val QuestionsList = ArrayList<Questions>()
        val que1 = Questions(
            1,
            "Which country does this flag bilong too",
            R.drawable.ic_flag_of_india,
            "Argentina","Australia","India","Pakistan",3

        )
        val que2 = Questions(
            2,
            "Which country does this flag bilong too",
            R.drawable.ic_flag_of_argentina,
            "Argentina","Australia","India","Pakistan",1

        )
        val que3 = Questions(
            3,
            "Which country does this flag bilong too",
            R.drawable.ic_flag_of_australia,
            "Argentina","Australia","India","Pakistan",2

        )
        val que4 = Questions(
            4,
            "Which country does this flag bilong too",
            R.drawable.ic_flag_of_germany,
            "Argentina","Germany","India","Pakistan",2

        )
        QuestionsList.add(que1)
        QuestionsList.add(que2)
        QuestionsList.add(que3)
        QuestionsList.add(que4)
        return QuestionsList
    }
}