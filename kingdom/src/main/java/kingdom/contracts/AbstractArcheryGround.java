package kingdom.contracts;

import kingdom.core.KingdomEntity;

public abstract class AbstractArcheryGround implements KingdomEntity {

    /**
     * Trains a new archer and adds them to the roster.
     * @param archerName the name of the archer
     */
    public abstract void trainArcher(String archerName);

    /**
     * Returns the total number of trained archers.
     * @return archer count
     */
    public abstract int getArcherCount();

    /**
     * Conducts a practice session and reports the accuracy percentage.
     * @return accuracy percentage (0.0 - 100.0)
     */
    public abstract double holdPractice();
}