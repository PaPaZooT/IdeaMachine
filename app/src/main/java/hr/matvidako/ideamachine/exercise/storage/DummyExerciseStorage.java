package hr.matvidako.ideamachine.exercise.storage;

import java.util.ArrayList;
import java.util.List;

import hr.matvidako.ideamachine.exercise.Exercise;

public class DummyExerciseStorage implements ExerciseStorage{

    @Override
    public List<Exercise> loadExercises() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise(1, "Gratitude", "List 10 things that you don't like and then turn them into things you can be grateful for."));
        exercises.add(new Exercise(2, "Apps", "List 10 things that you don't like and then turn them into things you can be grateful for."));
        exercises.add(new Exercise(3, "Quiet moments", "List 10 things that you don't like and then turn them into things you can be grateful for."));
        exercises.add(new Exercise(4, "Coursera courses", "List 10 things that you don't like and then turn them into things you can be grateful for."));
        return exercises;
    }

}
