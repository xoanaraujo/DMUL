package xoanaraujo.tuttifruttigdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import sun.jvm.hotspot.gc.z.ZForwardingEntry;
import xoanaraujo.tuttifruttigdx.fruit.FruitManager;
import xoanaraujo.tuttifruttigdx.fruit.FruitType;

import java.awt.font.FontRenderContext;

public class WorldContactAdapter implements ContactListener {
    private static final String TAG = WorldContactAdapter.class.getSimpleName();
    private final FruitManager fruitManager;


    public WorldContactAdapter(Core context) {
        this.fruitManager = context.getFruitManager();
    }
    @Override
    public void beginContact(Contact contact) {
        final Fixture fixureA =  contact.getFixtureA();
        final Fixture fixureB =  contact.getFixtureB();
        final Body bodyA = fixureA.getBody();
        final Body bodyB = fixureB.getBody();
        Gdx.app.debug("beginContact A", fixureA.getBody().getUserData().toString());
        Gdx.app.debug("beginContact B", fixureB.getBody().getUserData().toString());
        for (FruitType type : FruitType.values()) {
            if (type.getBit() == fixureA.getFilterData().categoryBits + fixureB.getFilterData().categoryBits){
                fruitManager.combineFruit(bodyA, bodyB, type);
            }
        }
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
