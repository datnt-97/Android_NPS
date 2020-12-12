package base.core.mudules.map;

public class MapModel {
    public static int locationRequestCode = 1000;
    private double Lat = 0.0, Lng = 0.0;

    public static int getLocationRequestCode() {
        return locationRequestCode;
    }

    public static void setLocationRequestCode(int locationRequestCode) {
        MapModel.locationRequestCode = locationRequestCode;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLng() {
        return Lng;
    }

    public void setLng(double lng) {
        Lng = lng;
    }
}
