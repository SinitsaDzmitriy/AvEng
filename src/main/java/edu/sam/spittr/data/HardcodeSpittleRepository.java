package edu.sam.spittr.data;

import edu.sam.spittr.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class HardcodeSpittleRepository implements SpittleRepository {

    @Autowired
    public HardcodeSpittleRepository() {}

    @Override
    public List<Spittle> findSpittles(long max, int count){
        ArrayList<Spittle> spittles = new ArrayList<>();
        spittles.add(new Spittle("1st message", new Date(),52.0976, 23.7341 ));
        spittles.add(new Spittle("2nd message", new Date(),53.9045, 27.5615 ));
        return spittles;
    }
}
