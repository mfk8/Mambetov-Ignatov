package com.HSEteam.FinalProjectQuiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.HSEteam.FinalProjectQuiz.QuizContract.QuestionsTable;

public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "quizzes.db";
    private static final int DB_VERSION = 8;

    public static final String CATEGORY_SPORTS = "sports";
    public static final String CATEGORY_TV = "tv";
    public static final String CATEGORY_MOVIES = "movies";
    public static final String CATEGORY_RUSSIA = "russia";
    public static final String CATEGORY_WORD = "words";
    public static final String CATEGORY_SCIENCE = "science";

    private final String CREATE_TABLE_QUERY = "CREATE TABLE " + QuestionsTable.TABLE_NAME +
            "(" +
            QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QuestionsTable.COLUMN_QUESTION + " TEXT, " +
            QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
            QuestionsTable.COLUMN_ANSWER + " TEXT, " +
            QuestionsTable.COLUMN_CATEGORY + " TEXT" +
            ")";

    private final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME;

    private SQLiteDatabase db;
    private List<Question> mQuestionList;

    private Bundle categoryIntentBundle;

    public QuizDBHelper(Context context, Bundle bundle) {
        super(context, DB_NAME, null, DB_VERSION);
        this.categoryIntentBundle = bundle;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(CREATE_TABLE_QUERY);

        setUpQuestions();
        insertQuestions();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_QUERY);
        onCreate(db);
    }

    private void setUpQuestions() {
        mQuestionList = new ArrayList<>();


        //questions for category russia
        mQuestionList.add(new Question("Какой город славится пряниками и оборонной промышленностью?", "Москва", "Тула", "Грозный", "Ижевск", "Тула", CATEGORY_RUSSIA));
        mQuestionList.add(new Question("Какая область является анклавом по отношению к России?", "Калининградская", "Ростовская", "Ленинградская", "Брестская", "Калининградская", CATEGORY_RUSSIA));
        mQuestionList.add(new Question("В одной известной песне течёт река кхм-кхм, конца и края нет. Что это за река?", "Волга", "Урал", "Ока", "Москва", "Волга", CATEGORY_RUSSIA));
        mQuestionList.add(new Question("Где находится Роза-Хутор?", "Сочи", "Грозный", "Махачкала", "Якутск", "Сочи", CATEGORY_RUSSIA));
        mQuestionList.add(new Question("Самая высокая точка России?", "Казбек", "Эльбрус", "Ключевская Сопка", "Эверест", "Эльбрус", CATEGORY_RUSSIA));


        //questions for category movies
        mQuestionList.add(new Question("Фильм с Гослингом про киборгов?", "Бегущий по лезвию 2049", "Только бог простит", "Драйв", "Как тебе моя тачка, чувак?", "Бегущий по лезвию 2049", CATEGORY_MOVIES));
        mQuestionList.add(new Question("Какой актёр играет Измайлова в Полицейском с Рублёвки?", "А. Петров", "Жан Рено", "С. Бурунов", "И. Ургант", "А. Петров", CATEGORY_MOVIES));
        mQuestionList.add(new Question("Какого цвета Халк?", "Зелёный", "Чёрный", "Синий", "Красный", "Зелёный", CATEGORY_MOVIES));
        mQuestionList.add(new Question("Фамилия агента 007?", "Бонд", "Бленд", "Блант", "Смит", "Бонд", CATEGORY_MOVIES));
        mQuestionList.add(new Question("Русский режиссёр, получивший Оскар?", "Михалков", "Быков", "Скорсезе", "Нагиев", "Михалков", CATEGORY_MOVIES));


        //questions for category tv
        mQuestionList.add(new Question("Куда уехал Измайлов после отставки?", "Греция", "Голландия", "Австрия", "Швеция", "Швеция", CATEGORY_TV));
        mQuestionList.add(new Question("Джимми Киммел по-русски?", "Ургант", "Понасенков", "Пучков", "Соловьёв", "Ургант", CATEGORY_TV));
        mQuestionList.add(new Question("Среднее имя Шелдона?", "Ленард", "Джон", "Ли", "Брайан", "Ли", CATEGORY_TV));
        mQuestionList.add(new Question("Каково настоящее имя Джона Сноу?", "Эймон Тарзарин", "Эйгон Таргариен", "Дэймон Зарбариен", "Мэтт Дэймон", "Эйгон Таргариен", CATEGORY_TV));
        mQuestionList.add(new Question("Шоу про идиотов на ТНТ?", "Дом 2", "Дом 2.", "Дом. 2", "До.м 2", "Дом 2", CATEGORY_TV));

        mQuestionList.add(new Question("Первый элемент в периодической таблице?", "Уран", "Гелий", "Водород", "Углерод", "Водород", CATEGORY_SCIENCE));
        mQuestionList.add(new Question("Кто автор закона о существовании силы противодействия?", "Ньютон", "Ампер", "Фарадей", "Эйнштейн", "Ньютон", CATEGORY_SCIENCE));
        mQuestionList.add(new Question("Каким прибором измеряется сила тока?", "Амперметр", "Спектрометр", "Авометр", "Вольтметр", "Амперметр", CATEGORY_SCIENCE));
        mQuestionList.add(new Question("Какая планета наиболее сопоставима по размерам с Землей?", "Меркурий", "Марс", "Венера", "Юпитер", "Венера", CATEGORY_SCIENCE));
        mQuestionList.add(new Question("Кто автор теории об естественном отборе?", "Герберт Спенсер", "Чарльз Диккенс", "Чарльз Дикен", "Карл Ммаркс", "Чарльз Диккенс", CATEGORY_SCIENCE));


        mQuestionList.add(new Question("Which is a synonym of vigilant?", "puzzled", "watchful", "unhealthy", "wide", "watchful", CATEGORY_WORD));
        mQuestionList.add(new Question("Which of these is a word for an artistic critique?", "chanteuse", "charrette", "chariot", "charlatan", "charrette", CATEGORY_WORD));
        mQuestionList.add(new Question("Which of these words means \"substitute\"?", "prediction", "period", "proof", "proxy", "proxy", CATEGORY_WORD));
        mQuestionList.add(new Question("Which word means something like \"paradise\"?", "unicorn", "antelope", "utility", "utopia", "utopia", CATEGORY_WORD));
        mQuestionList.add(new Question("Which is a synonym of acute?", "severe", "tired", "long", "open", "severe", CATEGORY_WORD));


        mQuestionList.add(new Question("В какой команде сейчас играет Леброн Джеймс", "Кливленд", "Майами", "Лейкерс", "Team Superior", "Лейкерс", CATEGORY_SPORTS));
        mQuestionList.add(new Question("Сколько золотых медалей выиграл Майкс Фелпс на Олимпийских играх?", "4", "10", "23", "0", "23", CATEGORY_SPORTS));
        mQuestionList.add(new Question("Сколько замен в основное время в футболе?", "0", "1", "3", "Сколько угодно", "3", CATEGORY_SPORTS));
        mQuestionList.add(new Question( "В каком городе проходили Летние Олимпийские Игры 2008 года", "Пекин", "Лондон", "Москва", "Париж", "Пекин",  CATEGORY_SPORTS));
        mQuestionList.add(new Question( "Как зовут главного тренера сборной России по футболу?", "С. Черчесов", "Д. Марадонна", "Мадонна", "А. Кокорин", "С. Черчесов", CATEGORY_SPORTS));
    }






    private void insertQuestions() {
        for(Question q : mQuestionList) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(QuestionsTable.COLUMN_QUESTION, q.getmQuestion());
            contentValues.put(QuestionsTable.COLUMN_OPTION1, q.getmOption1());
            contentValues.put(QuestionsTable.COLUMN_OPTION2, q.getmOption2());
            contentValues.put(QuestionsTable.COLUMN_OPTION3, q.getmOption3());
            contentValues.put(QuestionsTable.COLUMN_OPTION4, q.getmOption4());
            contentValues.put(QuestionsTable.COLUMN_ANSWER, q.getmAnswer());
            contentValues.put(QuestionsTable.COLUMN_CATEGORY, q.getmCategory());
            db.insert(QuestionsTable.TABLE_NAME, null, contentValues);
        }
    }

    public ArrayList<Question> getAllQuestions(String categoryID) {
        Log.d("TAG", "Getting all questions for : " + categoryID);
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        String SELECT_TABLE_QUERY = "SELECT * FROM " + QuestionsTable.TABLE_NAME + " WHERE " + QuestionsTable.COLUMN_CATEGORY + " = \"" + categoryID + "\"";
        Cursor cursor = db.rawQuery(SELECT_TABLE_QUERY, null);
        if(cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setmQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setmOption1(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setmOption2(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setmOption3(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setmOption4(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setmAnswer(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER)));
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }
}
