package model;

/**
 * An enum representing different levels of physical activity.
 *
 * Each enum value has an associated activity level which is a double value.
 *
 * @author Samad
 */
public enum LifeStyle {
    SEDENTAIRE(1.2),
    PEU_ACTIF(1.375),
    ACTIF(1.55),
    FORT_ACTIF(1.725),
    EXTREMENT_ACTIF(1.9);

    private final double activityLevel;

    /**
     * Constructs a LifeStyle enum value with the given activity level.
     *
     * @param activityLevel the activity level associated with the enum value
     */
    LifeStyle(double activityLevel) {
        this.activityLevel = activityLevel;
    }

    /**
     * Returns the activity level associated with this enum value.
     *
     * @return the activity level associated with this enum value
     */
    public double getActivityLevel() {
        return activityLevel;
    }

}
