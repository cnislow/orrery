package model;
import java.util.ArrayList;
import processing.core.PApplet;
import vector.Vector;

/**
 *
 * @author chadnislow
 */

public class Model extends PApplet {
    
    public final double G = 6.67e-11;
    public final double dt = 18000;
    public final int size = 800;
    public final double scale = 1300e6;
    public final double bigScale = 3400e6;
    
    public ArrayList<Planet> planets = new ArrayList<>();
    public ArrayList<Planet> bigPlanets = new ArrayList<>();
    
    //initialize the planets
    public Planet sun = new Planet(1.98e30, new Vector(0, 0, 0), new Vector (0,0,0), 
                        new Vector (0,0,0), 50, 0,0,0);
    
    public Planet mercury = new Planet(3.285e23, new Vector(58e9, 0, 0), new Vector(0,-47400,0), 
                            new Vector(0,0,0), 10, 100,100,100);
    
    public Planet venus = new Planet (4.867e24, new Vector(108e9,0,0), new Vector(0,-35000,0), 
                          new Vector(0,0,0), 12, 255,0,100);
    
    public Planet earth = new Planet(5.98e24, new Vector(1.496e11, 0, 0), new Vector (0,-30000,0), 
                          new Vector (0,0,0), 20, 0,255,0);
    
    public Planet mars = new Planet(6.39e23, new Vector (227883.11e6,0,0), new Vector(0,-2.4e4,0), 
                         new Vector(0,0,0), 15, 255, 0,0);
    
    public Planet jupiter = new Planet(1.898e27, new Vector (7.78e11,0,0), new Vector(0,-13060,0), 
                            new Vector(0,0,0), 25, 0, 0,255);
    
    public Planet saturn = new Planet(5.683e26, new Vector (1.429e12,0,0), new Vector(0,-9690,0), 
                           new Vector(0,0,0), 30, 200, 80,140);
    
    
    public void settings(){
        size((int)size, (int) size);
    }
    
    public void setup() {
        stroke(255, 255, 0);
        frameRate(100);
        planets.add(earth);
        planets.add(mars);
        planets.add(mercury);
        planets.add(venus);
        bigPlanets.add(jupiter);
        bigPlanets.add(saturn);
    }
    
    //Draws the planets on the PApplet
    public void draw(){
        background(0);
        for (Planet p : planets){
            stroke(0);
            fill (p.r, p.b, p.g);
            ellipse((float) p.position.i/(float)scale + size/2, (float) p.position.j/(float)scale + size/2, p.radius, p.radius);
        }
        
        for (Planet p : bigPlanets){
            stroke(0);
            fill (p.r, p.b, p.g);
            ellipse((float) p.position.i/(float)bigScale + size/2, (float) p.position.j/(float)bigScale + size/2, p.radius, p.radius);
        }
        
        //saturn
        stroke(0);
        fill(200,80,140);
        ellipse((float) saturn.position.i/(float)4000e6 + size/2, (float) saturn.position.j/(float)4000e6 + size/2, saturn.radius, saturn.radius);
        
        stroke(0);
        fill(255,204,0);
        ellipse(400, 400, sun.radius, sun.radius);
        
        update();
    }

    //Calculates the force of gravity with relation to the sun
    public Vector forceG(Planet planet1) {
        return Vector.normScale(Vector.subtract(planet1.position, sun.position), 
               G * planet1.mass * sun.mass * 1/Math.pow(Vector.subtract(planet1.position, 
               sun.position).mag, 2));
    }
    
    //updates the position, velocity, and acceleration of the planets
    public void update(){
        
        for(Planet p : planets){
            p.acceleration = Vector.mult(forceG(p), -1/p.mass);
            p.velocity = Vector.add(p.velocity, (Vector.mult(p.acceleration, dt)));
            p.position = Vector.add(p.position, (Vector.mult(p.velocity,dt)));
        }
        
        for(Planet p : bigPlanets){
            p.acceleration = Vector.mult(forceG(p), -1/p.mass);
            p.velocity = Vector.add(p.velocity, (Vector.mult(p.acceleration, dt)));
            p.position = Vector.add(p.position, (Vector.mult(p.velocity,dt)));
        }
    }
    
    public static void main(String[] args) {
        PApplet.main("model.Model");
    }
}
