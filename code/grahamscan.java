import java.util.*;
public class grahamscan {
	public static final int CW = 1; public static final int CCW = 2;
	public static void main(String[] args) {
		pt[] pts = {new pt(1, 1), new pt(3, 1), new pt(2, 2), new pt(3, 3), new pt(1, 3)};
		Set<pt> hull = grahamScan(pts);
		for (pt p : hull) System.out.format("(%d, %d) ", p.x, p.y); System.out.println(); }
	public static int orientation(pt p, pt q, pt r) {       // Check orientation of three points
		int val = (q.y - p.y) * (r.x - q.x) -
		          (q.x - p.x) * (r.y - q.y);
		if (val == 0) return 0;                             // Collinear
		return (val > 0) ? CW : CCW; }
	public static Set<pt> grahamScan(pt[] pts) {
		Set<pt> hull = new HashSet<pt>();                   // Set of points in hull
		int l = 0;                                          // Select leftmost point to start with
		for (int i = 0; i < pts.length; i++)
			l = (pts[i].x < pts[l].x) ? i : l;
		int p = l, q;
		do {
			hull.add(pts[p]);
			q = (p + 1) % pts.length;                       // Advance to next point in CW order
			for (int i = 0; i < pts.length; i++)
				if (orientation(pts[p], pts[i], pts[q]) == CCW)
					q = i;
			p = q;
		} while (p != l);
		return hull; } }
class pt {
	public int x; public int y;
	public pt(int _x, int _y) { x = _x; y = _y; } }