package com.example.football_score;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.football_score.databinding.ActivityDetailBinding;
import com.example.football_score.databinding.ActivityMainBinding;

public class DetailActivity extends AppCompatActivity {

    public static final String TOTAL = "total";
    public static final String NAME_LOCAL = "nameLocal";
    public static final String NAME_VISIT = "nameVisit";
    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle extras = getIntent().getExtras();

        String total = extras.getString(TOTAL);
        String nameL = extras.getString(NAME_LOCAL);
        String nameV = extras.getString(NAME_VISIT);

        String [] totals = total.split(" - ");
        int scoreL = Integer.parseInt(totals[0].trim());
        int scoreV = Integer.parseInt(totals[1].trim());

        binding.textView4.setText(total);
        binding.textView2.setText(nameL + " - " + nameV);

        if(scoreL == scoreV){
            binding.textView5.setText(getString(R.string.fue_un_empate));
        }else if(scoreL > scoreV){
            binding.textView5.setText("¡Ganó el "+nameL+"! \uD83C\uDF89");
        }else{
            binding.textView5.setText("¡Ganó el "+nameV+"! \uD83C\uDF89");
        }

    }
}