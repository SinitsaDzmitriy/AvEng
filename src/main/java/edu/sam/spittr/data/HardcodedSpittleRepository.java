package edu.sam.spittr.data;

import edu.sam.spittr.Spittle;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.util.Precision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class HardcodedSpittleRepository implements SpittleRepository {


    private final long initialNumberOfEntries = 20L;

    private List<Spittle> spittleList;
    private long numberOfEntries = 0L;
    private long nextId = 1L;

    @Autowired
    public HardcodedSpittleRepository() {
        final Date currentDate = new Date();

        final double minLongitude = -180D;
        final double maxLongitude = 180D;

        final double minLatitude = -90D;
        final double maxLatitude = 90D;

        spittleList = new ArrayList<>();

        for(long i = 1; i <= initialNumberOfEntries; i++) {
            RandomDataGenerator randomizer = new RandomDataGenerator();

            long newSpittleTimeInMillis = randomizer.nextLong(0L, currentDate.getTime());
            double newSpittleLongitude = Precision.round(randomizer.nextUniform(minLongitude, maxLongitude), 2);
            double newSpittleLatitude = Precision.round(randomizer.nextUniform(minLatitude, maxLatitude), 2);

            Spittle newSpittle = new Spittle(nextId,
                    "Высказывание с ID = " + nextId,
                    new Date(newSpittleTimeInMillis),
                    newSpittleLongitude,
                    newSpittleLatitude);

            spittleList.add(newSpittle);

            nextId++;
            numberOfEntries++;
        }
    }

    @Override
    public List<Spittle> findSpittles(long max, int count){
        return spittleList;
    }

    @Override
    public long save(Spittle spittleToPersist){
        Spittle newSpittle = new Spittle(nextId,
                spittleToPersist.getMessage(),
                spittleToPersist.getTime(),
                spittleToPersist.getLongitude(),
                spittleToPersist.getLatitude());

        spittleList.add(newSpittle);
        numberOfEntries++;
        return nextId++;
    }
}
