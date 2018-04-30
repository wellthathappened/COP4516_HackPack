import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.*;

public class Geometry {
	
	public static class Vect {
		double x,y,z;
		public Vect(double _x, double _y, double _z){
			x=_x; y=_y; z=_z; }
		public Vect add(Vect v) {
			return new Vect(x+v.x, y+v.y, z+v.z); }
		public Vect subtract(Vect v){
			return new Vect(x-v.x, y-v.y, z-v.z); }
		public Vect scale(double mult){
			return new Vect(mult*x, mult*y, mult*z); }
		public Vect crossProd(Vect v) {
			return new Vect((y*v.z)-(z*v.y),(z-v.x)-(x*v.z),(x-v.y)-(y*v.x)); }
		public double dotProd(Vect v){
			return (x*v.x)+(y*v.y)+(z*v.z); }
	}
	
	public static void main(String[] args){
		double p1x = 0D, p1y = 0D, p1z = 0D, p2x = 0D, p2y = 0D, p2z = 0D, p3x = 0D, p3y = 0D, p4x = 0D, p4y = 0D, circX = 0D, circY = 0D, circR = 0D;
		Line2D line1 = new Line2D.Double(p1x, p1y, p2x, p2y), line2 = new Line2D.Double(p3x, p3y, p4x, p4y);
		Point2D circle = new Point2D.Double(circX, circY);
		Vect vect1 = new Vect(p1x, p1y, p1z), vect2 = new Vect(p2x, p2y, p2z), plane1 = new Vect(p1x, p1y, p1z), plane2 = new Vect(p1x, p1y, p1z), plane3 = new Vect(p1x, p1y, p1z);
		double[] coordsX = {p1x, p2x, p3x }, coordsY = {p1y, p2y, p3y };
		
		boolean res1 = intersectLineSegs(line1, line2);
		boolean res2 = intersectLines(p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y);
		boolean res3 = intersectLineSegCircle(line1, circle, circR);
		boolean res4 = intersectLineCircle(line1, circle, circR);
		boolean res5 = intersectLinePlane3(vect1, vect2, plane1, plane2, plane3);
		double res6 = polygonArea(coordsX, coordsY);
		boolean res7 = pointInPolygon(p1x, p1y, coordsX, coordsY);
	}
	
	//Returns whether two line segments intersect
	public static boolean intersectLineSegs(Line2D line1, Line2D line2){
		return line1.intersectsLine(line2); }
	
	//Returns whether two lines intersect
	public static boolean intersectLines(double p1x, double p1y, double p2x, double p2y, double p3x, double p3y, double p4x, double p4y){
  		double line1Slope = (p2y-p1y) / (p2x-p1x);
  		double line2Slope = (p4y-p3y) / (p4x-p3x);
  		return line1Slope != line2Slope; }
	
	//Returns whether a line segment intersects a circle
	public static boolean intersectLineSegCircle(Line2D line, Point2D circle, double r){
		if(!(line.getP1().distance(circle) > r) || !( line.getP2().distance(circle) > r))
			return true;
		return false; }
	//Returns whether a line intersects a circle
	public static boolean intersectLineCircle(Line2D line, Point2D circle, double r){
		if(line.getP1().distance(circle) < r && line.getP2().distance(circle) < r)
			return true;
		return false;}	
	//Returns whether a Vector and a line with 3 coordinates intersects
	public static boolean intersectLinePlane3(Vect v1, Vect v2, Vect p1, Vect p2, Vect p3)
	{
		Vect testV = (p2.subtract(p1).crossProd(p3.subtract(p1)));
		if(Math.abs(testV.dotProd(v1.subtract(v2))) < 0.0000001) 
			return false;
		double scale = - (testV.dotProd(v1.subtract(p1)) / (testV.dotProd(v1.subtract(v2))));
		Vect resV = v1.add(v1.subtract(v2).scale(scale));
		double x = resV.subtract(p1).dotProd(p2.subtract(p1));
		double y = resV.subtract(p1).dotProd(p3.subtract(p1));
		return (x >= 0D && x <= p2.subtract(p1).dotProd(p2.subtract(p1)) && y >= 0.0 && y <= p3.subtract(p1).dotProd(p3.subtract(p1))); }
	//Returns the area of a polygon
	public static double polygonArea(double[] coordsX, double[] coordsY)
	{
		int len = coordsX.length; double res = 0D; 
		for(int i=0; i < len; i++){
			int k= (i+1) % len;
			res += coordsX[i] * coordsY[k];
			res -= coordsY[i] * coordsX[k]; }
		res /= 2; res = Math.abs(res);
		return res; }
	//Returns whether a point is in a polygon or not
	public static boolean pointInPolygon(double pX, double pY, double[] coordsX, double[] coordsY){
		Path2D polygon = new Path2D.Double();
		polygon.moveTo(coordsX[0], coordsY[0]);
		for(int i=1; i <coordsX.length; i++){
			polygon.lineTo(coordsX[i], coordsY[i]);}
		return polygon.contains(pX, pY); }

	
}
