package model;
import vector.Vector;
import processing.core.PApplet;
/**
 * @author chadnislow
 */
public class Planet extends PApplet{
    
    public double mass;
    public Vector position;
    public Vector velocity;
    public Vector acceleration;
    public float radius;
    public float r;
    public float g;
    public float b;
    
    //planet object based on real values
    public Planet (double mass, Vector position, Vector velocity, Vector acceleration, float radius, float r, float g, float b){
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.radius = radius;
        this.r = r;
        this.g = g;
        this.b = b;
    }
}
