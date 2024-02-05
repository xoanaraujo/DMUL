package xoanaraujo.tuttifruttigdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class WorldContactAdapter implements ContactListener {
    private static final String TAG = WorldContactAdapter.class.getSimpleName();
    @Override
    public void beginContact(Contact contact) {
        final Fixture fixureA =  contact.getFixtureA();
        final Fixture fixureB =  contact.getFixtureB();
        Gdx.app.debug("beginContact A", fixureA.getBody().getUserData().toString());
        Gdx.app.debug("beginContact B", fixureB.getBody().getUserData().toString());
    }

    @Override
    public void endContact(Contact contact) {
        final Fixture fixureA =  contact.getFixtureA();
        final Fixture fixureB =  contact.getFixtureB();
        Gdx.app.debug("endContact A", fixureA.getBody().getUserData().toString());
        Gdx.app.debug("endContact B", fixureB.getBody().getUserData().toString());
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}
