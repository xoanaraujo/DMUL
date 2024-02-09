package xoanaraujo.tuttifruttigdx.fruit;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import xoanaraujo.tuttifruttigdx.Core;

import java.util.Iterator;

public class FruitManager {
    private final World world;
    private Array<Body> bodies;
    private Array<Fruit> fruitToCreate;
    private Array<Body> bodiesToDestroy;

    public FruitManager(Core context) {
        this.world = context.getWorld();
        bodies = new Array<>();
        fruitToCreate = new Array<>();
        bodiesToDestroy = new Array<>();
    }

    public void combineFruit(Body bodyA, Body bodyB, FruitType type){
        bodiesToDestroy.add(bodyA);
        bodiesToDestroy.add(bodyB);
        fruitToCreate.add(new Fruit(
                world,
                type,
                new Vector2(
                        (bodyA.getPosition().x + bodyB.getPosition().x) / 2,
                        (bodyA.getPosition().y + bodyB.getPosition().y) / 2)));
    }

    public void addFruit(Fruit fruit){
        fruitToCreate.add(fruit);
    }

    public void createFruits(){
        Iterator<Fruit> iter = fruitToCreate.iterator();
        while (iter.hasNext()){
            Fruit fruit = iter.next();
            fruit.createBody();
            iter.remove();
        }
    }

    public void destroyFruits(){
        Iterator<Body> iter = bodiesToDestroy.iterator();
        while (iter.hasNext()){
            Body body = iter.next();
            if (bodies.contains(body, false)){
                world.destroyBody(body);
            }
            iter.remove();
        }
    }
}
