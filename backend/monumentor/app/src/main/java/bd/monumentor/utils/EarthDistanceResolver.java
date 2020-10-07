package bd.monumentor.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EarthDistanceResolver {

  //https://community.esri.com/groups/coordinate-reference-systems/blog/2017/10/05/haversine-formula
  public double distance(Double longitude1, Double latitude1, Double longitude2, Double latitude2) {

    double R = 6371000.0;
    double phi1 = Math.toRadians(latitude1);
    double phi2 = Math.toRadians(latitude2);

    double deltaPhi = Math.toRadians(latitude2 - latitude1);
    double deltaLambda = Math.toRadians(longitude2- longitude1);

    double a = Math.pow(Math.sin(deltaPhi / 2.0), 2)
        + Math.cos(phi1) * Math.cos(phi2)
        * Math.pow(Math.sin(deltaLambda / 2.0), 2);

    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    double meters = R * c;
    double km = meters / 1000.0;

    return km;
  }
}
