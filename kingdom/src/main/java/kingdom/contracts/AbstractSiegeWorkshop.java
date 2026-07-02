package kingdom.contracts;

import kingdom.core.KingdomEntity;

public abstract class AbstractSiegeWorkshop implements KingdomEntity {

    /**
     * Builds a new siege engine (catapult) with a given name.
     * @param name the name of the siege engine
     */
    public abstract void buildCatapult(String name);

    /**
     * Returns the total number of siege engines built.
     * @return siege engine count
     */
    public abstract int getCatapultCount();

    /**
     * Test-fires a siege engine and records its range.
     * @return the range achieved in meters
     */
    public abstract int testFire();

    /**
     * Returns the average range of all test-fires conducted.
     * @return average range in meters
     */
    public abstract double getAverageRange();
}