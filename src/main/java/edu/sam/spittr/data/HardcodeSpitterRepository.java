package edu.sam.spittr.data;

import edu.sam.spittr.entities.Spitter;

import org.springframework.stereotype.Repository;

@Repository
public class HardcodeSpitterRepository implements SpitterRepository {
    @Override
    public Spitter save(Spitter spitter){return spitter;}

    @Override
    public Spitter findByUsername(String username){
        return new Spitter(0L, "Test", "Spitter", "test_spitter", "TestPassword");}
}
