package com.example.ahmed.thequizapp;

import android.provider.BaseColumns;

/**
 * Created by Ahmed on 3/3/2018.
 */


public final class QuizContract {

    private QuizContract() {
    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4="option4";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
        public static final String COLUMN_DIFFICULTY ="difficulty";

    }
}