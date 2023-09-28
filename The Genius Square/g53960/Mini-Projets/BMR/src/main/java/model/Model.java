package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The Model class represents the business logic of the application for
 * calculating BMR and calories based on user inputs.
 *
 * It maintains the result of BMR and calories, and provides methods for
 * performing the calculations and notifying observers
 *
 * of changes to the results.
 *
 * @author Samad
 */
public class Model {

    private double resultBmr;
    private double resultCalories;
    private final PropertyChangeSupport observable;

    /**
     * Constructs a Model object with default values for resultBmr and
     * resultCalories, and initializes the observable object for notifying
     * observers of property changes.
     */
    public Model() {
        this.resultBmr = 0;
        this.resultCalories = 0;
        this.observable = new PropertyChangeSupport(this);
    }

    /**
     *
     * Calculates the basal metabolic rate (BMR) based on user inputs for size,
     * weight, year of birth, and gender.
     *
     * @param size The user's height in centimeters.
     * @param weight The user's weight in kilograms.
     * @param year The user's birth year.
     * @param isWoman A boolean value indicating whether the user is female.
     */
    public void calculateBMR(int size, int weight, int year, boolean isWoman) {
        if (isWoman) {
            resultBmr = 9.6 * weight + 1.8 * size - 4.7 * year + 655;
        } else {
            resultBmr = 13.7 * weight + 5 * size - 6.8 * year + 66;
        }
        observable.firePropertyChange("Notification bmr", 0, resultBmr);
    }

    /**
     * Calculates the daily calorie needs based on the user's BMR and selected
     * lifestyle.
     *
     * @param lifeStyle The selected lifestyle.
     */
    public void calculateCalories(LifeStyle lifeStyle) {
        resultCalories = resultBmr * lifeStyle.getActivityLevel();
        observable.firePropertyChange("Notification calories", 0, resultCalories);

    }

    /**
     * Gets the result of the basal metabolic rate calculation.
     *
     * @return The BMR result.
     */
    public double getResultBmr() {
        return resultBmr;
    }

    /**
     * Gets the result of the daily calorie needs calculation.
     *
     * @return The calorie needs result.
     */
    public double getResultCalories() {
        return resultCalories;
    }

    /**
     * Adds a property change listener to the observable object for notifying
     * observers of property changes.
     *
     * @param listener The listener to add.
     */
    public void addObserver(PropertyChangeListener listener) {
        observable.addPropertyChangeListener(listener);
    }
}
