package com.aivars.firstgame.handlers;

import com.aivars.firstgame.states.GameState;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;


public class ContactHandler implements ContactListener {

    private Array<Body> removableBodies;

    public ContactHandler() {
        super();
        removableBodies = new Array<Body>();
    }

    public void beginContact(Contact c) {
        Fixture fa = c.getFixtureA();
        Fixture fb = c.getFixtureB();

        if (fa.getBody().getUserData() != null) {
            System.out.println(fa.getBody().getUserData());
            if (fa.isSensor()) {
                Body userData = (Body) fa.getBody().getUserData();
                removableBodies.add(userData);
                removableBodies.add(fa.getBody());
            } else if (fa.getBody().getUserData().equals("obstacle")) {
                GameState.setGameOver(true);
            }
        }

        if (fb.getBody().getUserData() != null) {
            if (fb.isSensor()) {
                Body userData = (Body) fb.getBody().getUserData();
                removableBodies.add(userData);
                removableBodies.add(fb.getBody());
            } else if (fb.getBody().getUserData().equals("obstacle")) {
                GameState.setGameOver(true);
            }
        }
    }

    public Array<Body> getRemovableBodies() {
        return removableBodies;
    }

    public void endContact(Contact c) {

    }

    public void preSolve(Contact c, Manifold m) {

    }

    public void postSolve(Contact c, ContactImpulse ci) {

    }

}
