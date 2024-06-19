package com.example.football_score;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.football_score.databinding.ActivityFirstBinding;
import com.example.football_score.databinding.ActivityMainBinding;

public class FirstActivity extends AppCompatActivity {

    public static final String TOTAL = "total";
    public static final String NAME_LOCAL = "nameLocal";
    public static final String NAME_VISIT = "nameVisit";
    private ActivityFirstBinding binding;
    private int scoreLocal = 0;
    private int scoreVisit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.button1.setOnClickListener(v -> {
            decrementScoreLocal();
        });

        binding.button2.setOnClickListener(v -> {
            incrementScoreLocal();
        });

        binding.button3.setOnClickListener(v -> {
            decrementScoreVisit();
        });

        binding.button4.setOnClickListener(v -> {
            incrementScoreVisit();
        });

        binding.saveButton.setOnClickListener(v ->{

            String result_local = binding.resultLocal.getText().toString();
            String result_visit = binding.resultVisit.getText().toString();
            String resultTotal = result_local + " - " + result_visit;
            String nameL = binding.textLocal.getText().toString();
            String nameV = binding.textVisit.getText().toString();

            openDetailActivity(resultTotal, nameL, nameV);
        });

        binding.imageButton.setOnClickListener(v -> {
            restartScores();
        });

    }

    private void restartScores() {
        scoreLocal = 0;
        scoreVisit = 0;
        binding.resultLocal.setText(String.valueOf(scoreLocal));
        binding.resultVisit.setText(String.valueOf(scoreVisit));
    }

    private void incrementScoreVisit() {
        scoreVisit ++;
        binding.resultVisit.setText(String.valueOf(scoreVisit));
    }

    private void decrementScoreVisit() {
        if(scoreVisit == 0){
            scoreVisit = 0;
        }else{
            scoreVisit --;
            binding.resultVisit.setText(String.valueOf(scoreVisit));
        }
    }

    private void decrementScoreLocal() {
        if(scoreLocal == 0){
            scoreLocal = 0;
        }else{
            scoreLocal --;
            binding.resultLocal.setText(String.valueOf(scoreLocal));
        }

    }

    private void incrementScoreLocal() {
        scoreLocal ++;
        binding.resultLocal.setText(String.valueOf(scoreLocal));
    }


    private void openDetailActivity(String resultTotal, String nameL, String nameV) {
        Intent intent = new Intent(this, DetailActivity.class);

        intent.putExtra(TOTAL, resultTotal);
        intent.putExtra(NAME_LOCAL, nameL);
        intent.putExtra(NAME_VISIT, nameV);

        startActivity(intent);
    }
}