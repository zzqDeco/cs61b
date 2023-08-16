public class NBody {
  public static double readRadius(String filename) {
    In in = new In(filename);
    in.readInt();
    return in.readDouble();
  }

  public static Planet[] readPlanets(String filename) {
    In in = new In(filename);
    int m_size = in.readInt();
    Planet[] ans = new Planet[m_size];
    in.readDouble();
    for(int i = 0; i < m_size; i++) {
      ans[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
    }
    return ans;
  }
  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]),dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double r = readRadius(filename);
    Planet[] planets = readPlanets(filename);


    StdDraw.setScale(-r, r);
    StdDraw.enableDoubleBuffering();

    double t=0;
    int num=planets.length;
    while (t<=T) {
      double[] xForces = new double[num];
      double[] yForces = new double[num];
      for (int i = 0; i < num; i++) {
        xForces[i] = planets[i].calcNetForceExertedByX(planets);
        yForces[i] = planets[i].calcNetForceExertedByY(planets);
      }
      for (int i = 0; i < num; i++) {
        planets[i].update(dt,xForces[i],yForces[i]);
      }

      StdDraw.picture(0, 0, "images/starfield.jpg");

      for (Planet i : planets) {
        StdDraw.picture(i.xxPos, i.yyPos, "images/"+i.imgFileName);
      }

      StdDraw.show();
      StdDraw.pause(10);
      t += dt;
    }

    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", r);
    for (int i = 0; i < planets.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
    }
  }
}
