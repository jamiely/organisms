package organisms.g2.data;

public class Point {
   public static Point add(Point a, Point b) {
      return new Point(a.x + b.x, a.y + b.y);
   }

   public static Point subtract(Point a, Point b) {
      return new Point(a.x - b.x, a.y - b.y);
   }
   
   public static Point origin() {
	   return new Point(0, 0);
   }

   public int x;
   public int y;

   public Point(int x, int y) {
      this.x = x;
      this.y = y;
   }
   
   public Point(Point p) {
	   this(p.x, p.y);
   }
   
   public void add(Point p) {
	   this.x += p.x;
	   this.y += p.y;
   }

   @Override
   public boolean equals(Object o) {
      if (o instanceof Point) {
         Point that = (Point) o;
         return this.x == that.x && this.y == that.y;
      }
      return false;
   }

   private int getDeltaX(Point that) {
      return Math.abs(this.x - that.x);
   }

   private int getDeltaY(Point that) {
      return Math.abs(this.y - that.y);
   }

   public double getEuclideanDistance(Point that) {
      int deltaX = this.x - that.x;
      int deltaY = this.y - that.y;
      return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
   }

   public int getTaxiCabDistance(Point that) {
      return getDeltaX(that) + getDeltaY(that);
   }

   public int getChebyshevDistance(Point that) {
      return Math.max(getDeltaX(that), getDeltaY(that));
   }

   public int getLineDistance (Point that) {
      return Math.min(getDeltaX(that), getDeltaY(that));
   }

   @Override
   public int hashCode() {
      int hashcode = 23;
      hashcode = (hashcode * 37) + this.x;
      hashcode = (hashcode * 37) + this.y;
      return hashcode;
   }

   @Override
   public String toString() {
      return String.format("(%d, %d)", x, y);
   }

}
