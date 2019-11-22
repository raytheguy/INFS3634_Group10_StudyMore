package com.example.studymore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.studymore.Multithreader.AsyncTaskDelegateListScore;
import com.example.studymore.Multithreader.AsyncTaskDelegateString;
import com.example.studymore.Multithreader.InsertScoreAsyncTask;
import com.example.studymore.ui.Quiz.Question;
import com.example.studymore.ui.Quiz.QuestionDatabase;
import com.example.studymore.ui.Quiz.Score;
import com.example.studymore.ui.Quiz.ScoreDatabase;
import com.google.android.material.snackbar.Snackbar;

public class QuizActivity extends AppCompatActivity implements AsyncTaskDelegateString {

    ConstraintLayout questionLayout;
    TextView questionNumber;
    TextView question;
    TextView option1;
    TextView option2;
    TextView option3;
    RadioGroup radioGroup;
    RadioButton radioButton;

    ConstraintLayout questionAnsweredLayout;
    Button checkAnswerBtn;
    Button nextQuestionBtn;

    ImageView trophy;
    ConstraintLayout endLayout;
    TextView endScore;
    Button saveScoreBtn;

    //question number
    Question questionData;
    int i = 1;
    int score = 0;

    String selectedResult;
    TextView result;
    int attemptNumber;
    int previousAttempts;

    Score scoreResult;
    ScoreDatabase scoreDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Main question screen views
        questionLayout = findViewById(R.id.questionLayout);
        questionNumber = findViewById(R.id.questionNumber);
        question = findViewById(R.id.question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        radioGroup = findViewById(R.id.quizRadioGroup);


        //Answered options view
        questionAnsweredLayout = findViewById(R.id.questionAnsweredOptions);
        result = findViewById(R.id.result);
        nextQuestionBtn = findViewById(R.id.nextQuestionBtn);

        //End screen layout views
        endLayout = findViewById(R.id.endLayout);
        endScore = findViewById(R.id.endScore);
        trophy = findViewById(R.id.trophy);
        saveScoreBtn = findViewById(R.id.saveScoreBtn);

        questionNumber.setText("Question " + Integer.toString(i));

        questionData = QuestionDatabase.getQuestionById(1);

        Context context = getApplicationContext();
        scoreDB = ScoreDatabase.getInstance(context);

        question.setText(questionData.getQuestion());
        option1.setText(questionData.getOption1());
        option2.setText(questionData.getOption2());
        option3.setText(questionData.getOption3());

        checkAnswerBtn = findViewById(R.id.checkAnswerBtn);
        checkAnswerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                //prevent app crash if user selects nothing
                if (radioButton != null) {
                    selectedResult = radioButton.getText().toString();
                }
                questionAnsweredLayout.setVisibility(View.VISIBLE);
                checkAnswerBtn.setVisibility(View.INVISIBLE);

                if (selectedResult == questionData.getAnswer()) {
                    result.setText("Correct");
                    score = score + 1;
                } else {
                    result.setText("Incorrect");
                }

                if (i == 10) {
                    nextQuestionBtn.setText("Finish Quiz :)");
                }

            }
        });

        nextQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i < 10) {
                    setQuestion();
                    questionAnsweredLayout.setVisibility(View.INVISIBLE);
                    checkAnswerBtn.setVisibility(View.VISIBLE);
                } else {
                    endLayout.setVisibility(View.VISIBLE);
                    questionLayout.setVisibility(View.INVISIBLE);
                    endScore.setText(Integer.toString(score) + "/10");
                    if(score<5)
                            trophy.setImageResource(R.drawable.flashcard_bronze);
                        else if(score<8)
                            trophy.setImageResource(R.drawable.flashcard_silver);
                        else
                            trophy.setImageResource(R.drawable.flashcard_gold);
                }
            }
        });

        saveScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                //cannot run this on the main thread, do this somewhere else

                InsertScoreAsyncTask insertScoreAsyncTask = new InsertScoreAsyncTask();
                insertScoreAsyncTask.setDatabase(scoreDB);
                insertScoreAsyncTask.setDelegate(QuizActivity.this);
                insertScoreAsyncTask.setScore(score);
                insertScoreAsyncTask.execute();

            }
        });
    }


    public void setQuestion() {
        i = i + 1;
        questionData = QuestionDatabase.getQuestionById(i);
        questionNumber.setText("Question " + Integer.toString(i));
        question.setText(questionData.getQuestion());
        option1.setText(questionData.getOption1());
        option2.setText(questionData.getOption2());
        option3.setText(questionData.getOption3());
    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
    }

    @Override
    public void handleTaskResult(String result){
        result += " Score Saved";
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    }
}
