package edu.sam.spittr.repository;

import com.google.gson.Gson;
import edu.sam.spittr.dto.SpittleDTO;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.util.Precision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Constructor;
import java.time.LocalTime;
import java.util.*;

@Repository
public class HardcodedSpittleRepository implements SpittleRepository {

    private List<SpittleDTO> spittleList;
    private long nextId = 1L;

    @Autowired
    public HardcodedSpittleRepository() {
        final long initialNumberOfEntries = 20L;

        final double minLongitude = -180D;
        final double maxLongitude = 180D;

        final double minLatitude = -90D;
        final double maxLatitude = 90D;

        spittleList = new ArrayList<>();

        for (long i = 1; i <= initialNumberOfEntries; i++) {
            RandomDataGenerator randomizer = new RandomDataGenerator();

            double newSpittleLongitude = Precision.round(randomizer.nextUniform(minLongitude, maxLongitude), 2);
            double newSpittleLatitude = Precision.round(randomizer.nextUniform(minLatitude, maxLatitude), 2);

            SpittleDTO spittleDTO = new SpittleDTO.Builder()
                    .setId(nextId)
                    .setMessage("Высказывание с ID = " + nextId)
                    .setLatitude(newSpittleLatitude)
                    .setLongitude(newSpittleLongitude)
                    .setTime(LocalTime.now())
                    .build();

            spittleList.add(spittleDTO);

            nextId++;
        }
    }

    @Override
    public List<SpittleDTO> findSpittles(long max, int count) {
        List<SpittleDTO> spittleListCopy = null;
        try {
            Class<?> spittleListClass = spittleList.getClass();
            Constructor<?> spittleListClassConstructor = spittleListClass.getConstructor();
            spittleListCopy = (List<SpittleDTO>) spittleListClassConstructor.newInstance();
            spittleListCopy.addAll(Arrays.asList(new SpittleDTO[spittleList.size()]));
            Collections.copy(spittleListCopy, spittleList);
        } catch (Exception e) {
            System.out.println("Cannot perform search:" + e);
            //TODO where is LOG?
        }
        return spittleListCopy;
    }

    @Override
    public long save(SpittleDTO spittleToPersist) {
        SpittleDTO newSpittle = new SpittleDTO.Builder()
                .setId(nextId)
                .setMessage(spittleToPersist.getMessage())
                .setLatitude(spittleToPersist.getLatitude())
                .setLongitude(spittleToPersist.getLongitude())
                .setTime(spittleToPersist.getTime())
                .build();
        spittleList.add(newSpittle);
        return nextId++;
    }

    @Override
    public SpittleDTO findById(long id) {
        SpittleDTO spittle = findOriginalById(id);
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(spittle), SpittleDTO.class);
    }

    @Override
    public long update(long id, SpittleDTO editSpittle) {
        SpittleDTO spittle = findOriginalById(id);
        if (spittle != null) {
            spittle.setMessage(editSpittle.getMessage());
            spittle.setTime(editSpittle.getTime());
            spittle.setLongitude(editSpittle.getLongitude());
            spittle.setLatitude(editSpittle.getLatitude());
            return id;
        } else {
            return 0L;
        }
    }

    @Override
    public long remove(long id) {
        SpittleDTO spittleToDelete = findOriginalById(id);
        if (spittleToDelete != null) {
            spittleList.remove(spittleToDelete);
            return id;
        } else {
            return 0L;
        }
    }

    private SpittleDTO findOriginalById(long id) {
        SpittleDTO spittle = null;
        for (int i = spittleList.size() - 1; i >= 0; i--) {
            spittle = spittleList.get(i);
            if (spittle.getId() == id) {
                break;
            }
        }
        return spittle;
    }

}
