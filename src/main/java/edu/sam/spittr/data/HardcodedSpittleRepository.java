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

    private final Long numberOfEntries = 20L;
    private List<Spittle> spittleList;

    @Autowired
    public HardcodedSpittleRepository() {
        final Date currentDate = new Date();

        final double minLongitude = -180D;
        final double maxLongitude = 180D;

        final double minLatitude = -90D;
        final double maxLatitude = 90D;

        spittleList = new ArrayList<>();

        for(long i = 1; i <= numberOfEntries; i++) {
            Spittle newSpittle = new Spittle();
            RandomDataGenerator randomizer = new RandomDataGenerator();

            long newSpittleTimeInMillis = randomizer.nextLong(0L, currentDate.getTime());
            double newSpittleLongitude = Precision.round(randomizer.nextUniform(minLongitude, maxLongitude), 2);
            double newSpittleLatitude = Precision.round(randomizer.nextUniform(minLatitude, maxLatitude), 2);

            newSpittle.setMessage("Спитл No " + i);
            newSpittle.setTime(new Date(newSpittleTimeInMillis));
            newSpittle.setLongitude(newSpittleLongitude);
            newSpittle.setLatitude(newSpittleLatitude);
            spittleList.add(newSpittle);
        }
    }

    @Override
    public List<Spittle> findSpittles(long max, int count){
        return spittleList;
    }
}
