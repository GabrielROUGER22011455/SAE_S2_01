package EarthquakeJavaFX;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

/**
 * CustomCircleMarkerLayer is a MapLayer that displays a red circle marker on the map.
 */
public class CustomCircleMarkerLayer extends MapLayer {

    private EarthquakeData data;
    private final MapPoint mapPoint;
    private final Circle circle;

    /**
     * Constructs a CustomCircleMarkerLayer with the specified map point.
     *
     * @param mapPoint the map point (latitude and longitude) where the circle marker should be displayed
     * @see com.gluonhq.maps.MapPoint
     */
    public CustomCircleMarkerLayer(MapPoint mapPoint, float magnitude) {
        this.mapPoint = mapPoint;
        Color color = Color.web("#FFA500");
        if (Math.floor(magnitude) < 10){
            switch ((int)Math.floor(magnitude)){
                case 1:
                    color = Color.valueOf("#c0c9d1");
                    break;
                case 2:
                    color = Color.valueOf("#9fd0fc");
                    break;
                case 3:
                    color = Color.valueOf("#67f090");
                    break;
                case 4:
                    color = Color.valueOf("#e7f067");
                    break;
                case 5:
                    color = Color.valueOf("#f0b467");
                    break;
                case 6:
                    color = Color.valueOf("#f07c67");
                    break;
                case 7:
                    color = Color.valueOf("#e03a69");
                    break;
                case 8:
                    color = Color.valueOf("#ff5ebf");
                    break;
                case 9:
                    color = Color.valueOf("#c75eff");
                    break;
            }
        }else{
            color = Color.valueOf("#62428a");
        }


        // Create a red circle with a radius of 5
        this.circle = new Circle(magnitude/1.3);
        this.circle.setFill(color.deriveColor(0, 1, 1, 0.7)); // Définit l'opacité à 0.5 (50%)

        // Add the circle to the MapLayer if the mapPoint coordinates are valid
        if (mapPoint.getLatitude() != 0 && mapPoint.getLongitude() != 0) {
            this.getChildren().add(circle);
        }
    }

    /**
     * This method is called whenever the map is refreshed.
     * It updates the position of the circle marker based on the map point coordinates.
     */
    @Override
    protected void layoutLayer() {
        // Convert the MapPoint to a Point2D
        Point2D point2d = this.getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());

        // Move the circle marker to the specified coordinates
        circle.setTranslateX(point2d.getX());
        circle.setTranslateY(point2d.getY());
    }
}