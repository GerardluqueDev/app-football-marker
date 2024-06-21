package com.example.football_score;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<Integer> scoreLocal = new MutableLiveData<>();
    private final MutableLiveData<Integer> scoreVisit = new MutableLiveData<>();

    public MainViewModel() {
        restartScores(); // en el constructor llamamos a la función para resetear a 0 los valores ya que el MutableLiveData por defecto es null
    }

    public LiveData<Integer> getScoreLocal() { // aqui cambiamos a MutableLiveData<Integer> porque ya no devolvemos un entero
        return scoreLocal;
    }

    public LiveData<Integer> getScoreVisit() { // aqui cambiamos a MutableLiveData<Integer> porque ya no devolvemos un entero
        return scoreVisit;
    }

    public void restartScores() {
        scoreLocal.setValue(0); // asi es como lo igualamos a 0
        scoreVisit.setValue(0);
    }

    public void incrementScoreVisit() {
        scoreVisit.setValue(scoreVisit.getValue() + 1); // incrementa en 1 (seria como ponerr ++)
    }

    public void decrementScoreVisit() {
        if(scoreVisit.getValue() == 0){
            scoreVisit.setValue(0);
        }else{
            scoreVisit.setValue(scoreVisit.getValue() - 1);
        }
    }

    public void decrementScoreLocal() {
        if(scoreLocal.getValue() == 0){
            scoreLocal.setValue(0);
        }else{
            scoreLocal.setValue(scoreLocal.getValue() - 1);
        }

    }

    public void incrementScoreLocal() {
        scoreLocal.setValue(scoreLocal.getValue() + 1);
    }
}
