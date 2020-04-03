package com.HSEteam.FinalProjectQuiz

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Log

import java.util.ArrayList

import com.HSEteam.FinalProjectQuiz.QuizContract.QuestionsTable

class QuizDBHelper(context: Context, private val categoryIntentBundle: Bundle) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    private val CREATE_TABLE_QUERY = "CREATE TABLE " + QuestionsTable.TABLE_NAME +
            "(" +
            QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QuestionsTable.COLUMN_QUESTION + " TEXT, " +
            QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
            QuestionsTable.COLUMN_ANSWER + " TEXT, " +
            QuestionsTable.COLUMN_CATEGORY + " TEXT" +
            ")"

    private val DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME

    private var db: SQLiteDatabase? = null
    private var mQuestionList: MutableList<Question>? = null

    override fun onCreate(db: SQLiteDatabase) {
        this.db = db
        db.execSQL(CREATE_TABLE_QUERY)

        setUpQuestions()
        insertQuestions()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_TABLE_QUERY)
        onCreate(db)
    }

    private fun setUpQuestions() {
        mQuestionList = ArrayList()


        //questions for category russia
        mQuestionList!!.add(Question("Какой город славится пряниками и оборонной промышленностью?", "Москва", "Тула", "Грозный", "Ижевск", "Тула", CATEGORY_RUSSIA))
        mQuestionList!!.add(Question("Какая область является анклавом по отношению к России?", "Калининградская", "Ростовская", "Ленинградская", "Брестская", "Калининградская", CATEGORY_RUSSIA))
        mQuestionList!!.add(Question("В одной известной песне течёт река кхм-кхм, конца и края нет. Что это за река?", "Волга", "Урал", "Ока", "Москва", "Волга", CATEGORY_RUSSIA))
        mQuestionList!!.add(Question("Где находится Роза-Хутор?", "Сочи", "Грозный", "Махачкала", "Якутск", "Сочи", CATEGORY_RUSSIA))
        mQuestionList!!.add(Question("Самая высокая точка России?", "Казбек", "Эльбрус", "Ключевская Сопка", "Эверест", "Эльбрус", CATEGORY_RUSSIA))


        //questions for category movies
        mQuestionList!!.add(Question("Фильм с Гослингом про киборгов?", "Бегущий по лезвию 2049", "Только бог простит", "Драйв", "Как тебе моя тачка, чувак?", "Бегущий по лезвию 2049", CATEGORY_MOVIES))
        mQuestionList!!.add(Question("Какой актёр играет Измайлова в Полицейском с Рублёвки?", "А. Петров", "Жан Рено", "С. Бурунов", "И. Ургант", "А. Петров", CATEGORY_MOVIES))
        mQuestionList!!.add(Question("Какого цвета Халк?", "Зелёный", "Чёрный", "Синий", "Красный", "Зелёный", CATEGORY_MOVIES))
        mQuestionList!!.add(Question("Фамилия агента 007?", "Бонд", "Бленд", "Блант", "Смит", "Бонд", CATEGORY_MOVIES))
        mQuestionList!!.add(Question("Русский режиссёр, получивший Оскар?", "Михалков", "Быков", "Скорсезе", "Нагиев", "Михалков", CATEGORY_MOVIES))


        //questions for category tv
        mQuestionList!!.add(Question("Куда уехал Измайлов после отставки?", "Греция", "Голландия", "Австрия", "Швеция", "Швеция", CATEGORY_TV))
        mQuestionList!!.add(Question("Джимми Киммел по-русски?", "Ургант", "Понасенков", "Пучков", "Соловьёв", "Ургант", CATEGORY_TV))
        mQuestionList!!.add(Question("Среднее имя Шелдона?", "Ленард", "Джон", "Ли", "Брайан", "Ли", CATEGORY_TV))
        mQuestionList!!.add(Question("Каково настоящее имя Джона Сноу?", "Эймон Тарзарин", "Эйгон Таргариен", "Дэймон Зарбариен", "Мэтт Дэймон", "Эйгон Таргариен", CATEGORY_TV))
        mQuestionList!!.add(Question("Шоу про идиотов на ТНТ?", "Дом 2", "Дом 2.", "Дом. 2", "До.м 2", "Дом 2", CATEGORY_TV))

        mQuestionList!!.add(Question("Первый элемент в периодической таблице?", "Уран", "Гелий", "Водород", "Углерод", "Водород", CATEGORY_SCIENCE))
        mQuestionList!!.add(Question("Кто автор закона о существовании силы противодействия?", "Ньютон", "Ампер", "Фарадей", "Эйнштейн", "Ньютон", CATEGORY_SCIENCE))
        mQuestionList!!.add(Question("Каким прибором измеряется сила тока?", "Амперметр", "Спектрометр", "Авометр", "Вольтметр", "Амперметр", CATEGORY_SCIENCE))
        mQuestionList!!.add(Question("Какая планета наиболее сопоставима по размерам с Землей?", "Меркурий", "Марс", "Венера", "Юпитер", "Венера", CATEGORY_SCIENCE))
        mQuestionList!!.add(Question("Кто автор теории об естественном отборе?", "Герберт Спенсер", "Чарльз Диккенс", "Чарльз Дикен", "Карл Ммаркс", "Чарльз Диккенс", CATEGORY_SCIENCE))


        mQuestionList!!.add(Question("Which is a synonym of vigilant?", "puzzled", "watchful", "unhealthy", "wide", "watchful", CATEGORY_WORD))
        mQuestionList!!.add(Question("Which of these is a word for an artistic critique?", "chanteuse", "charrette", "chariot", "charlatan", "charrette", CATEGORY_WORD))
        mQuestionList!!.add(Question("Which of these words means \"substitute\"?", "prediction", "period", "proof", "proxy", "proxy", CATEGORY_WORD))
        mQuestionList!!.add(Question("Which word means something like \"paradise\"?", "unicorn", "antelope", "utility", "utopia", "utopia", CATEGORY_WORD))
        mQuestionList!!.add(Question("Which is a synonym of acute?", "severe", "tired", "long", "open", "severe", CATEGORY_WORD))


        mQuestionList!!.add(Question("В какой команде сейчас играет Леброн Джеймс", "Кливленд", "Майами", "Лейкерс", "Team Superior", "Лейкерс", CATEGORY_SPORTS))
        mQuestionList!!.add(Question("Сколько золотых медалей выиграл Майкс Фелпс на Олимпийских играх?", "4", "10", "23", "0", "23", CATEGORY_SPORTS))
        mQuestionList!!.add(Question("Сколько замен в основное время в футболе?", "0", "1", "3", "Сколько угодно", "3", CATEGORY_SPORTS))
        mQuestionList!!.add(Question("В каком городе проходили Летние Олимпийские Игры 2008 года", "Пекин", "Лондон", "Москва", "Париж", "Пекин", CATEGORY_SPORTS))
        mQuestionList!!.add(Question("Как зовут главного тренера сборной России по футболу?", "С. Черчесов", "Д. Марадонна", "Мадонна", "А. Кокорин", "С. Черчесов", CATEGORY_SPORTS))
    }


    private fun insertQuestions() {
        for (q in mQuestionList!!) {
            val contentValues = ContentValues()
            contentValues.put(QuestionsTable.COLUMN_QUESTION, q.getmQuestion())
            contentValues.put(QuestionsTable.COLUMN_OPTION1, q.getmOption1())
            contentValues.put(QuestionsTable.COLUMN_OPTION2, q.getmOption2())
            contentValues.put(QuestionsTable.COLUMN_OPTION3, q.getmOption3())
            contentValues.put(QuestionsTable.COLUMN_OPTION4, q.getmOption4())
            contentValues.put(QuestionsTable.COLUMN_ANSWER, q.getmAnswer())
            contentValues.put(QuestionsTable.COLUMN_CATEGORY, q.getmCategory())
            db!!.insert(QuestionsTable.TABLE_NAME, null, contentValues)
        }
    }

    fun getAllQuestions(categoryID: String): ArrayList<Question> {
        Log.d("TAG", "Getting all questions for : $categoryID")
        val questionList = ArrayList<Question>()
        db = readableDatabase
        val SELECT_TABLE_QUERY = "SELECT * FROM " + QuestionsTable.TABLE_NAME + " WHERE " + QuestionsTable.COLUMN_CATEGORY + " = \"" + categoryID + "\""
        val cursor = db!!.rawQuery(SELECT_TABLE_QUERY, null)
        if (cursor.moveToFirst()) {
            do {
                val question = Question()
                question.setmQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTION)))
                question.setmOption1(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION1)))
                question.setmOption2(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION2)))
                question.setmOption3(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION3)))
                question.setmOption4(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION4)))
                question.setmAnswer(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER)))
                questionList.add(question)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return questionList
    }

    companion object {

        private val DB_NAME = "quizzes.db"
        private val DB_VERSION = 8

        val CATEGORY_SPORTS = "sports"
        val CATEGORY_TV = "tv"
        val CATEGORY_MOVIES = "movies"
        val CATEGORY_RUSSIA = "russia"
        val CATEGORY_WORD = "words"
        val CATEGORY_SCIENCE = "science"
    }
}
