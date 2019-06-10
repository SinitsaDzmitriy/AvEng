package edu.sam.spittr.data;

import edu.sam.spittr.entities.Spittle;
import java.util.List;

public interface SpittleRepository {
    /**
     * @param max - maximum ID of any Spittle that should be returned.
     * @param count - indicates how many Spittle objects to return.
     * @return
     */
    List<Spittle> findSpittles(long max, int count);

    // finds a Spittle by ID
    Spittle findOne(long id);
}
