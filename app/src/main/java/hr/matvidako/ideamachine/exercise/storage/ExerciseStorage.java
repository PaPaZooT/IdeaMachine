package hr.matvidako.ideamachine.exercise.storage;

import java.util.List;

import hr.matvidako.ideamachine.exercise.Exercise;

public interface ExerciseStorage {
    List<Exercise> loadExercises();
}
