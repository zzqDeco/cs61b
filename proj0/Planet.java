public class Planet {
  public double xxPos, yyPos, xxVel, yyVel, mass;
  public String imgFileName;
  public static double G = 6.67e-11;

  public Planet(double xP, double yP, double xV, double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Planet(Planet p) {
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

  public double calcDistance(Planet b) {
    return Math.sqrt((b.xxPos - xxPos) * (b.xxPos - xxPos) + (b.yyPos - yyPos) * (b.yyPos - yyPos));
  }

  public double calcForceExertedBy(Planet b) {
    return G * mass * b.mass / calcDistance(b) / calcDistance(b);
  }

  public double calcForceExertedByX(Planet b) {
    return calcForceExertedBy(b) * (b.xxPos - xxPos) / calcDistance(b);
  }

  public double calcForceExertedByY(Planet b) {
    return calcForceExertedBy(b) * (b.yyPos - yyPos) / calcDistance(b);
  }

  public double calcNetForceExertedByX(Planet[] arrPlanets) {
    double ans = 0.0;
    for (Planet b : arrPlanets) {
      if (!this.equals(b))
        ans += calcForceExertedByX(b);
    }
    return ans;
  }

  public double calcNetForceExertedByY(Planet[] arrPlanets) {
    double ans = 0.0;
    for (Planet b : arrPlanets) {
      if (!this.equals(b))
        ans += calcForceExertedByY(b);
    }
    return ans;
  }

  public void update(double t, double Fx, double Fy) {
    double ax = Fx / mass, ay = Fy / mass;
    xxVel += t * ax;
    yyVel += t * ay;
    xxPos += t * xxVel;
    yyPos += t * yyVel;
  }
}