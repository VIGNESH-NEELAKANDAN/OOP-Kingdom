package kingdom.contracts;

import kingdom.core.KingdomEntity;

public abstract class AbstractExplosiveArena implements KingdomEntity {

    /**
     * Trains a new demolitionist in handling explosives.
     * @param name the name of the demolitionist
     */
    public abstract void trainDemolitionist(String name);

    /**
     * Returns the total number of trained demolitionists.
     * @return demolitionist count
     */
    public abstract int getDemolitionistCount();

    /**
     * Prepares an explosive charge for battle.
     * @return the number of explosives ready after preparation
     */
    public abstract int prepareExplosive();

    /**
     * Returns the number of explosives currently ready for use.
     * @return explosives ready count
     */
    public abstract int getExplosivesReady();
}