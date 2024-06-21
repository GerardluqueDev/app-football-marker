package com.example.football_score;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.football_score.databinding.ActivityFirstBinding;
import com.example.football_score.databinding.ActivityMainBinding;

public class FirstActivity extends AppCompatActivity {

    public static final String TOTAL = "total";
    public static final String NAME_LOCAL = "nameLocal";
    public static final String NAME_VISIT = "nameVisit";
    private ActivityFirstBinding binding;
    private MainViewModel viewModel;

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

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.getScoreLocal().observe(this, scoreLocal -> { // observador del scoreLocal
            binding.resultLocal.setText(String.valueOf(scoreLocal));
        });

        viewModel.getScoreVisit().observe(this, scoreVisit -> { // observador del scoreVisit
            binding.resultVisit.setText(String.valueOf(scoreVisit));
        });

        binding.button1.setOnClickListener(v -> {
            viewModel.decrementScoreLocal();
        });

        binding.button2.setOnClickListener(v -> {
            viewModel.incrementScoreLocal();
        });

        binding.button3.setOnClickListener(v -> {
            viewModel.decrementScoreVisit();
        });

        binding.button4.setOnClickListener(v -> {
            viewModel.incrementScoreVisit();
        });

        binding.imageButton.setOnClickListener(v -> {
            viewModel.restartScores();
        });

        binding.saveButton.setOnClickListener(v ->{

            String result_local = binding.resultLocal.getText().toString();
            String result_visit = binding.resultVisit.getText().toString();
            String resultTotal = result_local + " - " + result_visit;
            String nameL = binding.textLocal.getText().toString();
            String nameV = binding.textVisit.getText().toString();

            openDetailActivity(resultTotal, nameL, nameV);
        });

    }

    private void openDetailActivity(String resultTotal, String nameL, String nameV) {
        Intent intent = new Intent(this, DetailActivity.class);

        intent.putExtra(TOTAL, resultTotal);
        intent.putExtra(NAME_LOCAL, nameL);
        intent.putExtra(NAME_VISIT, nameV);

        startActivity(intent);
    }
}