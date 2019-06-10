package edu.sam.spittr.data;

import edu.sam.spittr.entities.Spitter;

public interface SpitterRepository {
    Spitter save(Spitter newUser);
    Spitter findByUsername(String username);
}
