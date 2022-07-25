
public class NBody {
    public static double readRadius(String fileName){
        In in = new In(fileName);
        int N = in.readInt();
        double R = in.readDouble();
        return R;
    }

    public static Planet[] readPlanets(String fileName){
        In in = new In(fileName);
        Planet[] planetArray = new Planet[5];
        in.readDouble();
        in.readDouble();
        for (int count = 0; count < 5; count = count +1){
            double xx = in.readDouble();
            double yy = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double m = in.readDouble();
            String name = in.readString();
            planetArray[count] = new Planet(xx, yy, xv, yv, m, name);
        }
        return planetArray;
    }

    public static void main(String[] args){
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = args[2];
        double R = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);

        StdDraw.setScale(-R, R);
        StdDraw.clear();
        String background = "proj0/images/starfield.jpg";
        StdDraw.picture(0, 0, background);   
             
        for (Planet p : planets){
            p.draw();
        }
        StdDraw.show();

		for (double t=0; t<=T; t+=dt) {
			double[] xForces = new double[5];
			double[] yForces = new double[5];
			for (int count=0; count<5; count = count + 1) {
				xForces[count] = planets[count].calcNetForceExertedByX(planets);
				yForces[count] = planets[count].calcNetForceExertedByY(planets);
			}
			for (int count=0; count<5; count = count + 1) {
				planets[count].update(dt, xForces[count], yForces[count]);
			}
			StdDraw.picture(0, 0, "proj0/images/starfield.jpg");
			for (Planet p : planets) {
				p.draw();
			}
        }

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", R);
		for (int count = 0; count < planets.length; count = count + 1) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   				planets[count].xxPos, planets[count].yyPos, planets[count].xxVel, planets[count].yyVel, planets[count].mass, planets[count].imgFileName);	
		}	

	}

}


