package com.example.android.courtcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView teamAScoreTv;
    private Button teamAThreeScoreBtn;
    private Button teamATwoScoreBtn;
    private Button teamAFreeThrowBtn;

    private TextView teamBScoreTv;
    private Button teamBThreeScoreBtn;
    private Button teamBTwoScoreBtn;
    private Button teamBFreeThrowBtn;

    private Button resetBtn;
    private Button endBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamAScoreTv = (TextView) findViewById(R.id.tv_team_a_score);
        teamAThreeScoreBtn = (Button) findViewById(R.id.btn_three_score_a);
        teamATwoScoreBtn = (Button) findViewById(R.id.btn_two_score_a);
        teamAFreeThrowBtn = (Button) findViewById(R.id.btn_free_throw_a);
        teamBScoreTv = (TextView) findViewById(R.id.tv_team_b_score);
        teamBThreeScoreBtn = (Button) findViewById(R.id.btn_three_score_b);
        teamBTwoScoreBtn = (Button) findViewById(R.id.btn_two_score_b);
        teamBFreeThrowBtn = (Button) findViewById(R.id.btn_free_throw_b);
        resetBtn = (Button) findViewById(R.id.btn_reset);
        endBtn = (Button) findViewById(R.id.btn_end);
        init();
    }

    /**
     * 初始化两队的分数
     */
    private int teamAScore = 0;
    private int teamBScore = 0;

    public void init() {
        teamAScore = 0;
        teamBScore = 0;
        teamAThreeScoreBtn.setOnClickListener(this);
        teamATwoScoreBtn.setOnClickListener(this);
        teamAFreeThrowBtn.setOnClickListener(this);
        teamBThreeScoreBtn.setOnClickListener(this);
        teamBTwoScoreBtn.setOnClickListener(this);
        teamBFreeThrowBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
        endBtn.setOnClickListener(this);
    }

    private static int TEAM_A = 0;
    private static int TEAM_B = 1;

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_three_score_a:
                addScore(3, TEAM_A);
                break;
            case R.id.btn_two_score_a:
                addScore(2, TEAM_A);
                break;
            case R.id.btn_free_throw_a:
                addScore(1, TEAM_A);
                break;
            case R.id.btn_three_score_b:
                addScore(3, TEAM_B);
                break;
            case R.id.btn_two_score_b:
                addScore(2, TEAM_B);
                break;
            case R.id.btn_free_throw_b:
                addScore(1, TEAM_B);
                break;
            case R.id.btn_reset:
                reset();
                break;
            case R.id.btn_end:
                end();
                break;

        }
    }

    private void end() {
        if (teamAScore > teamBScore) {
            Toast.makeText(this, "Team A Win", Toast.LENGTH_SHORT).show();
        } else if (teamAScore < teamBScore) {
            Toast.makeText(this, "Team B Win", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "进入加时赛", Toast.LENGTH_SHORT).show();
        }
    }

    private void reset() {
        teamAScore = 0;
        teamBScore = 0;
        teamAScoreTv.setText(String.valueOf(teamAScore));
        teamBScoreTv.setText(String.valueOf(teamBScore));
    }

    private void addScore(int i, int team) {
        if (team == TEAM_A) {
            teamAScore += i;
            teamAScoreTv.setText(String.valueOf(teamAScore));
        } else {
            teamBScore += i;
            teamBScoreTv.setText(String.valueOf(teamBScore));
        }


    }
}
