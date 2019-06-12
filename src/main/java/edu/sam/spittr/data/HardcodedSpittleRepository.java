package edu.sam.spittr.data;

import com.google.gson.Gson;
import edu.sam.spittr.Spittle;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.util.Precision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Constructor;
import java.util.*;

@Repository
public class HardcodedSpittleRepository implements SpittleRepository {

    private List<Spittle> spittleList;
    private long nextId = 1L;

    @Autowired
    public HardcodedSpittleRepository() {
        final long initialNumberOfEntries = 20L;

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
        }
    }

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        List<Spittle> spittleListCopy = null;
        try {
            Class<?> spittleListClass = spittleList.getClass();
            Constructor <?> spittleListClassConstructor = spittleListClass.getConstructor();
            spittleListCopy = (List<Spittle>) spittleListClassConstructor.newInstance();
            spittleListCopy.addAll(Arrays.asList(new Spittle[spittleList.size()]));
        } catch (Exception e) {
            System.out.println(e);
        }
        Collections.copy(spittleListCopy, spittleList);
        return spittleListCopy;
    }

    @Override
    public long save(Spittle spittleToPersist){
        Spittle newSpittle = new Spittle(nextId,
                spittleToPersist.getMessage(),
                spittleToPersist.getTime(),
                spittleToPersist.getLongitude(),
                spittleToPersist.getLatitude());

        spittleList.add(newSpittle);
        return nextId++;
    }

    @Override
    public Spittle findById(long id) {
        Spittle spittle = findOriginalById(id);
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(spittle), Spittle.class);
    }

    @Override
    public long update(long id, Spittle editSpittle) {
        Spittle spittle = findOriginalById(id);
        if(spittle != null) {
            spittle.setMessage(editSpittle.getMessage());
            spittle.setTime(editSpittle.getTime());
            spittle.setLongitude(editSpittle.getLongitude());
            spittle.setLatitude(editSpittle.getLatitude());
            return id;
        } else{
            return 0L;
        }
    }

    @Override
    public long remove(long id) {
        Spittle spittleToDelete = findOriginalById(id);
        if (spittleToDelete != null) {
            spittleList.remove(spittleToDelete);
            return id;
        } else {
            return 0L;
        }
    }

    private Spittle findOriginalById(long id) {
        Spittle spittle = null;
        for (int i = spittleList.size() - 1; i >= 0; i--) {
            spittle = spittleList.get(i);
            if(spittle.getId() == id) {
                break;
            }
        }
        return spittle;
    }

}
