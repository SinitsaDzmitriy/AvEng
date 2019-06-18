package edu.sam.spittr.repository;

import com.google.gson.Gson;
import edu.sam.spittr.domain.Spittle;
import edu.sam.spittr.dto.SpittleDTO;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.util.Precision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HardcodedSpittleRepository implements SpittleRepository {

    private List<Spittle> spittleList;
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

            Spittle spittle = new Spittle();
            spittle.setId(nextId);
            spittle.setMessage("Высказывание с ID = " + nextId);
            spittle.setTime(LocalTime.now());
            spittle.setLatitude(newSpittleLatitude);
            spittle.setLongitude(newSpittleLongitude);

            spittleList.add(spittle);
            nextId++;
        }
    }

    @Override
    public long create(SpittleDTO spittleToPersist) {
        Spittle newSpittle = spittleToPersist.toSpittle();
        newSpittle.setId(nextId);
        spittleList.add(newSpittle);
        return nextId++;
    }

    @Override
    public List<SpittleDTO> readSpittles(long max, int count) {
        List<SpittleDTO> spittleDtoList = new ArrayList<>();
        SpittleDTO spittleDto;
        for(Spittle spittle: spittleList) {
            spittleDto = new SpittleDTO.Builder().build(spittle);
            spittleDtoList.add(spittleDto);
        }
        return spittleDtoList;
    }

    @Override
    public SpittleDTO readById(long id) {
        SpittleDTO dto = new SpittleDTO.Builder().build(readOriginalById(id));
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(dto), SpittleDTO.class);
    }

    private Spittle readOriginalById(long id) {
        Spittle spittle = null;
        for (int i = spittleList.size() - 1; i >= 0; i--) {
            spittle = spittleList.get(i);
            if (spittle.getId() == id) {
                break;
            }
        }
        return spittle;
    }


    @Override
    public long update(long idOfSpittleToUpdate, SpittleDTO editSpittle) {
        if(editSpittle.getId() != 0){
            Spittle spittle = readOriginalById(idOfSpittleToUpdate);
            if (spittle != null) {
                spittle.setMessage(editSpittle.getMessage());
                spittle.setTime(editSpittle.getTime());
                spittle.setLongitude(editSpittle.getLongitude());
                spittle.setLatitude(editSpittle.getLatitude());
                return spittle.getId();
            }
        } else {
            // throw new Exception();
        }
        return 0L;
    }

    @Override
    public long delete(long id) {
        Spittle spittleToDelete = readOriginalById(id);
        if (spittleToDelete != null) {
            spittleList.remove(spittleToDelete);
            return id;
        } else {
            return 0L;
        }
    }

}
